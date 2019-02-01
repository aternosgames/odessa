package games.aternos.odessa.api.engine;

import games.aternos.odessa.api.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * Interface for the main shared engine Lobby.
 */
public interface Lobbyable {

  /**
   * Starts and internalizes the Lobby.0
   * Requires the game that the lobby will be for. This will help with player slots, kits, etc.
   */
  void startLobby(Game g, Plugin p);

  /**
   * Destroys the lobby
   */
  void destroyLobby();

  Boolean canStartGame();

  /**
   * Returns the plugin.
   */
  Plugin getPlugin();

  /**
   * Returns the selected game the lobby is for.
   */
  Game getSelectedGame();

  /**
   * Returns all of the Lobby Listeners
   */
  List<Listener> getLobbyListeners();

  /**
   * Set all the lobby Listeners;
   */
  void setLobbyListeners(List<Listener> gameListeners);

  /**
   * Adds and registers a Listener to the lobby.
   */
  default void addLobbyListener(Listener lobbyListener) {
    this.getLobbyListeners().add(lobbyListener);
    Bukkit.getPluginManager().registerEvents(lobbyListener, getPlugin());
  }

  /**
   * Removes and disables a listener from the game.
   */
  default void removeLobbyListener(Listener lobbyListener) {
    this.getLobbyListeners().remove(lobbyListener);
    HandlerList.unregisterAll(lobbyListener);
  }

}
