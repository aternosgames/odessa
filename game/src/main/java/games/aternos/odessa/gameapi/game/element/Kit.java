package games.aternos.odessa.gameapi.game.element;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Kit {

  private final List<ItemStack> kitItems;

  private final ItemStack coverItem;

  private final String kitName;

  public Kit(String kitName, List<ItemStack> kitItems, ItemStack coverItem) {
    this.kitName = kitName;
    this.kitItems = kitItems;
    this.coverItem = coverItem;
  }

  public List<ItemStack> getKitItems() {
    return kitItems;
  }

  public String getKitName() {
    return kitName;
  }

  public ItemStack getCoverItem() {
    return coverItem;
  }
}
