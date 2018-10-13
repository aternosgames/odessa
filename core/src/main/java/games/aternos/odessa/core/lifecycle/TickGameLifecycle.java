package games.aternos.odessa.core.lifecycle;

/**
 * The type Tick game lifecycle. Updates game phases every tick.
 */
public class TickGameLifecycle extends BukkitTaskGameLifecycle {

    /**
     * Instantiates a new Tick game lifecycle. Updates game phases every tick.
     */
    public TickGameLifecycle() {
        super(1);
    }
}
