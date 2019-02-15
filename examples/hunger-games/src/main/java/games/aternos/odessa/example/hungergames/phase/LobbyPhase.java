package games.aternos.odessa.example.hungergames.phase;

import edu.umd.cs.findbugs.annotations.NonNull;
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
    // todo: make and set runnable
  }

  @Override
  public void startPhase() {
    this.setActive(true);
    // todo: start runable
  }

  @Override
  public void endPhase() {
    this.setActive(false);
    // todo: stop runnable
  }
}
