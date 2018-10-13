package games.aternos.odessa.core.phase;

import games.aternos.odessa.api.Game;
import games.aternos.odessa.api.phase.GamePhase;

/**
 * This is the default game phase. Its just a placeholder
 * not containing any logic. It will be used when a game
 * gets instantiated without an initial phase.
 */
public class DefaultGamePhase extends AbstractGamePhase {

    @Override
    public void endPhase() {
        /* do nothing, wait for next game phase */
    }

    @Override
    public void startPhase() {
        /* do nothing, wait for next game phase */
    }

    @Override
    public void updatePhase() {
        /* do nothing, wait for next game phase */
    }
}
