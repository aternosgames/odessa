package games.aternos.examplegame.teamdeathmatch;

import games.aternos.odessa.core.engine.GameManager;
import org.bukkit.plugin.java.JavaPlugin;


class TDM extends JavaPlugin {

  @Override
  public void onEnable() {
    GameManager.getInstance().registerGame(new TDMGame());
  }
}
