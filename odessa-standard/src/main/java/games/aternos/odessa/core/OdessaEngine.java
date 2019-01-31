package games.aternos.odessa.core;


import games.aternos.odessa.core.engine.StartupTask;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class OdessaEngine extends JavaPlugin {

  @Override
  public void onEnable() {
    BukkitTask startup = new StartupTask(this).runTaskLater(this, 60);
  }

}
