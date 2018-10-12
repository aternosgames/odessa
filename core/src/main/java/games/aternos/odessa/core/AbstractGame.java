package games.aternos.odessa.core;

import games.aternos.odessa.api.Game;
import games.aternos.odessa.api.phase.GamePhase;
import games.aternos.odessa.core.phase.InitialGamePhase;

/**
 * The abstract class for main game logic.
 */
public abstract class AbstractGame implements Game {

    protected String name;
    protected GamePhase phase; //Current game phase

    /**
     * Instantiates a new AbstractGame with using {@link InitialGamePhase} as initial game phase.
     *
     * @param name the name
     */
    public AbstractGame(String name) {
        this(name, new InitialGamePhase());
    }

    /**
     * Instantiates a new AbstractGame.
     *
     * @param name         the name of the game
     * @param initialPhase the initial game phase
     */
    public AbstractGame(String name, GamePhase initialPhase) {
        this.name = name;
        this.phase = initialPhase;
    }

    @Override
    public void advancePhase(GamePhase nextPhase) {
        //End current phase
        phase.endPhase();
        //Set current phase to nextPhase
        this.phase = nextPhase;
        //Start next phase
        phase.startPhase();
    }

    @Override
    public GamePhase getCurrentPhase() {
        return this.phase;
    }
}
