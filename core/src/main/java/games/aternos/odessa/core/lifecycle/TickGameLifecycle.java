package games.aternos.odessa.core.lifecycle;

import games.aternos.odessa.api.Game;
import games.aternos.odessa.api.lifecycle.GameLifecycle;
import games.aternos.odessa.core.phase.AbstractGamePhase;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

/**
 * The type Tick game lifecycle. Updates game phases every tick.
 */
public class TickGameLifecycle extends AbstractGameLifecycle {

    /**
     * Instantiates a new Tick game lifecycle.
     */
    public TickGameLifecycle() {
        super(1);
    }
}
