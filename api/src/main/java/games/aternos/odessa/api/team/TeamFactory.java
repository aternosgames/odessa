package games.aternos.odessa.api.team;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * The interface Team factory.
 */
public interface TeamFactory {

    /**
     * Searches team by id
     *
     * @param id the id of the searched team
     * @return the team belonging to the id, null if not existing
     */
    PlayerTeam getTeamById(int id);

    /**
     * Searches team by id
     *
     * @param color the color of the searched team
     * @return the team belonging to the color, null if not existing
     */
    PlayerTeam getTeamByColor(ChatColor color);

    /**
     * Searches team by player.
     *
     * @param player the player that should be searched for
     * @return the team of the player, null if player has no team
     */
    PlayerTeam getTeamByPlayer(Player player);

    /**
     * Gets all available teams.
     *
     * @return the teams
     */
    List<PlayerTeam> getTeams();


    /**
     * Function for adding a team to the team pool.
     *
     * @param color      the color
     * @param maxPlayers the max players
     */
    void createTeam(ChatColor color, int maxPlayers);

    /**
     * Find the smallest team.
     *
     * @return the player team with the fewest players
     */
    PlayerTeam getSmallestTeam();
}