package games.aternos.odessa.core.engine;

import games.aternos.odessa.api.game.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains all registered games.
 */
public class GameManager {

  static GameManager instance = new GameManager();

  private List<Game> gameList;

  private Game activeGame;

  public GameManager() {
    gameList = new ArrayList<>();
  }

  public static GameManager getInstance() {
    return instance;
  }

  public Game getActiveGame() {
    return activeGame;
  }

  public void setActiveGame(Game activeGame) {
    this.activeGame = activeGame;
  }

  public boolean hasActiveGame() {
    return activeGame == null;
  }

  public void registerGame(Game game) {
    this.getGameList().add(game);
  }

  public void removeGame(Game game) {
    this.getGameList().remove(game);
  }

  public List<Game> getGameList() {
    return gameList;
  }

  public void setGameList(List<Game> gameList) {
    this.gameList = gameList;
  }
}
