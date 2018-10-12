package games.aternos.odessa.core.phase;

import games.aternos.odessa.api.Game;
import games.aternos.odessa.api.phase.GamePhase;

/**
 * This is the default game phase. Its just a placeholder
 * not containing any logic. It will be used when a game
 * gets instantiated without an initial phase.
 */
public class DefaultGamePhase implements GamePhase {

    @Override
    public void endPhase() {
        /* do nothing, wait for next game phase */
    }

    @Override
    public void startPhase(Game game) {
        /* do nothing, wait for next game phase */
    }

    @Override
    public void update() {
        /* do nothing, wait for next game phase */
    }
}