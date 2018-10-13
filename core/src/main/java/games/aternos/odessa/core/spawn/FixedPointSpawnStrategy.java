package games.aternos.odessa.core.spawn;

import games.aternos.odessa.api.spawn.PlayerSpawnStrategy;
import games.aternos.playground.location.Locations;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Collection;

/**
 * Fixed point spawn strategy. Will spawn all players around the same location (+-5 blocks in x/z direction)
 */
public class FixedPointSpawnStrategy implements PlayerSpawnStrategy {

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
    public void spawn(Collection<Player> players) {
        players.forEach(player -> player.teleport(Locations.spread(location, 5, true)));
    }
}
