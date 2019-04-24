package games.aternos.odessa.engine.lobby.handler;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.lobby.LobbyControllerOwned;
import games.aternos.odessa.gameapi.eventhook.Hook;
import games.aternos.odessa.gameapi.eventhook.handler.EntityDamageByEntityEventHook;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class LobbyEntityDamageEntityHandler extends LobbyControllerOwned implements Listener {
  public LobbyEntityDamageEntityHandler(LobbyController owner) {
    super(owner);
    EntityDamageByEntityEventHook.hooks.add(new EntityDamageHandler(EntityDamageByEntityEventHook.class));
  }

  private class EntityDamageHandler extends Hook {

    public EntityDamageHandler(Class event) {
      super(event);
    }

    @Override
    public void run(Object o) {
      if (!getOwner().getGameLobbySystem().isActive()) {
        return;
      }
      EntityDamageByEntityEvent entityDamageByEntityEvent = (EntityDamageByEntityEvent) o;
      entityDamageByEntityEvent.setCancelled(true);
    }
  }

}
