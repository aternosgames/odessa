package games.aternos.odessa.example.hungergames;

import games.aternos.odessa.example.hungergames.phase.lobby.LobbyPhase;
import games.aternos.odessa.gameapi.GameApi;
import games.aternos.odessa.gameapi.game.Game;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Core class of the example game.
 */
public class HungerGames extends JavaPlugin {


  @Override
  public void onEnable() {
    GameApi.getGameApi().registerGame(new HungerGame());
  }

  private class HungerGame extends Game {
    public HungerGame() {
      super(new HungerGamesGameLifecycleManager(), new HungerGamesData(), new HungerGamesGameConfiguration());
    }

    public void initialize() {
      this.getGameLifecycleManager().setActivePhase(new LobbyPhase(this.getGameLifecycleManager(), this));
    }

  }



}
