package games.aternos.odessa.core.lifecycle;

/**
 * The type seconds game lifecycle. Updates game phases every second.
 */
public class SecondsGameLifecycle extends AbstractGameLifecycle {

    /**
     * Instantiates a new seconds game lifecycle.  Updates game phases every second.
     */
    public SecondsGameLifecycle() {
        super(20);
    }

}
