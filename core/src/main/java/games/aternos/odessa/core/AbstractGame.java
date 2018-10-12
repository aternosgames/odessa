package games.aternos.odessa.core;

import com.google.common.base.Preconditions;
import games.aternos.odessa.api.Game;
import games.aternos.odessa.api.phase.GamePhase;
import games.aternos.odessa.core.phase.DefaultGamePhase;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

/**
 * The abstract class for main game logic.
 */
public abstract class AbstractGame implements Game, Runnable {

    private BukkitTask thread;

    protected String name;
    protected GamePhase phase; //Current game phase

    /**
     * Instantiates a new AbstractGame.
     *
     * @param name         the name of the game
     * @param initialPhase the initial game phase, if null {@link DefaultGamePhase} will be used
     */
    public AbstractGame(String name, GamePhase initialPhase) {
        Preconditions.checkNotNull(name, "'name' cannot be null");

        this.name = name;
        this.phase = (initialPhase == null ? new DefaultGamePhase() : initialPhase);
    }

    @Override
    public void run() {
        //Repeated update of game phase
        this.phase.update();
    }

    @Override
    public void start(Plugin plugin) {
        //Call startPhase of initial GamePhase
        this.phase.startPhase(this);
        //Start thread that will call #update() on current game phase
        this.thread = Bukkit.getScheduler().runTaskTimer(plugin, this, 0, 20);
    }

    @Override
    public void end() {
        this.thread.cancel();
    }

    @Override
    public void advancePhase(GamePhase nextPhase) {
        Preconditions.checkNotNull(nextPhase, "'nextPhase' cannot be null");

        //End current phase
        phase.endPhase();
        //Set current phase to nextPhase
        this.phase = nextPhase;
        //Start next phase
        phase.startPhase(this);
    }

    @Override
    public GamePhase getCurrentPhase() {
        return this.phase;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
