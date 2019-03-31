package games.aternos.odessa.engine.lobby.handler;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.lobby.LobbyControllerOwned;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class LobbyEntityDamageEntityHandler extends LobbyControllerOwned implements Listener {
  public LobbyEntityDamageEntityHandler(LobbyController owner) {
    super(owner);
  }

  @EventHandler
  public void playerHitPlayerLobby(EntityDamageByEntityEvent e) {
    if(!this.getOwner().getGameLobbySystem().isActive()){
      return;
    }
    e.setCancelled(true);
  }

}
