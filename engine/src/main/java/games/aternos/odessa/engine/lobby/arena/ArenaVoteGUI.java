package games.aternos.odessa.engine.lobby.arena;

import games.aternos.odessa.engine.lobby.GameLobbySystem;
import games.aternos.odessa.gameapi.game.element.Arena;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;

public class ArenaVoteGUI {
    private final GameLobbySystem gameLobbySystem;

    public ArenaVoteGUI(GameLobbySystem gameLobbySystem){
        this.gameLobbySystem = gameLobbySystem;
    }

    public void openArenaVoteMenu(Player p){

        Inventory vote = Bukkit.createInventory(null, 9, "Arena Vote");

        for(Arena a : this.gameLobbySystem.getGame().getGameConfiguration().getGameArenas()){
            ItemStack arena = new ItemStack(Material.SIGN);
            ItemMeta arenaMeta = arena.getItemMeta();
            arenaMeta.setDisplayName(a.getArenaName());
            arenaMeta.setLore(Collections.singletonList("Author: " + a.getArenaAuthor()));
            arena.setItemMeta(arenaMeta);
            vote.addItem(arena);
        }

        p.openInventory(vote);
    }

}
