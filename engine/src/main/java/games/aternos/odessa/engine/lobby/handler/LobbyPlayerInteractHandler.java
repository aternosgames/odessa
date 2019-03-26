package games.aternos.odessa.engine.lobby.handler;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.lobby.LobbyControllerOwned;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LobbyPlayerInteractHandler extends LobbyControllerOwned implements Listener {
  public LobbyPlayerInteractHandler(LobbyController owner) {
    super(owner);
  }

  @EventHandler
  public void onInteract(PlayerInteractEvent e) {

    ItemStack i = e.getPlayer().getInventory().getItemInMainHand();
    ItemMeta im = i.getItemMeta();
    if (im.getDisplayName() != null) {
      if (im.getDisplayName().contains("Kit Selection") && e.getPlayer().getInventory().getItemInMainHand().getType() == Material.CHEST) {
        this.getOwner().getGameLobbySystem().getKitSelectionGUI().openGUI(e.getPlayer());
      } else if (im.getDisplayName().contains("Arena Vote") && e.getPlayer().getInventory().getItemInMainHand().getType() == Material.GRASS) {
        this.getOwner().getGameLobbySystem().getArenaVoteGUI().openGUI(e.getPlayer());
      }
    }

    e.setCancelled(true);
  }
}
