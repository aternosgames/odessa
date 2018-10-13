package games.aternos.odessa.api.chat;

import org.bukkit.entity.Player;

/**
 * The chat factory format.
 */
public interface ChatFactory {

    /**
     * Gets player join message.
     *
     * @param player the player - the string that identifies the joined player
     * @return the formatted player join message
     */
    String getPlayerJoinMessage(String player);

    /**
     * Gets countdown message.
     *
     * @param secondsLeft the seconds left
     * @return the countdown message
     */
    String getCountdownMessage(int secondsLeft);

    /**
     * Gets game start message.
     *
     * @return the game start message
     */
    String getGameStartMessage();

    /**
     * Gets game explanation text. Should be less than 300 letters.
     *
     * @return the game explanation message
     */
    String getGameExplanationMessage();

    /**
     * Gets formatted player name.
     *
     * @param player the player
     * @return the formatted player name
     */
    String getFormattedPlayerName(Player player);
}
