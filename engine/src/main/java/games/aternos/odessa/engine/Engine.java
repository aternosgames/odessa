package games.aternos.odessa.engine;

import games.aternos.odessa.engine.gameserver.GameServer;
import org.bukkit.plugin.java.JavaPlugin;

public class Engine extends JavaPlugin {

  private GameServer gameServer;

  private static Engine engine;

  @Override
  public void onEnable(){
    engine = this;
    this.gameServer = new GameServer(this);
  }

  public GameServer getGameServer() {
    return gameServer;
  }

  public static Engine getEngine() {
    return engine;
  }
}
