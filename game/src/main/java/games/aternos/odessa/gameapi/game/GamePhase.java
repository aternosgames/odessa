package games.aternos.odessa.gameapi.game;

public interface GamePhase {

  Runnable gamePhaseRunnable();

  boolean isActive();

  void startPhase();

  void endPhase();

}
