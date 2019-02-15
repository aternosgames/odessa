package games.aternos.odessa.gameapi.game;

import edu.umd.cs.findbugs.annotations.NonNull;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

abstract public class GamePhase {

  private GameLifecycleManager owner;

  private GamePhase nextPhase;

  private BukkitRunnable gamePhaseRunnable;

  private BukkitTask gamePhaseRunnableTask;

  private boolean isActive;

  public GamePhase(@NonNull GameLifecycleManager owner) {
    this.owner = owner;
    this.initialize();
  }

  abstract public void initialize();

  public GamePhase getNextPhase() {
    return this.nextPhase;
  }

  public void setNextPhase(GamePhase nextPhase) {
    this.nextPhase = nextPhase;
  }

  public BukkitRunnable getGamePhaseRunnable() {
    return gamePhaseRunnable;
  }

  public void setGamePhaseRunnable(BukkitRunnable gamePhaseRunnable) {
    this.gamePhaseRunnable = gamePhaseRunnable;
  }

  public boolean isActive() {
    return this.isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  abstract public void startPhase();

  abstract public void endPhase();


  public GameLifecycleManager getOwner() {
    return owner;
  }

  public BukkitTask getGamePhaseRunnableTask() {
    return gamePhaseRunnableTask;
  }

  public void setGamePhaseRunnableTask(BukkitTask gamePhaseRunnableTask) {
    this.gamePhaseRunnableTask = gamePhaseRunnableTask;
  }
}
