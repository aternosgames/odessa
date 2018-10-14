package games.aternos.odessa.api.team;

import org.bukkit.ChatColor;

/**
 * The interface Team options.
 */
public interface TeamOptions {

    /**
     * Gets the amount of players per team.
     *
     * @return the amount of players per team
     */
    int getPlayerCountPerTeam();

    /**
     * Gets the amount of teams.
     *
     * @return the team count
     */
    int getTeamCount();

    /**
     * Gets max amount of players that could play this game.
     * In most cases this will be playerCountPerTeam * teamCount.
     *
     * @return the max players
     */
    default int getMaxPlayers() {
        return getTeamCount() * getPlayerCountPerTeam();
    }

    /**
     * Get team colors.
     *
     * @return the chat color [ ]
     */
    default ChatColor[] getTeamColors() { return null; }

    /**
     * Returns if friendly fire is allowed inside teams.
     *
     * @return true if friendly fire is allowed
     */
    boolean isFriendlyFire();

    /**
     * Sets friendly fire.
     *
     * @param friendlyFire set to true if friendly fire should be allowed
     */
    void setFriendlyFire(boolean friendlyFire);
}
