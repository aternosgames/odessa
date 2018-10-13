package games.aternos.odessa.core.spawn.player;

import com.google.common.base.Preconditions;

import games.aternos.odessa.api.spawn.PlayerSpawnStrategy;
import games.aternos.odessa.api.team.PlayerTeam;
import games.aternos.odessa.api.team.TeamFactory;
import games.aternos.playground.location.Locations;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Map;

/**
 * TeamBase spawn strategy. Will spawn all players of one team at a given location.
 */
public class TeamBaseSpawnStrategy implements PlayerSpawnStrategy {

    private TeamFactory teamFactory;
    private Map<Integer, Location> teamSpawns;

    /**
     * Instantiates a new team base spawn strategy.
     *
     * @param teamFactory the team factory containing the teams
     * @param teamSpawns  the team spawns - map containing location for every team id.
     */
    public TeamBaseSpawnStrategy(TeamFactory teamFactory, Map<Integer, Location> teamSpawns) {
        this.teamFactory = teamFactory;
        this.teamSpawns = teamSpawns;
    }

    @Override
    public void spawn(Collection<Player> players) {
        players.forEach(player -> {
            //Get players team
            PlayerTeam team = teamFactory.getTeamByPlayer(player);
            //Get spawn location for team and randomize it, so players dont spawn on the same spot
            Preconditions.checkState(teamSpawns.containsKey(team.getId()), "team has no defined spawn location");
            Location playerSpawn = Locations.spread(teamSpawns.get(team.getId()), 2, true);
            //Teleport player
            player.teleport(playerSpawn);
        });
    }
}
