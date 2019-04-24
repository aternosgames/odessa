package games.aternos.odessa.engine.lobby.handler;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.lobby.LobbyControllerOwned;
import games.aternos.odessa.gameapi.eventhook.Hook;
import games.aternos.odessa.gameapi.eventhook.handler.PlayerDropItemEventHook;
import org.bukkit.event.player.PlayerDropItemEvent;

public class LobbyPlayerDropHandler extends LobbyControllerOwned {
  public LobbyPlayerDropHandler(LobbyController owner) {
    super(owner);
    PlayerDropItemEventHook.hooks.add(new PlayerDropItemHandler());
  }

  private class PlayerDropItemHandler extends Hook {
    @Override
    public void run(Object o) {
      ((PlayerDropItemEvent) o).setCancelled(true);
    }
  }
}
