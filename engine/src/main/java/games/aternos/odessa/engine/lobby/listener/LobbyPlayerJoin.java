package games.aternos.odessa.engine.lobby.listener;

import games.aternos.odessa.engine.lobby.LobbyController;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LobbyPlayerJoin implements Listener {

  private final LobbyController lobbyController;

  public LobbyPlayerJoin(LobbyController lobbyController) {
    this.lobbyController = lobbyController;
  }

  @EventHandler
  public void playerJoinLobby(PlayerJoinEvent event) {
    lobbyController.getGameLobbySystem().getGame().getGameData().addPlayer(event.getPlayer());
  }

}


