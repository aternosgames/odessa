package games.aternos.odessa.engine.lobby.handler;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.lobby.LobbyControllerOwned;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LobbyPlayerJoinHandler extends LobbyControllerOwned implements Listener {
  public LobbyPlayerJoinHandler(LobbyController owner) {
    super(owner);
  }

  @EventHandler
  public void playerJoinLobby(PlayerJoinEvent event) {
    if(!this.getOwner().getGameLobbySystem().isActive()){
      return;
    }
    event.setJoinMessage(ChatColor.BLUE + "Lobby> " + ChatColor.GRAY + " +" + event.getPlayer().getName());
    this.getOwner().playerJoin(event.getPlayer());
  }
}
