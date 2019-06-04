package games.aternos.odessa.engine.lobby.handler;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.lobby.LobbyControllerOwned;
import games.aternos.odessa.gameapi.eventhook.Hook;
import games.aternos.odessa.gameapi.eventhook.handler.PlayerQuitEventHook;
import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerQuitEvent;

import javax.annotation.Nonnull;

public class LobbyPlayerLeaveHandler extends LobbyControllerOwned {
  public LobbyPlayerLeaveHandler(LobbyController owner) {
    super(owner);
    PlayerQuitEventHook.hooks.add(new PlayerQuitHandler("PlayerQuitHandler"));
  }

  public class PlayerQuitHandler extends Hook {
    private PlayerQuitHandler(@Nonnull String hookID) {
      super(hookID);
    }

    @Override
    public void run(Object o) {
      PlayerQuitEvent e = (PlayerQuitEvent) o;
      e.setQuitMessage(
          ChatColor.BLUE + "Lobby> " + ChatColor.GRAY + " -" + e.getPlayer().getName());
      getOwner().playerQuit(e.getPlayer());
    }
  }
}
