package games.aternos.odessa.api.game.arena;

import games.aternos.odessa.api.location.SpawnPoint;

import java.util.List;

public interface GameArena {
  /**
   * Returns a list of the Arena Spawns using the Playground Spawnpoint Class
   */
  List<SpawnPoint> getArenaSpawnPoints();

  /**
   * Set a list of SpawnPoints using Playerground Spawnpoint Class
   */
  void setArenaSpawnPoints(List<SpawnPoint> spawnPoints);

  default void addArenaSpawnPoint(SpawnPoint spawnPoint) {
    getArenaSpawnPoints().add(spawnPoint);
  }

  default void removeArenaSpawnPoint(SpawnPoint spawnPoint) {
    getArenaSpawnPoints().remove(spawnPoint);
  }
}
