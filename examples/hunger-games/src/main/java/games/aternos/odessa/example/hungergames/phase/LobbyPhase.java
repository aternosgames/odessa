package games.aternos.odessa.example.hungergames.phase;

import edu.umd.cs.findbugs.annotations.NonNull;
import games.aternos.odessa.example.hungergames.HungerGames;
import games.aternos.odessa.example.hungergames.phase.runnable.LobbyPhaseRunnable;
import games.aternos.odessa.gameapi.GameApi;
import games.aternos.odessa.gameapi.game.GameLifecycleManager;
import games.aternos.odessa.gameapi.game.GamePhase;

// todo: this class
public class LobbyPhase extends GamePhase {


  public LobbyPhase(@NonNull GameLifecycleManager owner) {
    super(owner);
  }

  @Override
  public void initialize() {
    this.setNextPhase(new InGamePhase(this.getOwner()));
    this.setGamePhaseRunnable(new LobbyPhaseRunnable(this));
  }

  @Override
  public void startPhase() {
    this.setActive(true);
    this.setGamePhaseRunnableTask(this.getGamePhaseRunnable().runTaskTimer(GameApi.getGameApi(), 0, 20L));
  }

  @Override
  public void endPhase() {
    this.setActive(false);
    this.getGamePhaseRunnableTask().cancel();
  }
}
