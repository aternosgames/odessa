package games.aternos.odessa.gameapi;

import edu.umd.cs.findbugs.annotations.NonNull;
import games.aternos.odessa.gameapi.eventhook.EventHookLoader;
import games.aternos.odessa.gameapi.game.Game;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

/**
 * Handles the loading and execution of Odessa Games.
 */
public class GameApi extends JavaPlugin {

  private static GameApi gameApi;

  private Game game;

  public static GameApi getGameApi() {
    return gameApi;
  }

  @Override
  public void onEnable() {
    gameApi = this;
      EventHookLoader a = new EventHookLoader();
  }

  /**
   * Registers and starts up a game on Odessa.
   * todo: more elegant multi game supporting thingy
   *
   * @param game The game.
   */
  public void registerGame(@NonNull Game game) {

    if (this.getGame() != null) {
      Debug.$("A game is already registered..ignoring");
      return;
    }
    this.setGame(game);
    this.getGame().initialize();
    Debug.$("Loading Game: " + this.getGame().getGameConfiguration().getGameName());
  }

  public Game getGame() {
    return game;
  }

  private void setGame(@Nonnull Game game) {
    this.game = game;
  }

}
