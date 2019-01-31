package games.aternos.odessa.api.game.team;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public interface GameTeam {

    String getTeamName();

    void setTeamName(String s);

    ChatColor getTeamColor();

    void setTeamColor(ChatColor chatColor);

    List<Player> getTeamPlayers();

    void setTeamPlayers(List<Player> players);

    default void addTeamPlayers(Player player) {
        getTeamPlayers().add(player);
    }

    default void removeTeamPlayer(Player player) {
        getTeamPlayers().remove(player);
    }

}
