package games.aternos.odessa.engine.service;

import games.aternos.odessa.gameapi.GameApi;

import javax.annotation.Nonnull;

abstract public class Service {

  /**
   * All Services are owned by the GameAPI
   */
  private final GameApi gameApi;

  /**
   * Super Constructor for all Services
   *
   * @param gameApi The Owning GameAPI
   */
  protected Service(@Nonnull GameApi gameApi) {
    this.gameApi = gameApi;
  }

  /**
   * Returns the owning GameAPI
   */
  public GameApi getGameApi() {
    return gameApi;
  }
}
