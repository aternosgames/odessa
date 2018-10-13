package games.aternos.odessa.core.lifecycle;

import games.aternos.odessa.api.Game;
import games.aternos.odessa.api.lifecycle.GameLifecycle;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

/**
 * The type abstract game lifecycle. Updates game phases every {@code period} ticks.
 */
public abstract class AbstractGameLifecycle implements GameLifecycle {

    private Game game;
    private BukkitTask thread;
    private long period;

    /**
     * Instantiates a new abstract game lifecycle. Updates game phases every {@code period} ticks.
     *
     * @param period the period
     */
    public AbstractGameLifecycle(long period) {
        this.period = period;
    }

    @Override
    public void start(Game game) {
        this.game = game;
        this.thread = Bukkit.getScheduler().runTaskTimer(game.getPlugin(), this, period, period);
    }

    @Override
    public void stop() {
        this.thread.cancel();
    }

    @Override
    public BukkitTask getBukkitTask() {
        return this.thread;
    }

    @Override
    public void run() {
        game.getCurrentPhase().update();
    }
}
