package games.aternos.odessa.core.spawn;

import games.aternos.odessa.api.spawn.SpawnStrategy;
import games.aternos.playground.location.Locations;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Fixed point spawn strategy. Will spawn all players around the same location (+-5 blocks in x/z direction)
 */
public class FixedPointSpawnStrategy implements SpawnStrategy {

    private Location location;

    /**
     * Instantiates a new Fixed point spawn strategy.
     *
     * @param location the location
     */
    public FixedPointSpawnStrategy(Location location) {
        this.location = location;
    }

    @Override
    public void spawnPlayers(List<Player> playerList) {
        playerList.forEach(player -> player.teleport(Locations.spread(location, 5, true)));
    }
}
