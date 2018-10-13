package games.aternos.odessa.core.phase;

import com.google.common.base.Preconditions;
import games.aternos.odessa.api.Game;
import games.aternos.odessa.api.phase.GamePhase;

/**
 * The type Abstract game phase.
 */
public abstract class AbstractGamePhase implements GamePhase {

    private Game game;
    private int time;

    @Override
    public void startPhase(Game game) {
        Preconditions.checkNotNull(game, "'game' cannot be null");
        this.game = game;

        this.startPhase();
    }

    /**
     * Gets game.
     *
     * @return the game
     */
    public Game getGame() { return this.game; }

    /**
     * Start phase.
     */
    public abstract void startPhase();

    @Override
    public void update() {
        time++;
        updatePhase();;
    }

    /**
     * Gets age of phase in seconds (counts how often {@code update()} has been called).
     *
     * @return the age
     */
    public int getAge() { return this.time; }

    /**
     * Update phase.
     */
    public abstract void updatePhase();
}
