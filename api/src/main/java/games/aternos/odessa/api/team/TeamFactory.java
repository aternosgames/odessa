package games.aternos.odessa.api.team;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

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
     * Function for adding a team to the team pool.
     *
     * @param color
     * @param maxPlayers
     */
    void createTeam(ChatColor color, int maxPlayers);

}