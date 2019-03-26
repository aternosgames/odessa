package games.aternos.odessa.engine.lobby.handler;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.lobby.LobbyControllerOwned;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class LobbyEntityDamageHandler extends LobbyControllerOwned implements Listener {
  public LobbyEntityDamageHandler(LobbyController owner) {
    super(owner);
  }

  @EventHandler
  public void onEntityDamageLobby(EntityDamageEvent e) {
    e.setCancelled(true);
  }

}
