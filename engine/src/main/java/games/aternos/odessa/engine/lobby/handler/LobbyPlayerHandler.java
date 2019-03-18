package games.aternos.odessa.engine.lobby.handler;

import games.aternos.odessa.engine.lobby.LobbyController;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
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
        }
      }
    }
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


