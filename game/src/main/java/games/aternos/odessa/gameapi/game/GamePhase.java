package games.aternos.odessa.gameapi.game;

import edu.umd.cs.findbugs.annotations.NonNull;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import javax.annotation.Nonnull;

abstract public class GamePhase {

  private final GameLifecycleManager owner;
  private final Game game;
  private GamePhase nextPhase;

  private BukkitRunnable gamePhaseRunnable;

  private BukkitTask gamePhaseRunnableTask;

  private boolean isActive;

  protected GamePhase(@NonNull GameLifecycleManager owner, Game game) {
    this.owner = owner;
    this.game = game;
    this.initialize();
  }

  protected abstract void initialize();

  public GamePhase getNextPhase() {
    return this.nextPhase;
  }

  public void setNextPhase(@Nonnull GamePhase nextPhase) {
    this.nextPhase = nextPhase;
  }

  public BukkitRunnable getGamePhaseRunnable() {
    return gamePhaseRunnable;
  }

  public void setGamePhaseRunnable(@Nonnull BukkitRunnable gamePhaseRunnable) {
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

  abstract public void hook();


  public GameLifecycleManager getOwner() {
    return owner;
  }

  public BukkitTask getGamePhaseRunnableTask() {
    return gamePhaseRunnableTask;
  }

  public void setGamePhaseRunnableTask(@Nonnull BukkitTask gamePhaseRunnableTask) {
    this.gamePhaseRunnableTask = gamePhaseRunnableTask;
  }

  public Game getGame() {
    return game;
  }
}
