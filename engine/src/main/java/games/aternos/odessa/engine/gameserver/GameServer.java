package games.aternos.odessa.engine.gameserver;

import org.bukkit.plugin.Plugin;

public class GameServer {

  private final Plugin plugin;

  public GameServer(Plugin plugin) {
    this.plugin = plugin;
  }

  public Plugin getPlugin() {
    return plugin;
  }
}
