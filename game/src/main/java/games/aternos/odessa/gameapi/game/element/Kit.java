package games.aternos.odessa.gameapi.game.element;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Kit {

  private List<ItemStack> kitItems;

  private String kitName;

  public Kit(String kitName, List<ItemStack> kitItems) {
    this.kitName = kitName;
    this.kitItems = kitItems;
  }

  public List<ItemStack> getKitItems() {
    return kitItems;
  }

  public void setKitItems(List<ItemStack> kitItems) {
    this.kitItems = kitItems;
  }

  public String getKitName() {
    return kitName;
  }

  public void setKitName(String kitName) {
    this.kitName = kitName;
  }
}
