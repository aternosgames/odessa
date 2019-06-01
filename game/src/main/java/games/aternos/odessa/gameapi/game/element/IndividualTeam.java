package games.aternos.odessa.gameapi.game.element;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class IndividualTeam extends Team {

  private final Player teamPlayer;

  public IndividualTeam(@Nonnull String teamName, ChatColor teamColor, @Nonnull Player teamPlayer) {
    super(teamName, teamColor);
    this.teamPlayer = teamPlayer;
  }

  @Nonnull
  public Player getTeamPlayer() {
    return teamPlayer;
  }
}
