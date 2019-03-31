package games.aternos.odessa.engine.lobby.handler;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.lobby.LobbyControllerOwned;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LobbyPlayerLeaveHandler extends LobbyControllerOwned implements Listener {
  public LobbyPlayerLeaveHandler(LobbyController owner) {
    super(owner);
  }

  @EventHandler
  public void playerLeaveLobby(PlayerQuitEvent event) {
    if(!this.getOwner().getGameLobbySystem().isActive()){
      return;
    }
    event.setQuitMessage(ChatColor.BLUE + "Lobby> " + ChatColor.GRAY + " -" + event.getPlayer().getName());
    this.getOwner().playerQuit(event.getPlayer());
  }
}
