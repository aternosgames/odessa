package games.aternos.odessa.core.engine;

import games.aternos.odessa.api.game.Game;
import games.aternos.odessa.core.Debug;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/**
 * Called a bit after startup to get the ball rolling
 */
public class StartupTask extends BukkitRunnable {

  private final Plugin plugin;

  public StartupTask(Plugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public void run() {

    int gameListSize = GameManager.getInstance().getGameList().size();
    Game game;
    switch (gameListSize) {
      case 0:// No games registered...nothing for odessa to do
        Debug.$("No games loaded... Odessa will do nothing and not function.");
        return;
      case 1:// Only one game registered.
        game = GameManager.getInstance().getGameList().get(0);
        GameManager.getInstance().setActiveGame(game);
      default:
        Random r = new Random();
        game = GameManager.getInstance().getGameList().get(r.nextInt(gameListSize));
        GameManager.getInstance().setActiveGame(game);
    }

    GameManager.getInstance().getActiveGame().start(plugin);
    Debug.$("Selected Game: " + game.getGameData().getGameName());


  }

}
