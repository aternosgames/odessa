package games.aternos.odessa.gameapi.game;

public interface GamePhase {

  GamePhase nextPhase();

  Runnable gamePhaseRunnable();

  boolean isActive();

  void startPhase();

  void endPhase();

}
