package games.aternos.odessa.gameapi.game;

import java.util.List;

/**
 * Manages the Game Lifecycle, sets the current phase and has the ability to continue to the next.
 */
abstract public class GameLifecycleManager {
  GamePhase activePhase;

  /**
   * Returns the active game phase in the manager.
   */
  public GamePhase getActivePhase(){
    return this.activePhase;
  }

  /**
   * Sets the active game phase in the manager.
   * This also starts the game phase!!
   */
  public void setActivePhase(GamePhase gamePhase){
    this.activePhase = gamePhase;
    this.activePhase.startPhase();
  }

  /**
   * Cycles to the next phase as defined in the phase
   * This also starts the game phase!!
   */
  public void nextPhase(){
   this.activePhase.endPhase();
   activePhase = this.activePhase.getNextPhase();
  }



}
