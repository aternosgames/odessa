package games.aternos.odessa.engine.lobby.handler;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.lobby.LobbyControllerOwned;
import games.aternos.odessa.gameapi.game.element.Arena;
import games.aternos.odessa.gameapi.game.element.Kit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * todo: simplify and clean
 */
public class LobbyPlayerClickHandler extends LobbyControllerOwned implements Listener {
  public LobbyPlayerClickHandler(LobbyController owner) {
    super(owner);
  }

  @EventHandler
  public void onPlayerClick(InventoryClickEvent e) {
    e.setCancelled(true);
    Player p = (Player) e.getWhoClicked();
    if (e.getInventory().getName().equals("Kit Selection")) {

      if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
        Kit selected = null;
        for (Kit k : this.getOwner().getGameLobbySystem().getGameConfiguration().getGameKits()) {
          if (e.getCurrentItem().getItemMeta().getDisplayName().equals(k.getKitName())) {
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
    } else if (e.getInventory().getName().equals("Arena Vote")) {
      /*
      todo: remove vote if already voted
       */
      if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
        Arena votedFor = null;
        for (Arena a : this.getOwner().getGameLobbySystem().getGame().getGameConfiguration().getGameArenas()) {
          if (e.getCurrentItem().getItemMeta().getDisplayName().equals(a.getArenaName())) {
            votedFor = a;
          }
        }
        if (votedFor != null) {
          if (!this.getOwner().getGameLobbySystem().getArenaVote().containsKey(votedFor)) {
            this.getOwner().getGameLobbySystem().getArenaVote().put(votedFor, 0);
          }
          this.getOwner().getGameLobbySystem().getArenaVote().put(votedFor, this.getOwner().getGameLobbySystem().getArenaVote().get(votedFor) + 1);
          p.closeInventory();
          p.sendMessage(ChatColor.BLUE + "Lobby> " + ChatColor.GRAY + "Voted for: " + votedFor.getArenaName() + " by: " + votedFor.getArenaAuthor());
        }


      }
    }
  }
}
