package games.aternos.odessa.core.lifecycle;

import games.aternos.odessa.api.Game;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

/**
 * The type abstract game lifecycle. Updates game phases every {@code period} ticks.
 */
public class BukkitTaskGameLifecycle extends AbstractGameLifecycle {

    private BukkitTask thread;
    private long period;

    /**
     * Instantiates a new abstract game lifecycle. Updates game phases every {@code period} ticks.
     *
     * @param period the period between updates in ticks (20 ticks = 1 second)
     */
    public BukkitTaskGameLifecycle(long period) {
        this.period = period;
    }

    @Override
    public void start(Game game) {
        super.start(game);

        //The BukkitTaskGameLifecycle uses the bukkit scheduler for getting runnable to work
        this.thread = Bukkit.getScheduler().runTaskTimer(game.getPlugin(), this, period, period);
    }

    @Override
    public void stop() {
        this.thread.cancel();
    }

    public BukkitTask getBukkitTask() {
        return this.thread;
    }
}
