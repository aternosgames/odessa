package games.aternos.odessa.gameapi.game.element;

import org.bukkit.ChatColor;

public abstract class Team {

  private ChatColor teamColor;
  private String teamName;

  public Team(String teamName, ChatColor teamColor) {
    this.teamColor = teamColor;
    this.teamName = teamName;
  }

  public ChatColor getTeamColor() {
    return teamColor;
  }

  public void setTeamColor(ChatColor teamColor) {
    this.teamColor = teamColor;
  }

  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }
}
