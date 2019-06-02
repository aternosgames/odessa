package games.aternos.odessa.engine.service.player;

import edu.umd.cs.findbugs.annotations.NonNull;
import games.aternos.odessa.engine.service.Service;
import games.aternos.odessa.gameapi.Debug;
import games.aternos.odessa.gameapi.GameApi;
import games.aternos.odessa.gameapi.game.element.Arena;
import games.aternos.odessa.gameapi.game.element.Kit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class PlayerService extends Service {
  public PlayerService(@NonNull GameApi gameApi) {
    super(gameApi);
  }

  /**
   * Cleans Player Gamemodes Player
   *
   * @param p Player
   */
  public void spectatorPlayer(Player p) {
    this.clearPlayer(p);
    this.healPlayer(p);
    p.setGameMode(GameMode.ADVENTURE);
  }

  public void clearPlayer(@NonNull Player p) {
    p.getInventory().setHelmet(null);
    p.getInventory().setChestplate(null);
    p.getInventory().setLeggings(null);
    p.getInventory().setBoots(null);
    p.getInventory().clear();
  }

  public void healPlayer(@NonNull Player p) {
    p.setHealth(20);
    p.setFoodLevel(20);
  }

  /**
   * Disperse players to a set of spawns. Ignores Teamid, for games that each player is on their own
   * team
   */
  public void dispersePlayers(List<Player> players, Arena arena) {

    List<Location> spawns = new ArrayList<>(arena.getSpawnPoints().keySet());

    disperseTeam(players, spawns);
  }

  /**
   * Used to desperse players in maps with their team. For every list of players the teamid will
   * increase from 0.. NOT 1 If there are multi spawns with the same teamid they will be used for
   * that one List of players. ex: List:Player: gets dispersed to all spawns with the ID of 0, etc.
   */
  public void dispersePlayers(Arena arena, List<Player>... teams) {
    int baseTeamId = 0;
    HashMap<Location, Integer> spawns = arena.getSpawnPoints();
    for (List<Player> team : teams) {
      List<Location> teamSpawns = new ArrayList<>();
      for (Location spawn : spawns.keySet()) {
        if (spawns.get(spawn) == baseTeamId) {
          teamSpawns.add(spawn);
        }
      }
      disperseTeam(team, teamSpawns);
      baseTeamId = baseTeamId + 1;
    }
  }

  private void disperseTeam(List<Player> players, List<Location> spawns) {

    int maxIndex = spawns.size() - 1;
    int currentIndex = 0;

    for (Player p : players) {

      if (currentIndex > maxIndex) {
        currentIndex = 0;
      }

      p.teleport(spawns.get(currentIndex));

      currentIndex = currentIndex + 1;
    }
  }

  public void teleportPlayerToRandomFromList(Player p, List<Player> players) {

    Random random = new Random();

    Player teleportTo = players.get(random.nextInt(players.size()));

    p.teleport(teleportTo.getLocation());
  }

  public void giveKit(Player p, Kit k) {
    for (ItemStack i : k.getKitItems()) {
      p.getInventory().addItem(i);
      Debug.$("Gave kit " + k.getKitName() + " to " + p.getName());
    }
  }

  public void giveKitsToPlayers(
          HashMap<Player, Kit> kits, boolean healPlayers, boolean cleanPlayers) {

    for (Player p : kits.keySet()) {

      if (healPlayers) {
        this.healPlayer(p);
      }
      if (cleanPlayers) {
        this.clearPlayer(p);
      }
      this.giveKit(p, kits.get(p));
    }
  }
}
