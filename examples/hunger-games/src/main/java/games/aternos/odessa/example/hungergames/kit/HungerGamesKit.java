package games.aternos.odessa.example.hungergames.kit;

import games.aternos.odessa.gameapi.game.element.Kit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class HungerGamesKit {

  private final List<Kit> kits;

  public HungerGamesKit() {
    kits = new ArrayList<>();
    generateKits();
  }

  public List<Kit> getKits() {
    return this.kits;
  }

  private void generateKits() { // should be config based in actual production game
    List<ItemStack> kitItems = new ArrayList<>();
    kitItems.add(new ItemStack(Material.STONE_SWORD, 1));
    Kit defaultKit = new Kit("Default", kitItems, kitItems.get(0));
    kits.add(defaultKit);
    kitItems.clear();
    kitItems.add(new ItemStack(Material.IRON_SWORD));
    Kit otherKit = new Kit("Other Kit", kitItems, kitItems.get(0));
    kits.add(otherKit);
  }

}
