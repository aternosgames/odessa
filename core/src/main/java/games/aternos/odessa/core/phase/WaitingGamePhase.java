package games.aternos.odessa.core.phase;

import com.google.common.base.Preconditions;
import games.aternos.odessa.api.Game;
import games.aternos.odessa.api.phase.GamePhase;
import games.aternos.odessa.api.phase.WaitingCondition;

/**
 * This game phase will wait until {@link WaitingCondition#check()} returns true and
 * proceed with the given {@code nextPhase}.
 */
public class WaitingGamePhase extends AbstractGamePhase {

    private Game game;
    private GamePhase nextPhase;
    private WaitingCondition condition;

    /**
     * Instantiates a new waiting game phase.
     * The game will continue with {@code nextPhase} when {@code condition} returns true.
     *
     * @param condition the condition
     * @param nextPhase the next phase
     */
    public WaitingGamePhase(WaitingCondition condition, GamePhase nextPhase) {
        this.condition = condition;
        this.nextPhase = nextPhase;
    }

    @Override
    public void endPhase() {
        /* Do nothing */
    }

    @Override
    public void startPhase() {
        this.game = game;
    }

    @Override
    public void update() {
        if(condition.check())
            game.advancePhase(this.nextPhase);
    }
}
