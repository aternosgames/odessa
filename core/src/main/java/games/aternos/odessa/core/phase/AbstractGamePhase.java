package games.aternos.odessa.core.phase;

import com.google.common.base.Preconditions;
import games.aternos.odessa.api.Game;
import games.aternos.odessa.api.phase.GamePhase;

/**
 * The type Abstract game phase.
 */
public abstract class AbstractGamePhase implements GamePhase {

    private Game game;

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
        updatePhase();
    }

    /**
     * Update phase.
     */
    public abstract void updatePhase();
}
