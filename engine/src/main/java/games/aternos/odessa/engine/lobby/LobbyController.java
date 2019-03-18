package games.aternos.odessa.engine.lobby;

import games.aternos.odessa.engine.lobby.command.SetLobbyLocationCommand;
import games.aternos.odessa.engine.lobby.handler.LobbyPlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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

  public void registerLobbyCommands() {
    SetLobbyLocationCommand setLobbyLocation = new SetLobbyLocationCommand(gameLobbySystem);
    this.gameLobbySystem.getGameApi().getCommand("setlobbyspawn").setExecutor(setLobbyLocation);
  }

  public void unRegisterCommands() {
    this.gameLobbySystem.getGameApi().getCommand("setlobbyspawn").setExecutor(null);
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

  public void playerJoin(Player p) {
    this.getGameLobbySystem().getGame().getGameData().addPlayer(p);
    this.getGameLobbySystem().getLobbyBoard().pushBoard();
  }

  public void playerQuit(Player p) {
    this.getGameLobbySystem().getGame().getGameData().removePlayer(p);
    this.getGameLobbySystem().getLobbyBoard().pushBoard();
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
