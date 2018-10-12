package games.aternos.odessa.core.team;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import games.aternos.odessa.api.Game;
import games.aternos.odessa.api.team.TeamFactory;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;

/**
 * This is the default odessa team factory.
 * This factory will create teams automatically on instantiation.
 */
public class OdessaTeamFactory implements TeamFactory {

    private TeamOptions teamOptions;
    private List<OdessaPlayerTeam> teams;

    /**
     * Instantiates a new team factory using the given {@code teamOptions}.
     *
     * @param teamOptions the team options
     */
    public OdessaTeamFactory(TeamOptions teamOptions) {
        Preconditions.checkNotNull(teamOptions, "'teamOptions' cannot be null");

        this.teamOptions = teamOptions;

        createTeams();
    }

    /**
     * OdessaTeamFactory will create teams automatically on instantiation.
     * Creates empty teams based on {@code teamCount} and {@code playerCountPerTeam}.
     */
    private void createTeams() {
        this.teams = Lists.newArrayList();

        //Create empty teams
        for(int i = 0; i < teamOptions.getTeamCount(); i++) {
            ChatColor teamColor = teamOptions.getTeamColors() == null ? null : teamOptions.getTeamColors()[i];
            createTeam(teamColor, teamOptions.getPlayerCountPerTeam());
        }
    }

    @Override
    public OdessaPlayerTeam getTeamById(int id) {
        Optional<OdessaPlayerTeam> optionalTeam = teams.stream().filter(team -> team.getId() == id).findAny();
        return optionalTeam.isPresent() ? optionalTeam.get() : null;
    }

    @Override
    public OdessaPlayerTeam getTeamByColor(ChatColor color) {
        Optional<OdessaPlayerTeam> optionalTeam = teams.stream().filter(team -> team.getColor().equals(color)).findAny();
        return optionalTeam.isPresent() ? optionalTeam.get() : null;
    }

    @Override
    public OdessaPlayerTeam getTeamByPlayer(Player player) {
        Optional<OdessaPlayerTeam> optionalTeam = teams.stream().filter(team -> team.getPlayers().contains(player)).findAny();
        return optionalTeam.isPresent() ? optionalTeam.get() : null;
    }

    @Override
    public void createTeam(ChatColor color, int maxPlayers) {
        //Obtain team id
        int id = teams.size();
        //Create Team
        OdessaPlayerTeam team = new OdessaPlayerTeam(id, color, maxPlayers);
        //Add team to teams list
        teams.add(team);
    }

    public static TeamFactory build(TeamOptions options) {
        return new OdessaTeamFactory(options);
    }
}

