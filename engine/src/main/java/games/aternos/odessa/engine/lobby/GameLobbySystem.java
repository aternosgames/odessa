package games.aternos.odessa.engine.lobby;

import games.aternos.odessa.gameapi.GameApi;
import games.aternos.odessa.gameapi.game.GameLifecycleManager;

/**
 * Uniformed Lobby System that can be called into action by a game.
 */
public class GameLobbySystem {

  private final GameLifecycleManager gameLifecycleManager;
  private final GameApi gameApi;


  public GameLobbySystem(GameLifecycleManager gameLifecycleManager, GameApi gameApi) {
    this.gameLifecycleManager = gameLifecycleManager;
    this.gameApi = gameApi;
  }

  public void startLobby() {

  }

  public void stopLobby() {

  }

  public GameLifecycleManager getGameLifecycleManager() {
    return gameLifecycleManager;
  }

  public GameApi getGameApi() {
    return gameApi;
  }
}
