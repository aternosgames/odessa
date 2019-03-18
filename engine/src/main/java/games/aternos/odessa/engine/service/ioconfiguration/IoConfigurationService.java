package games.aternos.odessa.engine.service.ioconfiguration;

import games.aternos.odessa.engine.service.Service;
import games.aternos.odessa.gameapi.GameApi;

/**
 * Allows for the creation and management of sep Configuration Files on the disk
 * Used for spawns and such, until databaseized.
 */
public class IoConfigurationService extends Service {

  public IoConfigurationService(GameApi gameApi) {
    super(gameApi);
  }


}
