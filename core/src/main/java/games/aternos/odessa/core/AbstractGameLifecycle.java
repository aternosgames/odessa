package games.aternos.odessa.core;

import games.aternos.odessa.api.Game;
import games.aternos.odessa.api.GameLifecycle;

/**
 * The type Abstract game lifecycle.
 */
public abstract class AbstractGameLifecycle implements GameLifecycle {

    private Game game;

    public AbstractGameLifecycle(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return this.game;
    }

    @Override
    public void run() {
        //Every GameLifecycle should update the game.
        game.update();

    }
}
