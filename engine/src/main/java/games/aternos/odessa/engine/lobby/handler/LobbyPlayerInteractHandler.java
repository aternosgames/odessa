package games.aternos.odessa.engine.lobby.handler;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.lobby.LobbyControllerOwned;
import games.aternos.odessa.gameapi.eventhook.Hook;
import games.aternos.odessa.gameapi.eventhook.handler.PlayerInteractEventHook;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LobbyPlayerInteractHandler extends LobbyControllerOwned {
    public LobbyPlayerInteractHandler(LobbyController owner) {
        super(owner);
        PlayerInteractEventHook.hooks.add(new PlayerInteractHandler());
    }

    public class PlayerInteractHandler extends Hook {
        @Override
        public void run(Object o) {
            PlayerInteractEvent e = (PlayerInteractEvent) o;
            ItemStack i = e.getPlayer().getInventory().getItemInMainHand();
            if (i == null || !i.hasItemMeta()) {
                return;
            }
            ItemMeta im = i.getItemMeta();
            if (im.getDisplayName() != null && i.getType() != Material.AIR) {
                if (im.getDisplayName().contains("Kit Selection") && e.getPlayer().getInventory().getItemInMainHand().getType() == Material.CHEST) {
                    getOwner().getGameLobbySystem().getKitSelectionGUI().openGUI(e.getPlayer());
                } else if (im.getDisplayName().contains("Arena Vote") && e.getPlayer().getInventory().getItemInMainHand().getType() == Material.GRASS) {
                    getOwner().getGameLobbySystem().getArenaVoteGUI().openGUI(e.getPlayer());
                }
            }

            e.setCancelled(true);
        }
    }

}
