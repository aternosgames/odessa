package games.aternos.odessa.core.engine.lobby;

import games.aternos.odessa.api.engine.Lobbyable;
import games.aternos.odessa.api.game.Game;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Create Listeners for the lobby..lobby arena etc.
 */
public class GameLobby implements Lobbyable {

  private Game game;
  private Plugin plugin;
  private List<Listener> lobbyListeners;


  @Override
  public void startLobby(Game game, Plugin plugin) {
    this.game = game;
    this.plugin = plugin;
    this.lobbyListeners = new ArrayList<>();
  }

  @Override
  public void destroyLobby() {
    for (Listener l : this.getLobbyListeners()) {
      this.removeLobbyListener(l);
    }
  }

  @Override
  public Boolean canStartGame() {
    // TODO: can start?
    return false;
  }

  @Override
  public Plugin getPlugin() {
    return this.plugin;
  }

  @Override
  public Game getSelectedGame() {
    return this.game;
  }

  @Override
  public List<Listener> getLobbyListeners() {
    return this.lobbyListeners;
  }

  @Override
  public void setLobbyListeners(List<Listener> lobbyListeners) {
    this.lobbyListeners = lobbyListeners;
  }
}
