package games.aternos.odessa.engine.service.arena.ioconfiguration;

import games.aternos.odessa.engine.service.ioconfiguration.IoConfigurationFile;
import games.aternos.odessa.engine.service.ioconfiguration.IoConfigurationService;
import games.aternos.odessa.gameapi.game.element.Arena;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameArenaIoConfiguration extends IoConfigurationFile {
  public GameArenaIoConfiguration(IoConfigurationService owner, String gameName) {
    super(new File(owner.getGameApi().getDataFolder() + "/" + gameName, "arenas.yml"), owner);
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
