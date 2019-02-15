package games.aternos.odessa.gameapi.game;

public interface Game {

  void initialize();

  void uninitialize();

  GameLifecycleManager getGameLifecycleManager();

  GameData getGameData();

  GameConfiguration getGameConfiguration();

}
