package games.aternos.examplegame.teamdeathmatch;

import games.aternos.odessa.api.game.GameData;
import games.aternos.odessa.api.game.team.GameTeam;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TDMData implements GameData {

  private String gameName;
  private Integer maxPlayers;
  private List<Player> gamePlayers;
  private List<GameTeam> gameTeams;

  public TDMData() {
    this.gameName = "Team Deathmatch";
    this.maxPlayers = 12;
    this.gamePlayers = new ArrayList<>();
    this.gameTeams = new ArrayList<>();
  }


  @Override
  public String getGameName() {
    return this.gameName;
  }

  @Override
  public void setGameName(String gameName) {
    this.gameName = gameName;
  }

  @Override
  public Integer getMaxPlayers() {
    return this.maxPlayers;
  }

  @Override
  public void setMaxPlayer(Integer maxPlayer) {
    this.maxPlayers = maxPlayer;
  }

  @Override
  public List<Player> getGamePlayers() {
    return this.gamePlayers;
  }

  @Override
  public void setGamePlayers(List<Player> players) {
    this.gamePlayers = players;
  }

  @Override
  public List<GameTeam> getGameTeams() {
    return this.gameTeams;
  }

  @Override
  public void setGameTeams(List<GameTeam> gameTeams) {
    this.gameTeams = gameTeams;
  }
}
