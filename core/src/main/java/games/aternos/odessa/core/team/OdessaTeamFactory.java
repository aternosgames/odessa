package games.aternos.odessa.core.team;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import games.aternos.odessa.api.team.PlayerTeam;
import games.aternos.odessa.api.team.TeamFactory;
import games.aternos.odessa.api.team.TeamOptions;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * This is the default odessa team factory.
 * This factory will create teams automatically on instantiation.
 */
public class OdessaTeamFactory implements TeamFactory {

    private TeamOptions teamOptions;
    private List<PlayerTeam> teams;
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
    public PlayerTeam getTeamById(int id) {
        Optional<PlayerTeam> optionalTeam = teams.stream().filter(team -> team.getId() == id).findAny();
        return optionalTeam.orElse(null);
    }

    @Override
    public PlayerTeam getTeamByColor(ChatColor color) {
        Optional<PlayerTeam> optionalTeam = teams.stream().filter(team -> team.getColor().equals(color)).findAny();
        return optionalTeam.orElse(null);
    }

    @Override
    public PlayerTeam getTeamByPlayer(Player player) {
        Optional<PlayerTeam> optionalTeam = teams.stream().filter(team -> team.getPlayers().contains(player)).findAny();
        return optionalTeam.orElse(null);
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

    @Override
    public PlayerTeam getSmallestTeam() {
        Comparator<PlayerTeam> comparator = Comparator.comparing( PlayerTeam::size );
        return teams.stream().min(comparator).get();
    }

    @Override
    public List<PlayerTeam> getTeams() {
        return this.teams;
    }

    @Override
    public TeamOptions getOptions() {
        return this.teamOptions;
    }

    public static class TeamFactoryBuilder {

        private int playerCountPerTeam;
        private int teamCount;
        private boolean friendlyFire;
        private List<ChatColor> teamColors = Lists.newArrayList();

        public TeamFactoryBuilder setPlayerCountPerTeam(int playerCountPerTeam) {
            this.playerCountPerTeam = playerCountPerTeam;
            return this;
        }

        public TeamFactoryBuilder setTeamCount(int teamCount) {
            this.teamCount = teamCount;
            return this;
        }

        public TeamFactoryBuilder setFriendlyFire(boolean friendlyFire) {
            this.friendlyFire = friendlyFire;
            return this;
        }

        public TeamFactoryBuilder addTeamColor(ChatColor color) {
            this.teamColors.add(color);
            return this;
        }

        public TeamFactoryBuilder setTeamColors(ChatColor[] teamColors) {
            this.teamColors.addAll(Arrays.asList(teamColors));
            return this;
        }

        public OdessaTeamFactory build() {
            ChatColor[] colors = new ChatColor[this.teamColors.size()];
            colors = this.teamColors.toArray(colors);

            return new OdessaTeamFactory(new OdessaTeamOptions(this.playerCountPerTeam, this.teamCount, this.friendlyFire, colors));
        }
    }
}

