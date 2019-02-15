package games.aternos.odessa.gameapi.game;

import org.bukkit.entity.Player;

import java.util.List;

/**
 * todo: lots (playerdata objects, scoredata etc)
 */
public interface GameData {

  String gameName();

  List<Player> getPlayers();

  void removePlayer(Player player);

  void addPlayer(Player player);

}
