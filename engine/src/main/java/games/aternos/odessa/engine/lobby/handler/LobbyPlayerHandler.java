package games.aternos.odessa.engine.lobby.handler;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.gameapi.game.element.Arena;
import games.aternos.odessa.gameapi.game.element.Kit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;

public class LobbyPlayerHandler implements Listener {

  private final LobbyController lobbyController;

  public LobbyPlayerHandler(@Nonnull LobbyController lobbyController) {
    this.lobbyController = lobbyController;
  }

  @EventHandler
  public void playerJoinLobby(PlayerJoinEvent event) {
    event.setJoinMessage(ChatColor.BLUE + "Lobby> " + ChatColor.GRAY + " +" + event.getPlayer().getName());
    this.lobbyController.playerJoin(event.getPlayer());
  }

  @EventHandler
  public void playerLeaveLobby(PlayerQuitEvent event) {
    event.setQuitMessage(ChatColor.BLUE + "Lobby> " + ChatColor.GRAY + " -" + event.getPlayer().getName());
    this.lobbyController.playerQuit(event.getPlayer());
  }

  @EventHandler
  public void onInteract(PlayerInteractEvent e) {
    if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.CHEST) {
      e.setCancelled(true);
      ItemStack i = e.getPlayer().getInventory().getItemInMainHand();
      ItemMeta im = i.getItemMeta();
      if (!(im.getDisplayName() == null)) {
        if (im.getDisplayName().contains("Kit Selection")) {
          this.lobbyController.getGameLobbySystem().getKitSelectionGUI().openKitSelectionMenu(e.getPlayer());
        }else if(im.getDisplayName().contains("Map Vote")){
          this.lobbyController.getGameLobbySystem().getArenaVoteGUI().openArenaVoteMenu(e.getPlayer());
        }
      }
    }
    e.setCancelled(true);
  }

  @EventHandler
  public void onPlayerClick(InventoryClickEvent e) {
    e.setCancelled(true);
    Player p = (Player) e.getWhoClicked();
    if (e.getInventory().getName().equals("Kit Selection")) {

      if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
        Kit selected = null;
        for (Kit k : this.lobbyController.getGameLobbySystem().getGameConfiguration().getGameKits()) {
          if (e.getCurrentItem().getItemMeta().getDisplayName().equals(k.getKitName())) {
            selected = k;
          }
        }

        if (selected != null) {
          if (!this.lobbyController.getGameLobbySystem().getGame().getGameData().getSelectedPlayerKits().containsKey(p)) {
            this.lobbyController.getGameLobbySystem().getGame().getGameData().getSelectedPlayerKits().put(p, selected);
            this.lobbyController.getGameLobbySystem().getLobbyBoard().pushBoard(p);
          } else {
            this.lobbyController.getGameLobbySystem().getGame().getGameData().getSelectedPlayerKits().remove(p);
            this.lobbyController.getGameLobbySystem().getGame().getGameData().getSelectedPlayerKits().put(p, selected);
            this.lobbyController.getGameLobbySystem().getLobbyBoard().pushBoard(p);
          }

          p.closeInventory();
          p.sendMessage(ChatColor.BLUE + "Lobby> " + ChatColor.GRAY + "Selected Kit: " + selected.getKitName());
        }


      }
    }else if(e.getInventory().getName().equals("Arena Vote")){
      /*
      todo: remove vote if already voted
       */
      if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
        Arena votedFor = null;
        for(Arena a : this.lobbyController.getGameLobbySystem().getGame().getGameConfiguration().getGameArenas()){
          if(e.getCurrentItem().getItemMeta().getDisplayName().equals(a.getArenaName())){
            votedFor = a;
          }
        }
        if(votedFor != null){
          if(!this.lobbyController.getGameLobbySystem().getMapVote().containsKey(votedFor)){
            this.lobbyController.getGameLobbySystem().getMapVote().put(votedFor, 0);
          }
          this.lobbyController.getGameLobbySystem().getMapVote().put(votedFor, this.lobbyController.getGameLobbySystem().getMapVote().get(votedFor) + 1);
          p.closeInventory();
          p.sendMessage(ChatColor.BLUE + "Lobby> " + ChatColor.GRAY + "Voted for: " + votedFor.getArenaName() + " by: " + votedFor.getArenaAuthor());
        }


      }
    }
  }

  @EventHandler
  public void onDropItemLobby(PlayerDropItemEvent e) {
    e.setCancelled(true);
  }

  @EventHandler
  public void playerHitPlayerLobby(EntityDamageByEntityEvent e) {
    e.setCancelled(true);
  }

  @EventHandler
  public void onHungerDepleteLobby(FoodLevelChangeEvent e) {
    e.setCancelled(true);
  }

  @EventHandler
  public void onEntityDamageLobby(EntityDamageEvent e) {
    e.setCancelled(true);
  }

  @EventHandler
  public void onWeatherChangeLobby(WeatherChangeEvent event) {
    event.setCancelled(true);
  }
}


