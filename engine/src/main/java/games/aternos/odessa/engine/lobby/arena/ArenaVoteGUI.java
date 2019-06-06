package games.aternos.odessa.engine.lobby.arena;

import com.sun.istack.internal.NotNull;
import games.aternos.odessa.engine.lobby.GameLobbySystem;
import games.aternos.odessa.gameapi.game.element.Arena;
import games.aternos.odessa.gameapi.game.element.GUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/** Handles the Arena Vote GUI */
public class ArenaVoteGUI implements GUI {
  private final GameLobbySystem gameLobbySystem;

  public ArenaVoteGUI(GameLobbySystem gameLobbySystem) {
    this.gameLobbySystem = gameLobbySystem;
  }

  /**
   * Opens the Arena Vote GUI
   *
   * @param p The Player
   */
  public void openGUI(@NotNull Player p) {

    Inventory vote = Bukkit.createInventory(null, 9, "Arena Vote");

    for (Arena a : this.gameLobbySystem.getGame().getGameConfiguration().getGameArenas()) {
      ItemStack arena = new ItemStack(Material.OAK_SIGN);
      ItemMeta arenaMeta = arena.getItemMeta();
        assert arenaMeta != null;
      arenaMeta.setDisplayName(a.getArenaName());
      int votes;
      votes = gameLobbySystem.getArenaVote().getOrDefault(a, 0);
      arenaMeta.setLore(Arrays.asList("Author: " + a.getArenaAuthor(), "Votes: " + votes));
      arena.setItemMeta(arenaMeta);
      vote.addItem(arena);
    }

    p.openInventory(vote);
  }
}
