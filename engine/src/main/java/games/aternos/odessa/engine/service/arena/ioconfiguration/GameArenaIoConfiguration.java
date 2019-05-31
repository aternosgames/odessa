package games.aternos.odessa.engine.service.arena.ioconfiguration;

import games.aternos.odessa.engine.service.ioconfiguration.IoConfigurationFile;
import games.aternos.odessa.engine.service.ioconfiguration.IoConfigurationService;
import games.aternos.odessa.gameapi.game.element.Arena;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameArenaIoConfiguration extends IoConfigurationFile {
  public GameArenaIoConfiguration(IoConfigurationService owner, String gameName) {
    super(new File(owner.getGameApi().getDataFolder() + "/" + gameName, "arenas.yml"));
  }

  public void createArena(String name, String author) {
    this.getConfiguration().set(name + ".author", author);
    try {
      this.getConfiguration().save(this.getConfigFile());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void addSpawn(Location spawn, Integer teamID, Integer spawnID, String mapName) {

    this.getConfiguration().set(mapName + ".spawns." + spawnID + ".world", spawn.getWorld().getName());
    this.getConfiguration().set(mapName + ".spawns." + spawnID + ".x", spawn.getBlockX());
    this.getConfiguration().set(mapName + ".spawns." + spawnID + ".y", spawn.getBlockY());
    this.getConfiguration().set(mapName + ".spawns." + spawnID + ".z", spawn.getBlockZ());
    this.getConfiguration().set(mapName + ".spawns." + spawnID + ".team", teamID);
    try {
      this.getConfiguration().save(this.getConfigFile());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<Arena> loadMapsFromIo() {

    List<Arena> arenas = new ArrayList<>();

    for (String mapName : this.getConfiguration().getConfigurationSection("").getKeys(false)) {

      HashMap<Location, Integer> spawnPoints = new HashMap<>();

      for (String spawnId : this.getConfiguration().getConfigurationSection(mapName + ".spawns").getKeys(false)) {
        World world = Bukkit.getWorld(this.getConfiguration().getString(mapName + ".spawns." + spawnId + ".world"));
        int x = this.getConfiguration().getInt(mapName + ".spawns." + spawnId + ".x");
        int y = this.getConfiguration().getInt(mapName + ".spawns." + spawnId + ".y");
        int z = this.getConfiguration().getInt(mapName + ".spawns." + spawnId + ".z");
        int team = this.getConfiguration().getInt(mapName + ".spawns." + spawnId + ".team");
        spawnPoints.put(new Location(world, x, y, z), team);
      }

      String author = this.getConfiguration().getString(mapName + ".author");

      arenas.add(new Arena(spawnPoints, mapName, author));
    }

    return arenas;
  }

}
