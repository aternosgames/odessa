package games.aternos.examplegame.teamdeathmatch;

import games.aternos.odessa.api.game.GameData;
import games.aternos.odessa.api.game.team.GameTeam;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TDMData implements GameData {

  String gameName;
  Integer maxPlayers;
  List<Player> gamePlayers;
  List<GameTeam> gameTeams;

  public TDMData() {
    this.gameName = "Team Deathmatch";
    this.maxPlayers = 12;
    this.gamePlayers = new ArrayList<>();
    this.gameTeams = new ArrayList<>();
  }


  @Override
  public void getGameName() {

  }

  @Override
  public void setGameName(String gameName) {

  }

  @Override
  public void getMaxPlayers() {

  }

  @Override
  public void setMaxPlayer(Integer maxPlayer) {

  }

  @Override
  public List<Player> getGamePlayers() {
    return null;
  }

  @Override
  public void setGamePlayers(List<Player> players) {

  }

  @Override
  public List<GameTeam> getGameTeams() {
    return null;
  }

  @Override
  public void setGameTeams(List<GameTeam> gameTeams) {

  }
}
