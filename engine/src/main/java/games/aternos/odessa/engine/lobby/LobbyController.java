package games.aternos.odessa.engine.lobby;

import games.aternos.odessa.engine.lobby.command.AddSpawnCommand;
import games.aternos.odessa.engine.lobby.command.CreateArenaCommand;
import games.aternos.odessa.engine.lobby.command.SetLobbyLocationCommand;
import games.aternos.odessa.engine.lobby.handler.*;
import games.aternos.odessa.gameapi.Debug;
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

  public void lobbyTick() {

  }

  void registerLobbyCommands() {
    SetLobbyLocationCommand setLobbyLocation = new SetLobbyLocationCommand(gameLobbySystem);
    this.gameLobbySystem.getGameApi().getCommand("setlobbyspawn").setExecutor(setLobbyLocation);
    CreateArenaCommand createArenaCommand = new CreateArenaCommand(gameLobbySystem);
    this.gameLobbySystem.getGameApi().getCommand("createarena").setExecutor(createArenaCommand);
    AddSpawnCommand addSpawnCommand = new AddSpawnCommand(gameLobbySystem);
    this.gameLobbySystem.getGameApi().getCommand("addarenaspawn").setExecutor(addSpawnCommand);
  }

  void unRegisterCommands() {
    this.gameLobbySystem.getGameApi().getCommand("setlobbyspawn").setExecutor(null);
    this.gameLobbySystem.getGameApi().getCommand("createarena").setExecutor(null);
    this.gameLobbySystem.getGameApi().getCommand("addarenaspawn").setExecutor(null);
  }

  void registerLobbyListeners() {
    this.lobbyListeners.add(new LobbyEntityDamageEntityHandler(this));
    this.lobbyListeners.add(new LobbyEntityDamageHandler(this));
    this.lobbyListeners.add(new LobbyPlayerClickHandler(this));
    this.lobbyListeners.add(new LobbyPlayerDropHandler(this));
    this.lobbyListeners.add(new LobbyPlayerHungerHandler(this));
    this.lobbyListeners.add(new LobbyPlayerInteractHandler(this));
    this.lobbyListeners.add(new LobbyPlayerJoinHandler(this));
    this.lobbyListeners.add(new LobbyPlayerLeaveHandler(this));
    this.lobbyListeners.add(new LobbyWeatherChangeHandler(this));
    for (Listener listener : this.lobbyListeners) {
      Bukkit.getServer().getPluginManager().registerEvents(listener, this.getGameLobbySystem().getGameApi());
      Debug.$("registered: " + listener.getClass().getName());
    }
  }

  void unRegisterLobbyListeners() {
    for (Listener l : this.getLobbyListeners()) {
      HandlerList.unregisterAll(l);
      lobbyListeners.remove(l);
    }
  }

  public void playerJoin(@Nonnull Player p) {
    this.getGameLobbySystem().getGame().getGameData().addPlayer(p);
    this.getGameLobbySystem().getLobbyBoard().pushBoard();
    cleanPlayer(p);
    p.sendActionBar(ChatColor.BOLD + this.getGameLobbySystem().getGame().getGameConfiguration().getGameName() + " Lobby");
  }

  public void playerQuit(@Nonnull Player p) {
    this.getGameLobbySystem().getGame().getGameData().removePlayer(p);
    this.getGameLobbySystem().getLobbyBoard().pushBoard();
  }

  private void cleanPlayer(@Nonnull Player p) {
    this.getGameLobbySystem().getPlayerService().clearPlayer(p);
    this.getGameLobbySystem().getPlayerService().healPlayer(p);
    p.setGameMode(GameMode.ADVENTURE);
    p.teleport(this.getGameLobbySystem().getLobbyIoConfiguration().getLobbySpawn());
    ItemStack kitSelection = new ItemStack(Material.CHEST);
    ItemStack arenaVote = new ItemStack(Material.GRASS);
    ItemMeta arenaVoteMeta = arenaVote.getItemMeta();
    ItemMeta kitSelectionMeta = kitSelection.getItemMeta();
    kitSelectionMeta.setDisplayName(ChatColor.GREEN + "Kit Selection");
    arenaVoteMeta.setDisplayName(ChatColor.GREEN + "Arena Vote");
    kitSelection.setItemMeta(kitSelectionMeta);
    arenaVote.setItemMeta(arenaVoteMeta);
    p.getInventory().setItem(0, kitSelection);
    p.getInventory().setItem(1, arenaVote);
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
