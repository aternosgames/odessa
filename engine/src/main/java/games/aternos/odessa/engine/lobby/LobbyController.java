package games.aternos.odessa.engine.lobby;

import games.aternos.odessa.engine.lobby.command.SetLobbyLocationCommand;
import games.aternos.odessa.engine.lobby.handler.LobbyPlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class LobbyController {

  private final GameLobbySystem gameLobbySystem;
  private List<Listener> lobbyListeners;

  LobbyController(@Nonnull GameLobbySystem gameLobbySystem) {
    this.gameLobbySystem = gameLobbySystem;
    this.lobbyListeners = new ArrayList<>();
  }

  void registerLobbyCommands() {
    SetLobbyLocationCommand setLobbyLocation = new SetLobbyLocationCommand(gameLobbySystem);
    this.gameLobbySystem.getGameApi().getCommand("setlobbyspawn").setExecutor(setLobbyLocation);
  }

  void unRegisterCommands() {
    this.gameLobbySystem.getGameApi().getCommand("setlobbyspawn").setExecutor(null);
  }

  void registerLobbyListeners() {
    LobbyPlayerHandler lobbyPlayerHandler = new LobbyPlayerHandler(this);
    lobbyListeners.add(lobbyPlayerHandler);
    Bukkit.getServer().getPluginManager().registerEvents(lobbyPlayerHandler, this.getGameLobbySystem().getGameApi());
  }

  void unRegisterLobbyListeners() {
    for (Listener l : this.getLobbyListeners()) {
      HandlerList.unregisterAll(l);
      lobbyListeners.remove(l);
    }
  }

  public void playerJoin(Player p) {
    this.getGameLobbySystem().getGame().getGameData().addPlayer(p);
    this.getGameLobbySystem().getLobbyBoard().pushBoard();
    cleanPlayer(p);
    p.sendActionBar(ChatColor.BOLD + this.getGameLobbySystem().getGame().getGameConfiguration().getGameName() + " Lobby");
  }

  public void playerQuit(Player p) {
    this.getGameLobbySystem().getGame().getGameData().removePlayer(p);
    this.getGameLobbySystem().getLobbyBoard().pushBoard();
  }

  private void cleanPlayer(Player p) {
    this.getGameLobbySystem().getPlayerService().clearPlayer(p);
    this.getGameLobbySystem().getPlayerService().healPlayer(p);
    p.setGameMode(GameMode.ADVENTURE);
    p.teleport(this.getGameLobbySystem().getLobbyIoConfiguration().getLobbySpawn());
    ItemStack kitSelection = new ItemStack(Material.CHEST);
    ItemMeta kitSelectionMeta = kitSelection.getItemMeta();
    kitSelectionMeta.setDisplayName(ChatColor.GREEN + "Kit Selection");
    kitSelection.setItemMeta(kitSelectionMeta);
    p.getInventory().setItem(0, kitSelection);
  }

  public GameLobbySystem getGameLobbySystem() {
    return gameLobbySystem;
  }

  public List<Listener> getLobbyListeners() {
    return lobbyListeners;
  }

  public void setLobbyListeners(@Nonnull List<Listener> lobbyListeners) {
    this.lobbyListeners = lobbyListeners;
  }
}
