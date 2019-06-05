package games.aternos.odessa.engine.lobby.handler;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.lobby.LobbyControllerOwned;
import games.aternos.odessa.gameapi.eventhook.Hook;
import games.aternos.odessa.gameapi.eventhook.handler.EntityDamageByEntityEventHook;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import javax.annotation.Nonnull;

public class LobbyEntityDamageEntityHandler extends LobbyControllerOwned {
  public LobbyEntityDamageEntityHandler(LobbyController owner) {
    super(owner);
      EntityDamageByEntityEventHook.hooks.add(
              new EntityDamageEntityHandler("EntityDamageEntityHandler"));
  }

  private class EntityDamageEntityHandler extends Hook {
      private EntityDamageEntityHandler(@Nonnull String hookID) {
          super(hookID);
      }

    @Override
    public void run(Object o) {
      ((EntityDamageByEntityEvent) o).setCancelled(true);
    }
  }
}
