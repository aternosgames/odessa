package games.aternos.odessa.gameapi.game.element;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class GroupTeam extends Team {

  private final List<Player> teamPlayers;

  public GroupTeam(String teamName, ChatColor teamColor, List<Player> teamPlayers) {
    super(teamName, teamColor);
    this.teamPlayers = teamPlayers;
  }

  public List<Player> getTeamPlayers() {
    return teamPlayers;
  }
}
