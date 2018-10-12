package games.aternos.odessa.core.team;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * The type Player team.
 */
public class PlayerTeam {

    private int id;
    private List<Player> players;
    private int maxSize;
    private ChatColor color;

    /**
     * Instantiates a new Player team.
     *
     * @param id      the id
     * @param color   the color
     * @param maxSize the max size
     */
    public PlayerTeam(int id, ChatColor color, int maxSize) {
        this.id = id;
        this.color = color;
        this.maxSize = maxSize;

        this.players = Lists.newArrayList();
    }

    /**
     * Gets team id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Adds player to this team.
     *
     * @param player the player that should be added
     * @return returns true if player was added, returns false if team is full or already contains player
     */
    public boolean addPlayer(Player player) {
        Preconditions.checkNotNull(player, "'player' cannot be null");
        if(players.size() >= maxSize)
            return false;

        //Check if player already is part of this team
        if(players.contains(player))
            return false;

        //Add player to team
        players.add(player);
        return true;
    }

    /**
     * Removes player from this team.
     *
     * @param player the player that should be removed
     * @return returns true if player was removed
     */
    public boolean removePlayer(Player player) {
        Preconditions.checkNotNull(player, "'player' cannot be null");

        //Remove player from team
        return players.remove(player);
    }

    /**
     * Gets players.
     *
     * @return the players
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public ChatColor getColor() {
        return this.color;
    }
}