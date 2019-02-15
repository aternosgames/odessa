package games.aternos.odessa.gameapi.game;

import games.aternos.odessa.gameapi.game.element.Kit;

import java.util.List;

abstract public class GameConfiguration {

  private String gameName;
  private List<Kit> gameKits;
  private int maxPlayers;
  private int minPlayers;

  public String getGameName() {
    return gameName;
  }

  public void setGameName(String gameName) {
    this.gameName = gameName;
  }

  public List<Kit> getGameKits() {
    return gameKits;
  }

  public void setGameKits(List<Kit> gameKits) {
    this.gameKits = gameKits;
  }

  public int getMaxPlayers() {
    return maxPlayers;
  }

  public void setMaxPlayers(int maxPlayers) {
    this.maxPlayers = maxPlayers;
  }

  public int getMinPlayers() {
    return minPlayers;
  }

  public void setMinPlayers(int minPlayers) {
    this.minPlayers = minPlayers;
  }
}
