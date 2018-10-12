package games.aternos.odessa.core.team;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayerTeam {

    private List<Player> players;
    private int maxSize;
    private ChatColor color;

    public PlayerTeam(ChatColor color, int maxSize) {
        this.color = color;
        this.maxSize = maxSize;

        this.players = Lists.newArrayList();
    }

    public boolean addPlayer(Player player) {
        Preconditions.checkNotNull(player, "'player' cannot be null");


        //Check if player already is part of this team
        if(players.contains(player))
            return false;

        //Add player to team
        players.add(player);
        return true;
    }

    public boolean removePlayer(Player player) {
        Preconditions.checkNotNull(player, "'player' cannot be null");

        //Remove player from team
        return players.remove(player);
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public ChatColor getColor() {
        return this.color;
    }
}