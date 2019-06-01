package games.aternos.odessa.engine.service.ioconfiguration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class IoConfigurationFile {

  private final File configFile;
  private final FileConfiguration configuration;

  protected IoConfigurationFile(File configFile) {
    this.configFile = configFile;
    this.configuration = YamlConfiguration.loadConfiguration(this.configFile);
    try {
      this.configuration.save(this.configFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public FileConfiguration getConfiguration() {
    return configuration;
  }

  public File getConfigFile() {
    return configFile;
  }
}
