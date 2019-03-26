package games.aternos.odessa.engine.lobby.ioconfiguration;

import games.aternos.odessa.engine.service.ioconfiguration.IoConfigurationFile;
import games.aternos.odessa.engine.service.ioconfiguration.IoConfigurationService;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;

/**
 * Handles the Lobby Io Condifuration for Arenas.
 */
public class LobbyIoConfiguration extends IoConfigurationFile {

  public LobbyIoConfiguration(IoConfigurationService owner) {
    super(new File(owner.getGameApi().getDataFolder() + "/lobby", "lobby.yml"), owner);
  }

  @NotNull
  public Location getLobbySpawn() {
    World world = Bukkit.getWorld((String) this.getConfiguration().get("spawn.world"));
    Integer x = (Integer) this.getConfiguration().get("spawn.x");
    Integer y = (Integer) this.getConfiguration().get("spawn.y");
    Integer z = (Integer) this.getConfiguration().get("spawn.z");

    return new Location(world, x, y, z);
  }

  public void setLobbySpawn(@Nonnull Location location) {
    this.getConfiguration().set("spawn.world", location.getWorld().getName());
    this.getConfiguration().set("spawn.x", location.getBlockX());
    this.getConfiguration().set("spawn.y", location.getBlockY());
    this.getConfiguration().set("spawn.z", location.getBlockZ());
    try {
      this.getConfiguration().save(this.getConfigFile());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
