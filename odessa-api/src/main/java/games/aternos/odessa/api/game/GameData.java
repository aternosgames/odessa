package games.aternos.odessa.api.game;

import games.aternos.odessa.api.game.team.GameTeam;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Handles all of the data about and from a Game.
 */
public interface GameData {

  String getGameName();

  void setGameName(String gameName);

  /**
   * Returns the abbreviated game name. Should have no spaces such as: TDM, KOTH, CTF, etc. Used for commands such as
   * /game set -name-, hence why it is important to not have spaces for top functionality and internal use.
   */
  String getAbbreviatedGameName();

  /**
   * Sets the abbreviated game name. Should have no spaces such as: TDM, KOTH, CTF, etc. Used for commands such as
   * /game set -name-, hence why it is important to not have spaces for top functionality and internal use.
   */
  void setAbbreviatedGameName(String abbreviatedGameName);

  Integer getMaxPlayers();

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
