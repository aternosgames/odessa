package games.aternos.odessa.gameapi.game;

import games.aternos.odessa.gameapi.Debug;

import javax.annotation.Nonnull;

/**
 * Manages the Game Lifecycle, sets the current phase and has the ability to continue to the next.
 */
abstract public class GameLifecycleManager {
  private GamePhase activePhase;

  /**
   * Returns the active game phase in the manager.
   */
  public GamePhase getActivePhase() {
    return this.activePhase;
  }

  /**
   * Sets the active game phase in the manager.
   * This also starts the game phase!!
   */
  public void setActivePhase(@Nonnull GamePhase gamePhase) {
    Debug.$("asd");
    this.activePhase = gamePhase;
    this.startActivePhase();
  }

  private void startActivePhase(){
    this.activePhase.startPhase();
  }

  /**
   * Cycles to the next phase as defined in the phase
   * This also starts the game phase!!
   */
  public void nextPhase() {
    this.activePhase.endPhase();
    this.setActivePhase(this.activePhase.getNextPhase());
  }

}
