package games.aternos.odessa.engine.lobby.handler;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.lobby.LobbyControllerOwned;
import games.aternos.odessa.gameapi.eventhook.Hook;
import games.aternos.odessa.gameapi.eventhook.handler.PlayerDropItemEventHook;
import org.bukkit.event.player.PlayerDropItemEvent;

import javax.annotation.Nonnull;

public class LobbyPlayerDropHandler extends LobbyControllerOwned {
  public LobbyPlayerDropHandler(LobbyController owner) {
    super(owner);
    PlayerDropItemEventHook.hooks.add(new PlayerDropItemHandler("PlayerDropItemHandler"));
  }

  private class PlayerDropItemHandler extends Hook {
    private PlayerDropItemHandler(@Nonnull String hookID) {
      super(hookID);
    }

    @Override
    public void run(Object o) {
      ((PlayerDropItemEvent) o).setCancelled(true);
    }
  }
}
