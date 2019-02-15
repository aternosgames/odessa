package games.aternos.odessa.gameapi.game;

import edu.umd.cs.findbugs.annotations.NonNull;

abstract public class GamePhase {

  private GameLifecycleManager owner;

  private GamePhase nextPhase;

  private Runnable gamePhaseRunnable;

  private boolean isActive;

  public GamePhase(@NonNull GameLifecycleManager owner) {
    this.owner = owner;
    this.initialize();
  }

  abstract public void initialize();

  public GamePhase getNextPhase(){
    return this.nextPhase;
  }

  public void setNextPhase(GamePhase nextPhase) {
    this.nextPhase = nextPhase;
  }

  public void setGamePhaseRunnable(Runnable gamePhaseRunnable) {
    this.gamePhaseRunnable = gamePhaseRunnable;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public Runnable getGamePhaseRunnable(){
    return gamePhaseRunnable;
  }

  public boolean isActive(){
    return this.isActive;
  }

  abstract public void startPhase();

  abstract public void endPhase();


  public GameLifecycleManager getOwner() {
    return owner;
  }
}
