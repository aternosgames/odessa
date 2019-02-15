package games.aternos.odessa.gameapi.game;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * todo: lots (playerdata objects, scoredata etc)
 */
abstract public class GameData {

  private List<Player> players;

  public GameData(){
    players = new ArrayList<>();
  }

  abstract public String gameName();

  public List<Player> getPlayers(){
    return this.players;
  }

  public void removePlayer(Player player){
    this.players.remove(player);
  }

  public void addPlayer(Player player){
    this.players.add(player);
  }

}
