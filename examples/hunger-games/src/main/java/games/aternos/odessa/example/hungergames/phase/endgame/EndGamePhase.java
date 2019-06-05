package games.aternos.odessa.example.hungergames.phase.endgame;

import edu.umd.cs.findbugs.annotations.NonNull;
import games.aternos.odessa.engine.service.player.PlayerService;
import games.aternos.odessa.example.hungergames.phase.endgame.runnable.EndGameRunnable;
import games.aternos.odessa.gameapi.GameApi;
import games.aternos.odessa.gameapi.game.Game;
import games.aternos.odessa.gameapi.game.GameEndReason;
import games.aternos.odessa.gameapi.game.GameLifecycleManager;
import games.aternos.odessa.gameapi.game.GamePhase;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class EndGamePhase extends GamePhase {

  private PlayerService playerService;

  private GameEndReason gameEndReason;

    private String winner;

  public EndGamePhase(@NonNull GameLifecycleManager owner, Game game) {
    super(owner, game);
  }

  @Override
  protected void initialize() {
    this.playerService = new PlayerService(GameApi.getGameApi());
    this.setGamePhaseRunnable(new EndGameRunnable(this));
  }

    private int tick;

    @Override
    public void endPhase() {
        this.setActive(false);
        this.getGamePhaseRunnable().cancel();
        GameApi.getGameApi().getServer().shutdown();
    }

  @Override
  public void startPhase() {
    this.setActive(true);
      this.gameEndReason = this.getGame().getGameData().getGameEndReason();

      if (this.gameEndReason == null) {
          this.gameEndReason = GameEndReason.ERROR;
      }
      this.setGamePhaseRunnableTask(
              this.getGamePhaseRunnable().runTaskTimer(GameApi.getGameApi(), 0, 20L));
      this.winner = this.getGame().getGameData().getPlayers().get(0).getName();
      if (this.winner == null) {
          this.winner = "Unknown";
      }
  }

  @Override
  public void hook() {

      if (tick >= 10) {
          this.endPhase();
      }

      tick++;
      switch (this.gameEndReason) {
          case ERROR:
              Bukkit.broadcastMessage(ChatColor.RED + "An error caused the game to stop!");
              break;
          case PLAYER_WON:
              Bukkit.broadcastMessage(ChatColor.GREEN + this.winner + " Won!");
              break;
          case TIME_LIMIT:
              Bukkit.broadcastMessage(ChatColor.RED + "An admin stopped the game!");
              break;
          case ZERO_PLAYERS:
              Bukkit.broadcastMessage("Error.");
              break;
          case ADMINISTRATIVE_STOP:
              Bukkit.broadcastMessage("An admin stopped the game.");
              break;
          default:
              Bukkit.broadcastMessage("Game over.");
      }
  }
}
