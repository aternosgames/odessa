package games.aternos.odessa.engine.lobby.handler;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.lobby.LobbyControllerOwned;
import games.aternos.odessa.gameapi.eventhook.Hook;
import games.aternos.odessa.gameapi.eventhook.handler.PlayerJoinEventHook;
import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.annotation.Nonnull;

public class LobbyPlayerJoinHandler extends LobbyControllerOwned {
  public LobbyPlayerJoinHandler(LobbyController owner) {
    super(owner);
    PlayerJoinEventHook.hooks.add(new PlayerJoinHandler("PlayerJoinHandler"));
  }

  public class PlayerJoinHandler extends Hook {
    private PlayerJoinHandler(@Nonnull String hookID) {
      super(hookID);
    }

    @Override
    public void run(Object o) {
      PlayerJoinEvent event = (PlayerJoinEvent) o;
      event.setJoinMessage(
          ChatColor.BLUE + "Lobby> " + ChatColor.GRAY + " +" + event.getPlayer().getName());
      getOwner().playerJoin(event.getPlayer());
    }
  }
}
