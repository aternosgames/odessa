package games.aternos.odessa.core.team;

import com.google.common.base.Preconditions;
import org.bukkit.ChatColor;

/**
 * This class is odessas default implementation of TeamOptions.
 */
public class OdessaTeamOptions implements games.aternos.odessa.api.team.TeamOptions {

    private int playerCountPerTeam;
    private int teamCount;
    private boolean friendlyFire;
    private ChatColor[] teamColors;

    /**
     * Instantiates new team options.
     *
     * If no teams are needed, {@code playerCountPerTeam} should be 1 and {@code teamCount} should be the
     * maximum amount of players.
     *
     * @param playerCountPerTeam the player count per team, has to be > 0
     * @param teamCount          the team count, has to be > 1
     * @param friendlyFire       the friendly fire
     * @param teamColors         the team colors, set to null for no team colors
     */
    public OdessaTeamOptions(int playerCountPerTeam, int teamCount, boolean friendlyFire, ChatColor[] teamColors) {
        Preconditions.checkArgument(playerCountPerTeam > 0, "'teamSize' cannot be <= 0");
        Preconditions.checkArgument(teamCount > 1, "'teamSize' cannot be <= 1");
        Preconditions.checkArgument(teamColors == null ? true : teamColors.length >= teamCount, "'teamColors' does not contain enough colors");

        this.playerCountPerTeam = playerCountPerTeam;
        this.teamCount = teamCount;
        this.friendlyFire = friendlyFire;
        this.teamColors = teamColors;
    }

    /**
     * Get a list of colors for the different teams.
     *
     * @return a list of colors, may be null
     */
    public ChatColor[] getTeamColors() {
        return this.teamColors;
    }

    @Override
    public int getPlayerCountPerTeam() {
        return this.playerCountPerTeam;
    }

    @Override
    public int getTeamCount() {
        return this.teamCount;
    }

    @Override
    public boolean isFriendlyFire() {
        return friendlyFire;
    }

    @Override
    public void setFriendlyFire(boolean friendlyFire) {
        this.friendlyFire = friendlyFire;
    }
}
