package games.aternos.odessa.engine.ioconfiguration;

import games.aternos.odessa.gameapi.GameApi;

/**
 * Manages file based configuration, such as spawns and arenas (for now).
 * todo
 */
public class IoConfigurationManager {

  private final GameApi gameApi;

  public IoConfigurationManager(GameApi gameApi) {
    this.gameApi = gameApi;
  }
}
