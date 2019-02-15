package games.aternos.odessa.engine.lobby;

import games.aternos.odessa.gameapi.GameApi;
import games.aternos.odessa.gameapi.game.Game;

/**
 * Uniformed Lobby System that can be called into action by a game.
 */
public class GameLobbySystem {

  private final Game game;
  private final GameApi gameApi;


  public GameLobbySystem(Game game, GameApi gameApi){
    this.game = game;
    this.gameApi = gameApi;
  }



}
