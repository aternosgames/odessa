package games.aternos.examplegame.teamdeathmatch;

import games.aternos.odessa.api.game.Game;
import games.aternos.odessa.api.game.GameData;
import games.aternos.odessa.api.game.arena.GameArena;
import games.aternos.odessa.api.game.phase.GamePhase;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class TDMGame implements Game {

  Plugin plugin;
  GameArena activeArena;
  List<GamePhase> gamePhases;
  GamePhase activePhase;
  GameData gameData;
  List<Listener> gameListeners;


  @Override
  public void start(Plugin plugin) {
    this.plugin = plugin;
    this.gamePhases = new ArrayList<>();
    gameListeners = new ArrayList<>();
    gameData = new TDMData();
  }

  @Override
  public void stop() {

  }

  @Override
  public Plugin getPlugin() {
    return null;
  }

  @Override
  public GameArena getActiveArena() {
    return null;
  }

  @Override
  public void setActiveArena(GameArena gameArena) {

  }

  @Override
  public List<GamePhase> getGamePhases() {
    return null;
  }

  @Override
  public void setGamePhases(List<GamePhase> gamePhases) {

  }

  @Override
  public GamePhase getActivePhase() {
    return null;
  }

  @Override
  public void setActivePhase() {

  }

  @Override
  public GameData getGameData() {
    return null;
  }

  @Override
  public void setGameData(GameData gameData) {

  }

  @Override
  public List<Listener> getGameListeners() {
    return null;
  }

  @Override
  public void setGameListeners(List<Listener> gameListeners) {

  }
}
