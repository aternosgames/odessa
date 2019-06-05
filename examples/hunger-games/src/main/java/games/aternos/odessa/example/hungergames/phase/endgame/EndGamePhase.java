package games.aternos.odessa.example.hungergames.phase.endgame;

import edu.umd.cs.findbugs.annotations.NonNull;
import games.aternos.odessa.engine.service.player.PlayerService;
import games.aternos.odessa.example.hungergames.phase.endgame.runnable.EndGameRunnable;
import games.aternos.odessa.gameapi.GameApi;
import games.aternos.odessa.gameapi.game.Game;
import games.aternos.odessa.gameapi.game.GameEndReason;
import games.aternos.odessa.gameapi.game.GameLifecycleManager;
import games.aternos.odessa.gameapi.game.GamePhase;

public class EndGamePhase extends GamePhase {

  private PlayerService playerService;

  private GameEndReason gameEndReason;

  public EndGamePhase(@NonNull GameLifecycleManager owner, Game game) {
    super(owner, game);
  }

  @Override
  protected void initialize() {
    this.playerService = new PlayerService(GameApi.getGameApi());
    this.setGamePhaseRunnable(new EndGameRunnable(this));
  }

  @Override
  public void startPhase() {
    this.setActive(true);
    this.setGamePhaseRunnableTask(this.getGamePhaseRunnable().runTaskTimer(GameApi.getGameApi(), 0, 20L));
  }

  @Override
  public void endPhase() {
    this.setActive(false);
    this.getGamePhaseRunnable().cancel();
    GameApi.getGameApi().getServer().shutdown();

  }

  @Override
  public void hook() {}
}
