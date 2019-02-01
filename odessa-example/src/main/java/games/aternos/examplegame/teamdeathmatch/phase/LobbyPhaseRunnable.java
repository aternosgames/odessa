package games.aternos.examplegame.teamdeathmatch.phase;

import games.aternos.odessa.api.game.phase.GamePhase;
import games.aternos.odessa.api.game.phase.GamePhaseRunnable;
import games.aternos.odessa.core.Debug;

/**
 * TODO: Add timer to process checks for the lobby.
 */
public class LobbyPhaseRunnable extends GamePhaseRunnable {


  public LobbyPhaseRunnable(GamePhase gamePhase) {
    super(gamePhase);
  }

  @Override
  public void run() {
    getGamePhase().update();
    Debug.$("RUN");
  }
}
