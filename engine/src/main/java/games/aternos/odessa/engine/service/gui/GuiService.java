package games.aternos.odessa.engine.service.gui;

import games.aternos.odessa.engine.service.Service;
import games.aternos.odessa.gameapi.GameApi;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class GuiService extends Service {
    protected GuiService(GameApi gameApi) {
        super(gameApi);
    }

    public Inventory createGuiInventory(List<ItemStack> items, String guiname, int guisize){
        Inventory i = Bukkit.createInventory(null, guisize, guiname);
        for(ItemStack item : items){
            i.addItem(item);
        }

        return i;
    }

    public void openGui(Player p, Inventory i){
        p.openInventory(i);
    }
}
