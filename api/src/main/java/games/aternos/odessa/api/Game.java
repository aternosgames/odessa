package games.aternos.odessa.api;

import games.aternos.odessa.api.phase.GamePhase;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

/**
 * The interface for main game logic.
 */
public interface Game {

    /**
     * Starts the game. Calls {@link GamePhase#startPhase(Game)} on initial game phase.
     *
     * @param plugin the plugin
     */
    public void start(Plugin plugin);

    /**
     * End the game.
     */
    public void end();

    /**
     * Switches game phase to {@code nextPhase}.
     * Calls {@link GamePhase#endPhase()} on current and {@link GamePhase#startPhase(Game)} on next phase.
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

    /**
     * Register {@code listener} as long as current game phase is active.
     *
     * Game phase related listeners always should be registered as temporary listener using
     * this method.
     *
     * @param listener the listener containing event handlers
     */
    public void registerTempListener(Listener listener);

}
