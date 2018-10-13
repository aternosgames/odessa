package games.aternos.odessa.api;

import games.aternos.odessa.api.chat.ChatFactory;
import games.aternos.odessa.api.phase.GamePhase;
import games.aternos.odessa.api.team.TeamFactory;
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
    void start(Plugin plugin);

    /**
     * End the game.
     */
    void end();

    /**
     * Switches game phase to {@code nextPhase}.
     * Calls {@link GamePhase#endPhase()} on current and {@link GamePhase#startPhase(Game)} on next phase.
     *
     * @param nextPhase the next phase
     */
    void advancePhase(GamePhase nextPhase);

    /**
     * Gets current game phase.
     *
     * @return current {@link GamePhase}
     */
    GamePhase getCurrentPhase();

    /**
     * Gets name.
     *
     * @return the name of the game
     */
   String getName();

    /**
     * Register {@code listener} as long as current game phase is active.
     * <p>
     * Game phase related listeners always should be registered as temporary listener using
     * this method.
     *
     * @param listener the listener containing event handlers
     */
    void registerTempListener(Listener listener);

    /**
     * Gets team factory which is responsible for team relations
     *
     * @return the team factory
     */
    TeamFactory getTeamFactory();

    /**
     * Gets chat factory which is responsible for formatting chat.
     *
     * @return the chat factory
     */
    ChatFactory getChatFactory();
}
