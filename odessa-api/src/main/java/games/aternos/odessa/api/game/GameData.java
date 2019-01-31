package games.aternos.odessa.api.game;

import games.aternos.odessa.api.game.team.GameTeam;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Handles all of the data about and from a Game.
 */
public interface GameData {

  void getGameName();

  void setGameName(String gameName);

  void getMaxPlayers();

  void setMaxPlayer(Integer maxPlayer);

  List<Player> getGamePlayers();

  void setGamePlayers(List<Player> players);

  List<GameTeam> getGameTeams();

  void setGameTeams(List<GameTeam> gameTeams);

  default void addGameTeam(GameTeam gameTeam) {
    this.getGameTeams().add(gameTeam);
  }

  default void removeGameTeam(GameTeam gameTeam) {
    this.getGameTeams().remove(gameTeam);
  }

  default void addGamePlayer(Player player) {
    this.getGamePlayers().add(player);
  }

  default void removeGamePlayer(Player player) {
    this.getGamePlayers().remove(player);
  }


}
