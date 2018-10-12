package games.aternos.odessa.api.phase;

import games.aternos.odessa.api.Game;

/**
 * The interface for game phases.
 */
public interface GamePhase {

    /**
     * Code that runs at the end of the phase.
     */
    void endPhase();

    /**
     * Code that runs at the start of the phase.
     * This will run before first call of {@code update()}.
     *
     * @param game the game object using this game phrase
     */
    void startPhase(Game game);

    /**
     * This method gets called every second by {@code game}. It should keep the game alive
     * and is responsible for changing of phases.
     */
    void update();
}
