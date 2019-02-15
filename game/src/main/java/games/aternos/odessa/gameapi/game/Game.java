package games.aternos.odessa.gameapi.game;

public interface Game {

  void initialize();

  GameLifecycleManager getGameLifecycleManager();
  GameData getGameData();

}
