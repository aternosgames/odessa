package games.aternos.odessa.api.location;

import org.bukkit.Location;

public class SpawnPoint {
    private Integer spawnId;
    private Location spawnLocation;

    public SpawnPoint(Integer spawnId, Location spawnLocation) {
        this.spawnId = spawnId;
        this.spawnLocation = spawnLocation;
    }

    public Integer getSpawnId() {
        return spawnId;
    }

    public void setSpawnId(Integer spawnId) {
        this.spawnId = spawnId;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }
}
