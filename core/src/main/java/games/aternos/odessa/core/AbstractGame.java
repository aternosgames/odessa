package games.aternos.odessa.core;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import games.aternos.odessa.api.Game;
import games.aternos.odessa.api.phase.GamePhase;
import games.aternos.odessa.core.phase.DefaultGamePhase;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

/**
 * The abstract class for main game logic.
 */
public abstract class AbstractGame implements Game, Runnable {

    private BukkitTask thread;
    private List<Listener> tempListeners;

    protected Plugin plugin; //Plugin that started the game
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

        //List containing all temp listeners
        tempListeners = Lists.newArrayList();
    }

    @Override
    public void run() {
        //Repeated update of game phase
        this.phase.update();
    }

    @Override
    public void start(Plugin plugin) {
        this.plugin = plugin;

        //Call startPhase of initial GamePhase
        this.phase.startPhase(this);
        //Start thread that will call #update() on current game phase
        this.thread = Bukkit.getScheduler().runTaskTimer(plugin, this, 0, 20);
    }

    @Override
    public void end() {
        //End thread which updated game phases
        this.thread.cancel();
    }

    @Override
    public void advancePhase(GamePhase nextPhase) {
        Preconditions.checkNotNull(nextPhase, "'nextPhase' cannot be null");

        //End current phase
        phase.endPhase();
        //Unregister all temp listeners
        tempListeners.forEach(listener -> HandlerList.unregisterAll(listener));
        tempListeners.clear();

        //Set current phase to nextPhase
        this.phase = nextPhase;
        //Start next phase
        phase.startPhase(this);
    }

    @Override
    public void registerTempListener(Listener listener) {
        //Add listener to temp listener list
        tempListeners.add(listener);
        //Register listener into bukkit
        Bukkit.getServer().getPluginManager().registerEvents(listener, this.plugin);
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
