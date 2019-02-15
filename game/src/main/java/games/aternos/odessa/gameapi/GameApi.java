package games.aternos.odessa.gameapi;

import edu.umd.cs.findbugs.annotations.NonNull;
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
  }

  /**
   * Registers and starts up a game on Odessa.
   * todo: more elegant multi game supporting thingy
   *
   * @param game The game.
   * @throws Exception If a game is already registered.
   */
  public void registerGame(@NonNull Game game) throws Exception {

    if (this.getGame() != null) {
      throw new Exception("Game Already Registered"); //todo: custom clean exceptions
    }
    this.setGame(game);
    this.getGame().initialize();
    Debug.$("Loading Game: " + this.getGame().getGameConfiguration().getGameName());
  }

  protected void unRegisterGame() { // terribly unsafe, should be removed in the future, but for tests currently.
    this.getGame().uninitialize();
    this.setGame(null);
  }

  public Game getGame() {
    return game;
  }

  private void setGame(@Nonnull Game game) {
    this.game = game;
  }

}
