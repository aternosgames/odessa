package games.aternos.odessa.core.team;

import com.google.common.base.Preconditions;
import org.bukkit.ChatColor;

/**
 * This class saves options about team settings.
 */
public class TeamOptions {

    private int playerCountPerTeam;
    private int teamCount;
    private boolean friendlyFire;
    private ChatColor[] teamColors;

    /**
     * Instantiates a new PlayerTeam options.
     *
     * If no teams are needed, {@code playerCountPerTeam} should be 1 and {@code teamCount} should be the
     * maximum amount of players.
     *
     * @param playerCountPerTeam the player count per team, has to be > 0
     * @param teamCount          the team count, has to be > 1
     * @param friendlyFire       the friendly fire
     * @param teamColors         the team colors, set to null for no team colors
     */
    public TeamOptions(int playerCountPerTeam, int teamCount, boolean friendlyFire, ChatColor[] teamColors) {
        Preconditions.checkArgument(playerCountPerTeam > 0, "'teamSize' cannot be <= 0");
        Preconditions.checkArgument(teamCount > 1, "'teamSize' cannot be <= 1");
        Preconditions.checkArgument(teamColors != null && teamColors.length >= teamCount, "'teamColors' does not contain enough colors");

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

    /**
     * Gets the amount of players per team.
     *
     * @return the amount of players per team
     */
    public int getPlayerCountPerTeam() {
        return this.playerCountPerTeam;
    }

    /**
     * Gets the amount of teams.
     *
     * @return the team count
     */
    public int getTeamCount() {
        return this.teamCount;
    }

    /**
     * Returns if friendly fire is allowed.
     *
     * @return true if friendly fire is allowed
     */
    public boolean isFriendlyFire() {
        return friendlyFire;
    }

    /**
     * Sets friendly fire.
     *
     * @param friendlyFire set to true if friendly fire should be allowed
     */
    public void setFriendlyFire(boolean friendlyFire) {
        this.friendlyFire = friendlyFire;
    }
}
