package games.aternos.odessa.core.phase;

import com.google.common.base.Preconditions;
import games.aternos.odessa.api.Game;
import games.aternos.odessa.api.phase.GamePhase;

public abstract class AbstractGamePhase implements GamePhase {

    private Game game;

    @Override
    public void startPhase(Game game) {
        Preconditions.checkNotNull(game, "'game' cannot be null");
        this.game = game;

        this.startPhase();
    }

    public Game getGame() { return this.game; }

    public abstract void startPhase();
}
