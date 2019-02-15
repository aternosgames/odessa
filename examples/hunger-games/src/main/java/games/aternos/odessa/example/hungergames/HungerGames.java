package games.aternos.odessa.example.hungergames;

import games.aternos.odessa.example.hungergames.phase.LobbyPhase;
import games.aternos.odessa.gameapi.GameApi;
import games.aternos.odessa.gameapi.game.Game;
import games.aternos.odessa.gameapi.game.GameData;
import games.aternos.odessa.gameapi.game.GameLifecycleManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * For now extending JavaPlugin to be able to load itself...may change? todo
 */
public class HungerGames extends JavaPlugin implements Game {

  private HungerGamesGameLifecycleManager hungerGamesGameLifecycleManager;
  private HungerGamesData hungerGamesData;

  @Override
  public void onEnable(){
    try {
      GameApi.getGameApi().registerGame(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void initialize(){
    this.hungerGamesGameLifecycleManager = new HungerGamesGameLifecycleManager(new LobbyPhase());
    this.hungerGamesData = new HungerGamesData();
  }

  @Override
  public GameLifecycleManager getGameLifecycleManager() {
    return this.hungerGamesGameLifecycleManager;
  }

  @Override
  public GameData getGameData() {
    return this.hungerGamesData;
  }
}
