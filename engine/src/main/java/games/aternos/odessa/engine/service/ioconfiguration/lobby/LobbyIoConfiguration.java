package games.aternos.odessa.engine.service.ioconfiguration.lobby;

import games.aternos.odessa.engine.service.ioconfiguration.IoConfigurationFile;
import games.aternos.odessa.engine.service.ioconfiguration.IoConfigurationService;

import java.io.File;

public class LobbyIoConfiguration extends IoConfigurationFile {

  public LobbyIoConfiguration(IoConfigurationService owner) {
    super(new File(owner.getGameApi().getDataFolder() + "/lobby", "lobby.yml"), owner);
  }
}
