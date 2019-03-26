package games.aternos.odessa.engine.lobby.handler;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.lobby.LobbyControllerOwned;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class LobbyPlayerDropHandler extends LobbyControllerOwned implements Listener {
  public LobbyPlayerDropHandler(LobbyController owner) {
    super(owner);
  }

  @EventHandler
  public void onDropItemLobby(PlayerDropItemEvent e) {
    e.setCancelled(true);
  }
}
