package games.aternos.odessa.engine.lobby.kit;

import games.aternos.odessa.engine.lobby.GameLobbySystem;
import games.aternos.odessa.gameapi.game.element.Kit;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class KitSelectionGUI {

  private final GameLobbySystem gameLobbySystem;

  public KitSelectionGUI(GameLobbySystem gameLobbySystem) {
    this.gameLobbySystem = gameLobbySystem;
  }

  public void openKitSelectionMenu(Player p) {
    Inventory selection = Bukkit.createInventory(null, 9, "Kit Selection");
    List<Kit> kits = this.gameLobbySystem.getGame().getGameConfiguration().getGameKits();
    Kit currentKit = this.gameLobbySystem.getGame().getGameData().getSelectedPlayerKits().get(p);

    if (currentKit == null) {
      currentKit = kits.get(0);
    }

    for (Kit k : kits) {

      ItemStack coverItem = k.getCoverItem();
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
