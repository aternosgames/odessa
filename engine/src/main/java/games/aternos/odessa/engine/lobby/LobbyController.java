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
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * Controls the LobbySystem
 */
public class LobbyController {

  private final GameLobbySystem gameLobbySystem;
  private List<Listener> lobbyListeners;
  private int tick;

  public LobbyController(@Nonnull GameLobbySystem gameLobbySystem) {
    this.gameLobbySystem = gameLobbySystem;
    this.lobbyListeners = new ArrayList<>();
    tick = 30;
  }

  /**
   * Called every second by the game phase to calculate lobby.
   */
  public void lobbyTick() {
    if (this.gameLobbySystem.getLobbyState().equals(LobbyState.WAITINGFORPLAYERS)) {

      if (this.gameLobbySystem.getGame().getGameData().getPlayers().size() >= this.gameLobbySystem.getGame().getGameConfiguration().getMinPlayers()) {
        this.gameLobbySystem.setLobbyState(LobbyState.FINALCALL);
        Bukkit.broadcastMessage(ChatColor.BLUE + "Lobby> " + "Minimum Players Reached. 30 Second final call");
      }
    } else if (this.gameLobbySystem.getLobbyState().equals(LobbyState.FINALCALL)) {
      conditionalAbort();
      this.getGameLobbySystem().getLobbyBoard().pushBoard();
      if (this.tick != 0) {
        this.tick = this.tick - 1;
      } else {
        // final countdown
        this.tick = 10;
        this.gameLobbySystem.setLobbyState(LobbyState.COUNTDOWN);
      }
    } else if (this.gameLobbySystem.getLobbyState().equals(LobbyState.COUNTDOWN)) {
      conditionalAbort();
      this.getGameLobbySystem().getLobbyBoard().pushBoard();
      Bukkit.broadcastMessage(ChatColor.BLUE + "Lobby> " + this.tick);
      if (tick != 1) {
        this.tick = this.tick - 1;
      } else {
       /*
       Start Game
        */
       this.gameLobbySystem.getGame().getGameData().setGameArena(this.gameLobbySystem.computeArenaVoted());
       this.gameLobbySystem.getGameLifecycleManager().nextPhase();
      }
    }
  }

  /**
   * Figures out if the lobby should abort, if so it does.
   */
  private void conditionalAbort() {
    if (this.gameLobbySystem.getGame().getGameData().getPlayers().size() < this.gameLobbySystem.getGame().getGameConfiguration().getMinPlayers()) {
      this.gameLobbySystem.setLobbyState(LobbyState.WAITINGFORPLAYERS);
      Bukkit.broadcastMessage(ChatColor.BLUE + "Lobby> " + "Minimum players no longer reached, countdown aborted.");
      this.tick = 30;
    }
  }

  /**
   * Registers the commands that can be used when the lobby is active.
   */
  public void registerLobbyCommands() {
    SetLobbyLocationCommand setLobbyLocation = new SetLobbyLocationCommand(gameLobbySystem);
    this.gameLobbySystem.getGameApi().getCommand("setlobbyspawn").setExecutor(setLobbyLocation);
    CreateArenaCommand createArenaCommand = new CreateArenaCommand(gameLobbySystem);
    this.gameLobbySystem.getGameApi().getCommand("createarena").setExecutor(createArenaCommand);
    AddSpawnCommand addSpawnCommand = new AddSpawnCommand(gameLobbySystem);
    this.gameLobbySystem.getGameApi().getCommand("addarenaspawn").setExecutor(addSpawnCommand);
  }


  /**
   * Registers the Listeners owned by the lobby.
   */
  public void registerLobbyListeners() {
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



  /**
   * Processes a Player Join when Lobby is Active.
   *
   * @param p The Player
   */
  public void playerJoin(@Nonnull Player p) {
    this.getGameLobbySystem().getGame().getGameData().addPlayer(p);
    this.getGameLobbySystem().getLobbyBoard().pushBoard();
    cleanPlayer(p);
    p.sendActionBar(ChatColor.BOLD + this.getGameLobbySystem().getGame().getGameConfiguration().getGameName() + " Lobby");
  }

  /**
   * Processes a Player Leave when Lobby is active
   *
   * @param p The Player
   */
  public void playerQuit(@Nonnull Player p) {
    this.getGameLobbySystem().getGame().getGameData().removePlayer(p);
    this.getGameLobbySystem().getLobbyBoard().pushBoard();
  }

  /**
   * Cleans the player for a lobby join
   *
   * @param p The Player
   */
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

  private List<Listener> getLobbyListeners() {
    return lobbyListeners;
  }

  public void setLobbyListeners(@Nonnull List<Listener> lobbyListeners) {
    this.lobbyListeners = lobbyListeners;
  }

  public int getTick() {
    return tick;
  }

  public void setTick(int tick) {
    this.tick = tick;
  }
}
