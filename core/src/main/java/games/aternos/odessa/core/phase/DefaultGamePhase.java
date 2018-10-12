package games.aternos.odessa.core.phase;

import games.aternos.odessa.api.Game;
import games.aternos.odessa.api.phase.GamePhase;

/**
 * This is the default game phase.
 */
public class DefaultGamePhase extends GamePhase {

    /**
     * Instantiates a new DefaultGamePhase. This phase will be used as default if no
     * phase was defined whilst instantiation of {@link games.aternos.odessa.core.AbstractGame}.
     *
     * @param game the belonging game
     */
    public DefaultGamePhase(Game game) {
        super(game);
    }

    @Override
    public void endPhase() {
        /* do nothing, wait for next game phase */
    }

    @Override
    public void startPhase() {
        /* do nothing, wait for next game phase */
    }

    @Override
    public void update() {
        /* do nothing, wait for next game phase */
    }
}
