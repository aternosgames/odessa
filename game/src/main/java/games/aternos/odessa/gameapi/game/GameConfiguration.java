package games.aternos.odessa.gameapi.game;

import games.aternos.odessa.gameapi.game.element.Arena;
import games.aternos.odessa.gameapi.game.element.Kit;

import java.util.List;

public abstract class GameConfiguration {

  private String gameName;
  private List<Kit> gameKits;
  private List<Arena> gameArenas;
  private int maxPlayers;
  private int minPlayers;

  public List<Arena> getGameArenas() {
    return gameArenas;
  }

  public void setGameArenas(List<Arena> gameArenas) {
    this.gameArenas = gameArenas;
  }

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
