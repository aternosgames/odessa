package games.aternos.odessa.engine.lobby.kit;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.lobby.LobbyControllerOwned;
import games.aternos.odessa.gameapi.game.element.GUI;
import games.aternos.odessa.gameapi.game.element.Kit;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Handles the Kit Selection GUI
 */
public class KitSelectionGUI extends LobbyControllerOwned implements GUI {
  public KitSelectionGUI(LobbyController lobbyController) {
    super(lobbyController);
  }

  /**
   * Open the Kit Selection GUI for a Player
   *
   * @param p Player
   */
  public void openGUI(@NotNull Player p) {
    Inventory selection = Bukkit.createInventory(null, 9, "Kit Selection");
    selection.clear();
    List<Kit> kits = this.getOwner().getGameLobbySystem().getGame().getGameConfiguration().getGameKits();
    Kit currentKit = this.getOwner().getGameLobbySystem().getGame().getGameData().getSelectedPlayerKits().get(p);

    if (currentKit == null) {
      currentKit = kits.get(0);
    }

    for (Kit k : kits) {

      ItemStack coverItem = new ItemStack(k.getCoverItem().getType());
      ItemMeta coverItemMeta = coverItem.getItemMeta();
      coverItemMeta.setDisplayName(k.getKitName());

      if (currentKit.equals(k)) {
        coverItemMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
      }
      coverItem.setItemMeta(coverItemMeta);
      selection.addItem(coverItem);
    }

    p.openInventory(selection);

  }


}
