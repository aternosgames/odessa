package games.aternos.odessa.api.team;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * The interface Player team.
 */
public interface PlayerTeam {

    /**
     * Gets team id.
     *
     * @return the id
     */
    int getId();

    /**
     * Gets team color.
     *
     * @return the color
     */
    ChatColor getColor();

    /**
     * Adds player to this team.
     *
     * @param player the player that should be added
     * @return returns true if player was added, returns false if team is full or already contains player
     */
    boolean addPlayer(Player player);

    /**
     * Removes player from this team.
     *
     * @param player the player that should be removed
     * @return returns true if player was removed
     */
    boolean removePlayer(Player player);

    /**
     * Gets players.
     *
     * @return the players
     */
    List<Player> getPlayers();

    /**
     * Gets current size.
     *
     * @return the amount of players inside this team
     */
    int getSize();
}
