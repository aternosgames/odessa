package games.aternos.examplegame.teamdeathmatch;

import games.aternos.odessa.api.engine.SecurityManager;
import games.aternos.odessa.api.game.Game;
import games.aternos.odessa.api.game.GameData;
import games.aternos.odessa.api.game.arena.GameArena;
import games.aternos.odessa.api.game.phase.GamePhase;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class TDMGame implements Game {

  private Plugin plugin;
  private GameArena activeArena;
  private List<GamePhase> gamePhases;
  private GamePhase activePhase;
  private GameData gameData;
  private List<Listener> gameListeners;
  private SecurityManager securityManager;

  TDMGame() {
    gameData = new TDMData();
  }

  @Override
  public void start(Plugin plugin) {
    this.plugin = plugin;
    this.gamePhases = new ArrayList<>();
    gameListeners = new ArrayList<>();

    securityManager = new TDMSecurityManager();
  }

  @Override
  public void stop() {
    this.plugin = null;
    this.gamePhases = null;
    gameListeners = null;
    gameData = null;
  }

  @Override
  public Plugin getPlugin() {
    return this.plugin;
  }

  @Override
  public SecurityManager getSecurityManager() {
    return securityManager;
  }

  @Override
  public void setSecurityManager(SecurityManager securityManager) {
    this.securityManager = securityManager;
  }

  @Override
  public GameArena getActiveArena() {
    return this.activeArena;
  }

  @Override
  public void setActiveArena(GameArena gameArena) {
    this.activeArena = gameArena;
  }

  @Override
  public List<GamePhase> getGamePhases() {
    return this.gamePhases;
  }

  @Override
  public void setGamePhases(List<GamePhase> gamePhases) {
    this.gamePhases = gamePhases;
  }

  @Override
  public GamePhase getActivePhase() {
    return this.activePhase;
  }

  @Override
  public void setActivePhase(GamePhase gamePhase) {
    this.activePhase = gamePhase;
  }

  @Override
  public GameData getGameData() {
    return this.gameData;
  }

  @Override
  public void setGameData(GameData gameData) {
    this.gameData = gameData;
  }

  @Override
  public List<Listener> getGameListeners() {
    return this.gameListeners;
  }

  @Override
  public void setGameListeners(List<Listener> gameListeners) {
    this.gameListeners = gameListeners;
  }
}
