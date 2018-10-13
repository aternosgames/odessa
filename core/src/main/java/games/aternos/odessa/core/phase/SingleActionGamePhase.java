package games.aternos.odessa.core.phase;

import com.google.common.base.Preconditions;
import games.aternos.odessa.api.Game;
import games.aternos.odessa.api.phase.GamePhase;

/**
 * This game phase will run whatever is supplied by {@code toRun} and then
 * instantly continue with {@code nextPhase}.
 */
public class SingleActionGamePhase extends AbstractGamePhase {

    private Runnable toRun;
    private GamePhase nextPhase;

    /**
     * Instantiates a new single action game phase.
     *
     * @param toRun     the to run
     * @param nextPhase the next phase
     */
    public SingleActionGamePhase(Runnable toRun, GamePhase nextPhase) {
        Preconditions.checkNotNull(toRun, "'toRun' cannot be null");
        Preconditions.checkNotNull(nextPhase, "'nextPhase' cannot be null");

        this.toRun = toRun;
        this.nextPhase = nextPhase;
    }

    @Override
    public void endPhase() {
        /* Do nothing */
    }

    @Override
    public void startPhase() {
        toRun.run();
        getGame().advancePhase(this.nextPhase);
    }

    @Override
    public void updatePhase() {
        /* Do nothing */
    }
}
