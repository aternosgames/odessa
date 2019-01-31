package games.aternos.odessa.api.game.phase;

/**
 * A GamePhase is a time period where set parameters, code, and listeners are active in a Game.
 */
public interface GamePhase {

  /**
   * Returns the name of the GamePhase
   */
  String getPhaseName();

  /**
   * Sets the name of the GamePhase.
   */
  void setPhaseName(String s);

  /**
   * Called at the beginning of the game phase..not at the addition of the GamePhase to the Game.
   */
  void start();

  /**
   * Called at the end of the GamePhase, and Safely cleans up the Phase.
   */
  void end();

  /**
   * Returns if the GamePhase is alive.
   */
  boolean isAlive();

  /**
   * Sets whether the GamePhase is alive.
   */
  void setAlive(Boolean alive);

  void update();


}
