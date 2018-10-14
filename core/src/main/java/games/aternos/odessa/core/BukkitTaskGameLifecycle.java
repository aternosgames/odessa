package games.aternos.odessa.core;

import games.aternos.odessa.api.Game;
import games.aternos.odessa.api.GameLifecycle;
import games.aternos.odessa.api.GameLifecycleFactory;
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
     * @param game   the game
     * @param period the period between updates in ticks (20 ticks = 1 second)
     */
    public BukkitTaskGameLifecycle(Game game, long period) {
        super(game);
        this.period = period;
    }

    @Override
    public void start() {
        //The BukkitTaskGameLifecycle uses the bukkit scheduler for getting runnable to work
        this.thread = Bukkit.getScheduler().runTaskTimer(getGame().getPlugin(), this, period, period);
    }

    @Override
    public void stop() {
        this.thread.cancel();
    }

    /**
     * Gets the bukkit task.
     *
     * @return the bukkit task
     */
    public BukkitTask getBukkitTask() {
        return this.thread;
    }

    /**
     * The factory for creating this lifecycle.
     */
    public static class BukkitTaskGameLifecycleFactory implements GameLifecycleFactory {

        private long period;

        /**
         * Instantiates a new Bukkit task game lifecycle factory.
         *
         * @param period the period
         */
        public BukkitTaskGameLifecycleFactory(long period) {
            this.period = period;
        }

        @Override
        public GameLifecycle create(Game game) {
            return new BukkitTaskGameLifecycle(game, period);
        }
    }
}
