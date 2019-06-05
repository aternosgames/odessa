package games.aternos.odessa.gameapi.game;

import games.aternos.odessa.gameapi.game.element.Arena;
import games.aternos.odessa.gameapi.game.element.Kit;
import games.aternos.odessa.gameapi.game.element.Team;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class GameData {

  private final List<Player> players;

  private final List<Player> spectators;

  private final HashMap<Player, Kit> selectedPlayerKits;

  private final List<Team> gameTeams;

  private Arena gameArena;

  protected GameData() {
    this.players = new ArrayList<>();
    this.selectedPlayerKits = new HashMap<>();
    this.gameTeams = new ArrayList<>();
    this.spectators = new ArrayList<>();
  }

  public GamePlayerType getGamePlayerType(Player player) {
    for (Player p : this.players) {
      if (player.equals(p)) {
        return GamePlayerType.PLAYER;
      }
    }
    for (Player p : this.spectators) {
      if (player.equals(p)) {
        return GamePlayerType.SPECTATOR;
      }
    }
    return null;
  }

  public void addSpectator(Player p) {
    this.spectators.add(p);
  }

  public HashMap<Player, Kit> getSelectedPlayerKits() {
    return selectedPlayerKits;
  }

  public List<Team> getGameTeams() {
    return gameTeams;
  }

  public List<Player> getPlayers() {
    return this.players;
  }

  public void removePlayer(@Nonnull Player player) {
    this.players.remove(player);
  }

  public void addPlayer(@Nonnull Player player) {
    this.players.add(player);
  }

  public List<Player> getSpectators() {
    return spectators;
  }

  public Arena getGameArena() {
    return this.gameArena;
  }

  public void setGameArena(Arena a) {
    this.gameArena = a;
  }

    public List<Player> getPlayersAndSpectatorsList() {

        List<Player> all = new ArrayList<>();
        all.addAll(this.players);
        all.addAll(this.spectators);
        return all;

    }
}
