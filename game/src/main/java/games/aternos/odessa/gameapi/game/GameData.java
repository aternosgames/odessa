package games.aternos.odessa.gameapi.game;

import games.aternos.odessa.gameapi.game.element.Arena;
import games.aternos.odessa.gameapi.game.element.Kit;
import games.aternos.odessa.gameapi.game.element.Team;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

abstract public class GameData {

  private final List<Player> players;

  private final HashMap<Player, Kit> selectedPlayerKits;

  private final List<Team> gameTeams;

  private Arena gameArena;

  protected GameData() {
    this.players = new ArrayList<>();
    this.selectedPlayerKits = new HashMap<>();
    this.gameTeams = new ArrayList<>();
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

  public void setGameArena(Arena a){
    this.gameArena = a;
  }

  public Arena getGameArena(){
    return this.gameArena;
  }

}
