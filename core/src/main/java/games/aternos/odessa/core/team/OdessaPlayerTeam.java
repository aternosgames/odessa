package games.aternos.odessa.core.team;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import games.aternos.odessa.api.team.PlayerTeam;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * This is the default odessa player team.
 */
public class OdessaPlayerTeam implements PlayerTeam {

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
    public OdessaPlayerTeam(int id, ChatColor color, int maxSize) {
        this.id = id;
        this.color = color;
        this.maxSize = maxSize;

        this.players = Lists.newArrayList();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
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

    @Override
    public boolean removePlayer(Player player) {
        Preconditions.checkNotNull(player, "'player' cannot be null");

        //Remove player from team
        return players.remove(player);
    }

    @Override
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