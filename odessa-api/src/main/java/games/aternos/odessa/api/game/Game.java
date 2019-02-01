package games.aternos.odessa.api.game;

import games.aternos.odessa.api.engine.SecurityManager;
import games.aternos.odessa.api.game.arena.GameArena;
import games.aternos.odessa.api.game.phase.GamePhase;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * The core interface for a Game on Odessa.
 */
public interface Game {

  /**
   * Called at the game initalization. Should start the first phase and the code required to accomplish that.
   */
  void start(Plugin plugin);

  /**
   * Called at the game deinitalization. Should safely remove the game. This can be called at both server shutdown or
   * when the server is recomposed to a different game.
   */
  void stop();

  /**
   * Returns the plugin the game is from.
   */
  Plugin getPlugin();

  SecurityManager getSecurityManager();

  void setSecurityManager(SecurityManager securityManager);

  GameArena getActiveArena();

  void setActiveArena(GameArena gameArena);

  List<GamePhase> getGamePhases();

  void setGamePhases(List<GamePhase> gamePhases);

  GamePhase getActivePhase();

  void setActivePhase(GamePhase gamePhase);

  GameData getGameData();

  void setGameData(GameData gameData);

  List<Listener> getGameListeners();

  void setGameListeners(List<Listener> gameListeners);

  /**
   * Adds and registers a Listener to the game.
   */
  default void addGameListener(Listener gameListener) {
    this.getGameListeners().add(gameListener);
    Bukkit.getPluginManager().registerEvents(gameListener, getPlugin());
  }

  /**
   * Removes and disables a listener from the game.
   */
  default void removeGameListener(Listener gameListener) {
    this.getGameListeners().remove(gameListener);
    HandlerList.unregisterAll(gameListener);
  }

  /**
   * Adds a GamePhase to the game.
   */
  default void addGamePhase(GamePhase gamePhase) {
    getGamePhases().add(gamePhase);
  }

}
