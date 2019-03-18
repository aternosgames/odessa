package games.aternos.odessa.engine.service.ioconfiguration;

import games.aternos.odessa.engine.service.Service;
import games.aternos.odessa.gameapi.GameApi;

/**
 * Manages file based configuration, such as spawns and arenas (for now).
 * todo
 */
public class IoConfigurationService extends Service {

  protected IoConfigurationService(GameApi gameApi) {
    super(gameApi);
  }
}
