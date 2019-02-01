package games.aternos.odessa.core;


import games.aternos.odessa.core.engine.StartupTask;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class OdessaEngine extends JavaPlugin {

  private static OdessaEngine odessaEngine;

  public static OdessaEngine getOdessaEngine() {
    return odessaEngine;
  }

  @Override
  public void onEnable() {
    odessaEngine = this;
    BukkitTask startup = new StartupTask(this).runTaskLater(this, 60);
  }

}
