package games.aternos.odessa.api.lifecycle;

import games.aternos.odessa.api.Game;
import org.bukkit.scheduler.BukkitTask;

/**
 * The game lifecycle interface.
 */
public interface GameLifecycle extends Runnable {

    /**
     * Starts the game lifecycle.
     *
     * @param game the game
     */
    void start(Game game);

    /**
     * Stops the game lifecycle.
     */
    void stop();

    /**
     * Gets the bukkit task running the game lifecycle. This is null if {@code start(Game)} was not called yet.
     *
     * @return the bukkit task
     */
    BukkitTask getBukkitTask();

}