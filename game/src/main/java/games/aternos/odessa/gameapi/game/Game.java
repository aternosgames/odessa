package games.aternos.odessa.gameapi.game;

abstract public class Game {

  private final GameLifecycleManager gameLifecycleManager;
  private final GameData gameData;
  private final GameConfiguration gameConfiguration;

  public Game(GameLifecycleManager gameLifecycleManager, GameData gameData, GameConfiguration gameConfiguration) {
    this.gameLifecycleManager = gameLifecycleManager;
    this.gameData = gameData;
    this.gameConfiguration = gameConfiguration;
  }


  public GameLifecycleManager getGameLifecycleManager() {
    return gameLifecycleManager;
  }

  public GameData getGameData() {
    return gameData;
  }

  public GameConfiguration getGameConfiguration() {
    return gameConfiguration;
  }

  public abstract void initialize();
}
