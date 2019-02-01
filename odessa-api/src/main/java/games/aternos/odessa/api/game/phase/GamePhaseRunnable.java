package games.aternos.odessa.api.game.phase;

import org.bukkit.scheduler.BukkitRunnable;

public abstract class GamePhaseRunnable extends BukkitRunnable {

  private GamePhase gamePhase;

  public GamePhaseRunnable(GamePhase gamePhase) {
    this.gamePhase = gamePhase;
  }

  public GamePhase getGamePhase() {
    return gamePhase;
  }

  public void setGamePhase(GamePhase gamePhase) {
    this.gamePhase = gamePhase;
  }
}
