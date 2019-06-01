package games.aternos.odessa.gameapi.game.element;

import org.bukkit.Location;

import java.util.HashMap;

public class Arena {
  private final HashMap<Location, Integer> spawnPoints;
  private final String arenaName;
  private final String arenaAuthor;

  public Arena(HashMap<Location, Integer> spawnPoints, String arenaName, String arenaAuthor) {
    this.spawnPoints = spawnPoints;
    this.arenaName = arenaName;
    this.arenaAuthor = arenaAuthor;
  }

  public HashMap<Location, Integer> getSpawnPoints() {
    return spawnPoints;
  }

  public String getArenaName() {
    return arenaName;
  }

  public String getArenaAuthor() {
    return arenaAuthor;
  }
}
