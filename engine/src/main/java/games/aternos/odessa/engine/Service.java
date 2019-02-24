package games.aternos.odessa.engine;

import games.aternos.odessa.gameapi.GameApi;

abstract public class Service {

  private final GameApi gameApi;

  protected Service(GameApi gameApi) {
    this.gameApi = gameApi;
  }

  public GameApi getGameApi() {
    return gameApi;
  }
}
