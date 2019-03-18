package games.aternos.odessa.engine.lobby;

import games.aternos.odessa.engine.lobby.scoreboard.LobbyBoard;
import games.aternos.odessa.gameapi.GameApi;
import games.aternos.odessa.gameapi.game.Game;
import games.aternos.odessa.gameapi.game.GameConfiguration;
import games.aternos.odessa.gameapi.game.GameLifecycleManager;

import javax.annotation.Nonnull;

/**
 * Uniformed Lobby System that can be called into action by a game.
 */
public class GameLobbySystem {

  private final GameLifecycleManager gameLifecycleManager;
  private final GameApi gameApi;
  private final Game game;
  private final GameConfiguration gameConfiguration;
  private final LobbyBoard lobbyBoard;

  private LobbyController lobbyController;

  public GameLobbySystem(@Nonnull GameLifecycleManager gameLifecycleManager, @Nonnull GameApi gameApi, @Nonnull GameConfiguration gameConfiguration, LobbyBoard lobbyBoard) {
    this.gameLifecycleManager = gameLifecycleManager;
    this.gameApi = gameApi;
    game = gameApi.getGame();
    this.gameConfiguration = gameConfiguration;
    this.lobbyBoard = lobbyBoard;
    this.lobbyController = new LobbyController(this);
  }

  public void startLobby() {
    this.getLobbyController().registerLobbyListeners();
  }

  public void stopLobby() {
    this.getLobbyController().unRegisterLobbyListeners();
  }

  public LobbyBoard getLobbyBoard() {
    return lobbyBoard;
  }

  public LobbyController getLobbyController() {
    return lobbyController;
  }

  public void setLobbyController(LobbyController lobbyController) {
    this.lobbyController = lobbyController;
  }

  public GameConfiguration getGameConfiguration() {
    return gameConfiguration;
  }

  public GameLifecycleManager getGameLifecycleManager() {
    return gameLifecycleManager;
  }

  public GameApi getGameApi() {
    return gameApi;
  }

  public Game getGame() {
    return game;
  }
}
