package games.aternos.odessa.engine.lobby.handler;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.lobby.LobbyControllerOwned;
import games.aternos.odessa.gameapi.eventhook.Hook;
import games.aternos.odessa.gameapi.eventhook.handler.FoodLevelChangeEventHook;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import javax.annotation.Nonnull;

public class LobbyPlayerHungerHandler extends LobbyControllerOwned {
  public LobbyPlayerHungerHandler(LobbyController owner) {
    super(owner);
    FoodLevelChangeEventHook.hooks.add(new PlayerHungerHandler("PlayerHungerHandler"));
  }

  public class PlayerHungerHandler extends Hook {
    private PlayerHungerHandler(@Nonnull String hookID) {
      super(hookID);
    }

    @Override
    public void run(Object o) {
      ((FoodLevelChangeEvent) o).setCancelled(true);
    }
  }
}
