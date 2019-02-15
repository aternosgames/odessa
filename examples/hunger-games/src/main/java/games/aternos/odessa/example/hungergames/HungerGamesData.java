package games.aternos.odessa.example.hungergames;

import games.aternos.odessa.gameapi.game.GameData;
import org.bukkit.entity.Player;

import java.util.List;
// todo: this class
public class HungerGamesData implements GameData {

  @Override
  public String gameName() {
    return "HungerGames";
  }

  @Override
  public List<Player> getPlayers() {
    return null;
  }

  @Override
  public void removePlayer(Player player) {

  }

  @Override
  public void addPlayer(Player player) {

  }
}
