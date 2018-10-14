package games.aternos.odessa.core;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import games.aternos.odessa.api.Game;
import games.aternos.odessa.api.chat.ChatFactory;
import games.aternos.odessa.api.GameLifecycle;
import games.aternos.odessa.api.GameLifecycleFactory;
import games.aternos.odessa.api.phase.GamePhase;
import games.aternos.odessa.api.team.TeamFactory;
import games.aternos.odessa.core.phase.DefaultGamePhase;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * The abstract class for main game logic.
 */
public class BasicGame implements Game {

    //private BukkitTask thread;
    private GameLifecycle gameLifecycle;
    private List<Listener> tempListeners;
    private Plugin plugin; //Plugin that started the game
    private GamePhase phase; //Current game phase

    private TeamFactory teamFactory;
    private ChatFactory chatFactory;

    protected String name;

    /**
     * Instantiates a new odessa game using GameBuilder.
     *
     * @param builder the builder
     */
    public BasicGame(GameBuilder builder) {
        this(builder.name, builder.initialPhase, builder.teamFactory, builder.chatFactory, builder.gameLifecycleFactory);
    }

    /**
     * Instantiates a new BasicGame.
     *
     * @param name         the name of the game
     * @param initialPhase the initial game phase, if null {@link DefaultGamePhase} will be used
     * @param teamFactory  the team factory to use
     */
    public BasicGame(String name, GamePhase initialPhase, TeamFactory teamFactory, ChatFactory chatFactory, GameLifecycleFactory gameLifecycleFactory) {
        Preconditions.checkNotNull(name, "'name' cannot be null");
        Preconditions.checkNotNull(teamFactory, "'teamFactory' cannot be null");
        Preconditions.checkNotNull(chatFactory, "'chatFactory' cannot be null");

        this.name = name;
        this.phase = (initialPhase == null ? new DefaultGamePhase() : initialPhase);
        this.teamFactory = teamFactory;
        this.chatFactory = chatFactory;
        this.gameLifecycle = gameLifecycleFactory.create(this);

        //List containing all temp listeners
        tempListeners = Lists.newArrayList();
    }

    @Override
    public void start(Plugin plugin) {
        this.plugin = plugin;

        //Call startPhase of initial GamePhase
        this.phase.startPhase(this);
        //Start thread that will call #update() on current game phase
      //  this.thread = Bukkit.getScheduler().runTaskTimer(plugin, this, 0, 20);
        this.gameLifecycle.start();
    }

    @Override
    public void end() {
        this.gameLifecycle.stop();
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
    public void update() {
        this.phase.update();
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

    @Override
    public TeamFactory getTeamFactory() {
        return this.teamFactory;
    }

    @Override
    public ChatFactory getChatFactory() { return this.chatFactory; }

    @Override
    public int getMaxPlayers() { return this.teamFactory.getOptions().getMaxPlayers(); }

    @Override
    public Plugin getPlugin() { return this.plugin; }

    /**
     * The builder for this class.
     */
    public static class GameBuilder {

        private GamePhase initialPhase = new DefaultGamePhase();

        private String name;
        private TeamFactory teamFactory;
        private ChatFactory chatFactory;
        private GameLifecycleFactory gameLifecycleFactory;

        public GameBuilder setGameLifecycleFactory(GameLifecycleFactory gameLifecycleFactory) {
            this.gameLifecycleFactory = gameLifecycleFactory;
            return this;
        }

        public GameBuilder setInitialPhase(GamePhase initialPhase) {
            this.initialPhase = initialPhase;
            return this;
        }

        public GameBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public GameBuilder setTeamFactory(TeamFactory teamFactory) {
            this.teamFactory = teamFactory;
            return this;
        }

        public GameBuilder setChatFactory(ChatFactory chatFactory) {
            this.chatFactory = chatFactory;
            return this;
        }

        public BasicGame build() { return new BasicGame(this); }
    }
}
