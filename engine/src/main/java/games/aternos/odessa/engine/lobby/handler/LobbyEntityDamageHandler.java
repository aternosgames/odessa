package games.aternos.odessa.engine.lobby.handler;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.lobby.LobbyControllerOwned;
import games.aternos.odessa.gameapi.Debug;
import games.aternos.odessa.gameapi.eventhook.Hook;
import games.aternos.odessa.gameapi.eventhook.handler.EntityDamageEventHook;
import org.bukkit.event.entity.EntityDamageEvent;

public class LobbyEntityDamageHandler extends LobbyControllerOwned {
  public LobbyEntityDamageHandler(LobbyController owner) {
    super(owner);
      EntityDamageEventHook.hooks.add(new EntityDamageHandler());
  }


    private class EntityDamageHandler extends Hook {
        @Override
        public void run(Object o) {
            Debug.$("rannnnn");
            if (!getOwner().getGameLobbySystem().isActive()) {
                return;
            }
            ((EntityDamageEvent) o).setCancelled(true);
        }
  }

}
