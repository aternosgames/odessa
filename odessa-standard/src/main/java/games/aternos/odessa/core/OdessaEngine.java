package games.aternos.odessa.core;


import games.aternos.odessa.core.command.CommandManager;
import games.aternos.odessa.core.engine.StartupTask;
import org.bukkit.plugin.java.JavaPlugin;

public class OdessaEngine extends JavaPlugin {

  private static OdessaEngine odessaEngine;

  public static OdessaEngine getOdessaEngine() {
    return odessaEngine;
  }

  @Override
  public void onEnable() {
    odessaEngine = this;
    new StartupTask(this).runTaskLater(this, 60);
    getCommand("server").setExecutor(new CommandManager());
  }

}
