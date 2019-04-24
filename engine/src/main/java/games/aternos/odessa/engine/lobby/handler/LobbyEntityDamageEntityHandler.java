package games.aternos.odessa.engine.lobby.handler;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.lobby.LobbyControllerOwned;
import games.aternos.odessa.gameapi.eventhook.Hook;
import games.aternos.odessa.gameapi.eventhook.handler.EntityDamageByEntityEventHook;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class LobbyEntityDamageEntityHandler extends LobbyControllerOwned {
  public LobbyEntityDamageEntityHandler(LobbyController owner) {
    super(owner);
    EntityDamageByEntityEventHook.hooks.add(new EntityDamageEntityHandler());
  }

  private class EntityDamageEntityHandler extends Hook {
    @Override
    public void run(Object o) {
      ((EntityDamageByEntityEvent) o).setCancelled(true);
    }
  }

}
