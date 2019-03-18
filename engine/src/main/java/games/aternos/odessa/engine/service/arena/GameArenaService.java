package games.aternos.odessa.engine.service.arena;

import games.aternos.odessa.engine.service.Service;
import games.aternos.odessa.engine.service.arena.ioconfiguration.GameArenaIoConfiguration;
import games.aternos.odessa.engine.service.ioconfiguration.IoConfigurationService;
import games.aternos.odessa.gameapi.GameApi;
import games.aternos.odessa.gameapi.game.element.Arena;

import java.util.List;

public class GameArenaService extends Service {

  private final IoConfigurationService ioConfigurationService;
  private final GameArenaIoConfiguration gameArenaIoConfiguration;
  private final String gameName;

  public GameArenaService(GameApi gameApi, IoConfigurationService ioConfigurationService, String gameName) {
    super(gameApi);
    this.gameName = gameName;
    this.ioConfigurationService = ioConfigurationService;
    this.gameArenaIoConfiguration = new GameArenaIoConfiguration(this.ioConfigurationService, gameName);
  }

  public List<Arena> getArenas() {
    return this.gameArenaIoConfiguration.loadMapsFromIo();
  }

  public IoConfigurationService getIoConfigurationService() {
    return ioConfigurationService;
  }

  public GameArenaIoConfiguration getGameArenaIoConfiguration() {
    return gameArenaIoConfiguration;
  }

  public String getGameName() {
    return gameName;
  }
}
