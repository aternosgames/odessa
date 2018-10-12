package games.aternos.odessa.core.team;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import games.aternos.odessa.api.Game;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * The type Team factory.
 */
public class TeamFactory {

    private Game game;
    private TeamOptions teamOptions;
    private List<PlayerTeam> teams;

    /**
     * Instantiates a new team factory using the given {@code teamOptions}.
     *
     * @param game        the game
     * @param teamOptions the team options
     */
    public TeamFactory(Game game, TeamOptions teamOptions) {
        Preconditions.checkNotNull(game, "'game' cannot be null");
        Preconditions.checkNotNull(teamOptions, "'teamOptions' cannot be null");

        this.game = game;
        this.teamOptions = teamOptions;

        createTeams();
    }

    /**
     * Creates empty teams based on {@code teamCount} and {@code playerCountPerTeam}.
     */
    private void createTeams() {
        this.teams = Lists.newArrayList();

        //Create empty teams
        for(int i = 0; i < teamOptions.getTeamCount(); i++) {
            ChatColor teamColor = teamOptions.getTeamColors() == null ? null : teamOptions.getTeamColors()[i];
            teams.add(new PlayerTeam(i, teamColor, teamOptions.getPlayerCountPerTeam()));
        }
    }

    /**
     * Searches team by id
     *
     * @param id the id of the searched team
     * @return the team belonging to the id, null if not existing
     */
    public PlayerTeam getTeamById(int id) {
        Optional<PlayerTeam> optionalTeam = teams.stream().filter(team -> team.getId() == id).findAny();
        return optionalTeam.isPresent() ? optionalTeam.get() : null;
    }

    /**
     * Searches team by id
     *
     * @param color the color of the searched team
     * @return the team belonging to the color, null if not existing
     */
    public PlayerTeam getTeamByColor(ChatColor color) {
        Optional<PlayerTeam> optionalTeam = teams.stream().filter(team -> team.getColor().equals(color)).findAny();
        return optionalTeam.isPresent() ? optionalTeam.get() : null;
    }

    /**
     * Searches team by player.
     *
     * @param player the player that should be searched for
     * @return the team of the player, null if player has no team
     */
    public PlayerTeam getTeamByPlayer(Player player) {
        Optional<PlayerTeam> optionalTeam = teams.stream().filter(team -> team.getPlayers().contains(player)).findAny();
        return optionalTeam.isPresent() ? optionalTeam.get() : null;
    }
}
