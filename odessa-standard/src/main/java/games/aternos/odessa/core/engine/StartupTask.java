package games.aternos.odessa.core.engine;

import games.aternos.odessa.api.game.Game;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/**
 * Called a bit after startup to get the ball rolling
 * TODO: Make a more elegant selection system..probably config related to server type etc.
 */
public class StartupTask extends BukkitRunnable {

  private final Plugin plugin;

  public StartupTask(Plugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public void run() {

    Integer gameListSize = GameManager.getInstance().getGameList().size();

    switch (gameListSize) {

      case 0:// No games registered...nothing for odessa to do
        return;
      case 1:// Only one game registered.
        GameManager.getInstance().setActiveGame(GameManager.getInstance().getGameList().get(0));
      default:
        Random r = new Random();
        Game selectedGame = GameManager.getInstance().getGameList().get(r.nextInt(gameListSize));
        GameManager.getInstance().setActiveGame(selectedGame);
    }

    GameManager.getInstance().getActiveGame().start(plugin);


  }

}
