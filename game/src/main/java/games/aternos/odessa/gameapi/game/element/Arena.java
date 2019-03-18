package games.aternos.odessa.gameapi.game.element;

import javax.xml.stream.Location;
import java.util.HashMap;

public class Arena {
  private final HashMap<Location, String> spawnPoints;
  private final String arenaName;
  private final String arenaAuthor;

  public Arena(HashMap<Location, String> spawnPoints, String arenaName, String arenaAuthor) {
    this.spawnPoints = spawnPoints;
    this.arenaName = arenaName;
    this.arenaAuthor = arenaAuthor;
  }


  public HashMap<Location, String> getSpawnPoints() {
    return spawnPoints;
  }

  public String getArenaName() {
    return arenaName;
  }

  public String getArenaAuthor() {
    return arenaAuthor;
  }
}
