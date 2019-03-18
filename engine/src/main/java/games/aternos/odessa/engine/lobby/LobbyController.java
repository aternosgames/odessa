package games.aternos.odessa.engine.lobby;

import games.aternos.odessa.engine.lobby.listener.LobbyPlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class LobbyController {

  private final GameLobbySystem gameLobbySystem;
  private List<Listener> lobbyListeners;

  public LobbyController(@Nonnull GameLobbySystem gameLobbySystem) {
    this.gameLobbySystem = gameLobbySystem;
    this.lobbyListeners = new ArrayList<>();
  }

  public void registerLobbyListeners() {
    LobbyPlayerHandler lobbyPlayerHandler = new LobbyPlayerHandler(this);
    lobbyListeners.add(lobbyPlayerHandler);
    Bukkit.getServer().getPluginManager().registerEvents(lobbyPlayerHandler, this.getGameLobbySystem().getGameApi());
  }

  public void unRegisterLobbyListeners() {
    for (Listener l : this.getLobbyListeners()) {
      HandlerList.unregisterAll(l);
      lobbyListeners.remove(l);
    }
  }

  public GameLobbySystem getGameLobbySystem() {
    return gameLobbySystem;
  }

  public List<Listener> getLobbyListeners() {
    return lobbyListeners;
  }

  public void setLobbyListeners(@Nonnull List<Listener> lobbyListeners) {
    this.lobbyListeners = lobbyListeners;
  }
}
