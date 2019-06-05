package games.aternos.odessa.example.hungergames.phase.ingame;

import edu.umd.cs.findbugs.annotations.NonNull;
import games.aternos.odessa.engine.service.player.PlayerService;
import games.aternos.odessa.engine.service.sidebar.SidebarService;
import games.aternos.odessa.example.hungergames.HungerGamesGameConfiguration;
import games.aternos.odessa.example.hungergames.phase.endgame.EndGamePhase;
import games.aternos.odessa.example.hungergames.phase.ingame.handler.InGamePlayerDeathHandler;
import games.aternos.odessa.example.hungergames.phase.ingame.handler.InGamePlayerJoinHandler;
import games.aternos.odessa.example.hungergames.phase.ingame.handler.InGamePlayerMoveHandler;
import games.aternos.odessa.example.hungergames.phase.ingame.runnable.InGameRunnable;
import games.aternos.odessa.gameapi.GameApi;
import games.aternos.odessa.gameapi.game.Game;
import games.aternos.odessa.gameapi.game.GameLifecycleManager;
import games.aternos.odessa.gameapi.game.GamePhase;
import games.aternos.odessa.gameapi.game.element.Kit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class InGamePhase extends GamePhase {

  private PlayerService playerService;

  private InGameSidebar inGameSidebar;

  private InGameState inGameState;


    /**
   * The current GameTick
   */
  private int gameTick;

  public InGamePhase(@NonNull GameLifecycleManager owner, Game game) {
    super(owner, game);
    this.setNextPhase(new EndGamePhase(this.getOwner(), this.getGame()));
  }

  @Override
  public void initialize() {
    this.playerService = new PlayerService(GameApi.getGameApi());
    this.inGameSidebar =
            new InGameSidebar(
                    new SidebarService(GameApi.getGameApi(), Bukkit.getScoreboardManager()), this);
    this.setGamePhaseRunnable(new InGameRunnable(this));
  }

  @Override
  public void startPhase() {
    this.setActive(true);
    playerService.dispersePlayers(
        this.getGame().getGameData().getPlayers(), this.getGame().getGameData().getGameArena());
    playerService.giveKitsToPlayers(
            this.getGame().getGameData().getSelectedPlayerKits(), true, true);
    this.setGamePhaseRunnableTask(
        this.getGamePhaseRunnable().runTaskTimer(GameApi.getGameApi(), 0, 20L));
    this.registerHooks();
    this.inGameSidebar.pushBoard();
    this.inGameState = InGameState.COUNTDOWN;
  }

    private int deathmatchCountDownStartTick;

  @Override
  public void hook() {

      //  if (this.getGame().getGameData().getPlayers().size() <= 1) {
      //   this.getOwner().nextPhase(); TODO: reenable this blocked out part -- for testing :)
      //   // player wins or oh shit whys there no players?
      // }


    switch (this.inGameState) {
      case COUNTDOWN:
        if (this.gameTick >= 10) {
          // next phase
          Bukkit.broadcastMessage("Let the Games Begin!");
          this.inGameState = InGameState.NORMAL_PLAY;
        } else {
          Bukkit.broadcastMessage(10 - this.gameTick + "");
          for (Player p : this.getGame().getGameData().getPlayersAndSpectatorsList()) {
            p.sendActionBar(ChatColor.GREEN + "Starting: " + (10 - this.gameTick));
          }
        }
        break;
      case NORMAL_PLAY:
        if (this.gameTick >= HungerGamesGameConfiguration.getTimeForceDeathmatch()
                || this.getGame().getGameData().getPlayers().size()
                <= HungerGamesGameConfiguration.getPlayerForceDeathmatch()) {
          // start deathmatch countdown
            this.deathmatchCountDownStartTick = this.gameTick;
            Bukkit.broadcastMessage("The deathmatch will begin in 30 seconds!");
            this.inGameState = InGameState.PRE_DEATHMATCH;
        }
        break;
        case PRE_DEATHMATCH:
            if (this.gameTick - this.deathmatchCountDownStartTick >= 20) {
                String message = "Deathmatch: " + (30 - (this.gameTick - this.deathmatchCountDownStartTick));
                Bukkit.broadcastMessage(message);
                for (Player p : this.getGame().getGameData().getPlayersAndSpectatorsList()) {
                    p.sendActionBar(message);
                }
            }
            if (30 - (this.gameTick - this.deathmatchCountDownStartTick) <= 1) {
                // START DM
                this.inGameState = InGameState.DEATHMATCH;
            }
            break;
      case DEATHMATCH:
        break;
      default:
        break;
    }

    if (this.gameTick >= HungerGamesGameConfiguration.getTimeGameMax()) {
      // end game
    }

    gameTick++;
  }

  @Override
  public void endPhase() {
    this.setActive(false);
    this.getGamePhaseRunnableTask().cancel();
  }

  public int getGameTick() {
    return gameTick;
  }

  public PlayerService getPlayerService() {
    return playerService;
  }

  public Kit hungerGamesSpectatorKit() {
    ItemStack compass = new ItemStack(Material.COMPASS);
    ItemMeta compassItemMeta = compass.getItemMeta();
    compassItemMeta.setDisplayName(ChatColor.GREEN + "Teleport");
    compass.setItemMeta(compassItemMeta);
    return new Kit("Spectator", Collections.singletonList(compass), null);
  }

  private void registerHooks() {
    new InGamePlayerJoinHandler(this);
    new InGamePlayerDeathHandler(this);
    new InGamePlayerMoveHandler(this);
  }

  public InGameSidebar getInGameSidebar() {
    return inGameSidebar;
  }

  public InGameState getInGameState() {
    return inGameState;
  }

  public enum InGameState {
    COUNTDOWN,
    NORMAL_PLAY,
      PRE_DEATHMATCH, // deathmatch countdown started
    DEATHMATCH
  }
}
