package games.aternos.odessa.api;

import games.aternos.odessa.api.phase.GamePhase;

/**
 * The interface for main game logic.
 */
public interface Game {

    /**
     * Starts the game. Calls {@link GamePhase#startPhase()} on initial game phase.
     */
    public void start();

    /**
     * Switches game phase to {@code nextPhase}.
     * Calls {@link GamePhase#endPhase()} on current and {@link GamePhase#startPhase()} on next phase.
     *
     * @param nextPhase the next phase
     */
    public void advancePhase(GamePhase nextPhase);

    /**
     * Gets current game phase.
     *
     * @return current {@link GamePhase}
     */
    public GamePhase getCurrentPhase();

    /**
     * Gets name.
     *
     * @return the name of the game
     */
    public String getName();

}
