package games.aternos.odessa.example.hungergames.phase.ingame;

import edu.umd.cs.findbugs.annotations.NonNull;
import games.aternos.odessa.engine.service.player.PlayerService;
import games.aternos.odessa.example.hungergames.phase.endgame.EndGamePhase;
import games.aternos.odessa.example.hungergames.phase.ingame.runnable.InGameRunnable;
import games.aternos.odessa.gameapi.GameApi;
import games.aternos.odessa.gameapi.game.Game;
import games.aternos.odessa.gameapi.game.GameLifecycleManager;
import games.aternos.odessa.gameapi.game.GamePhase;

public class InGamePhase extends GamePhase {

  private PlayerService playerService;

  public InGamePhase(@NonNull GameLifecycleManager owner, Game game) {
    super(owner, game);
    this.setNextPhase(new EndGamePhase(this.getOwner(), this.getGame()));
  }

  @Override
  public void initialize() {
    playerService = new PlayerService(GameApi.getGameApi());
    this.setGamePhaseRunnable(new InGameRunnable(this));
  }

  @Override
  public void startPhase() {
    this.setActive(true);
    playerService.dispersePlayers(
            this.getGame().getGameData().getPlayers(), this.getGame().getGameData().getGameArena());
    this.setGamePhaseRunnableTask(
            this.getGamePhaseRunnable().runTaskTimer(GameApi.getGameApi(), 0, 20L));
  }

  @Override
  public void endPhase() {
    this.setActive(false);
    this.getGamePhaseRunnableTask().cancel();
  }

  @Override
  public void hook() {
  }

  public PlayerService getPlayerService() {
    return playerService;
  }
}
