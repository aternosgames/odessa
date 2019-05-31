package games.aternos.odessa.engine.lobby.handler;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.lobby.LobbyControllerOwned;
import games.aternos.odessa.gameapi.eventhook.Hook;
import games.aternos.odessa.gameapi.eventhook.handler.InventoryClickEventHook;
import games.aternos.odessa.gameapi.game.element.Arena;
import games.aternos.odessa.gameapi.game.element.Kit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * todo: simplify and clean
 */
public class LobbyPlayerClickHandler extends LobbyControllerOwned {

  private final HashMap<Player, Arena> voted;

  public LobbyPlayerClickHandler(LobbyController owner) {
    super(owner);
    InventoryClickEventHook.hooks.add(new PlayerClickHandler());
    voted = new HashMap<>();
  }

  private class PlayerClickHandler extends Hook {
    @Override
    public void run(Object o) {
      InventoryClickEvent e = (InventoryClickEvent) o;
      e.setCancelled(true);
      if (e.getInventory().getName().equals("Kit Selection") && e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
        processKitSelection((Player) e.getWhoClicked(), e.getCurrentItem());
      } else if (e.getInventory().getName().equals("Arena Vote") && e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
        processArenaSelection((Player) e.getWhoClicked(), e.getCurrentItem());
      }
    }
  }

  private void processKitSelection(Player p, ItemStack currentItem) {
    Kit selected = null;
    for (Kit k : this.getOwner().getGameLobbySystem().getGameConfiguration().getGameKits()) {
      if (currentItem.getItemMeta().getDisplayName().equals(k.getKitName())) {
        selected = k;
      }
    }

    if (selected != null) {
      if (!this.getOwner().getGameLobbySystem().getGame().getGameData().getSelectedPlayerKits().containsKey(p)) {
        this.getOwner().getGameLobbySystem().getGame().getGameData().getSelectedPlayerKits().put(p, selected);
        this.getOwner().getGameLobbySystem().getLobbyBoard().pushBoard(p);
      } else {
        this.getOwner().getGameLobbySystem().getGame().getGameData().getSelectedPlayerKits().remove(p);
        this.getOwner().getGameLobbySystem().getGame().getGameData().getSelectedPlayerKits().put(p, selected);
        this.getOwner().getGameLobbySystem().getLobbyBoard().pushBoard(p);
      }

      p.closeInventory();
      p.sendMessage(ChatColor.BLUE + "Lobby> " + ChatColor.GRAY + "Selected Kit: " + selected.getKitName());
    }
  }

  private void processArenaSelection(Player p, ItemStack currentItem) {

    Arena votedFor = null;
    for (Arena a : this.getOwner().getGameLobbySystem().getGame().getGameConfiguration().getGameArenas()) {
      if (currentItem.getItemMeta().getDisplayName().equals(a.getArenaName())) {
        votedFor = a;
      }
    }

    if (votedFor != null) {
      if (this.voted.containsKey(p)) {
        this.getOwner().getGameLobbySystem().getArenaVote().put(votedFor, this.getOwner().getGameLobbySystem().getArenaVote().get(votedFor) - 1);
        this.voted.remove(p);
      }
      if (!this.getOwner().getGameLobbySystem().getArenaVote().containsKey(votedFor)) {
        this.getOwner().getGameLobbySystem().getArenaVote().put(votedFor, 0);
      }
      this.getOwner().getGameLobbySystem().getArenaVote().put(votedFor, this.getOwner().getGameLobbySystem().getArenaVote().get(votedFor) + 1);
      this.voted.put(p, votedFor);
      p.closeInventory();
      p.sendMessage(ChatColor.BLUE + "Lobby> " + ChatColor.GRAY + "Voted for: " + votedFor.getArenaName() + " by: " + votedFor.getArenaAuthor());
    }

  }

}
