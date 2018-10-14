package games.aternos.odessa.api;

import games.aternos.odessa.api.Game;

/**
 * The game lifecycle interface.
 */
public interface GameLifecycle extends Runnable {

    /**
     * Starts the game lifecycle.
     */
    void start();

    /**
     * Stops the game lifecycle.
     */
    void stop();

}
