package games.aternos.odessa.core.lifecycle;

import games.aternos.odessa.api.Game;
import games.aternos.odessa.api.lifecycle.GameLifecycle;

/**
 * The type Abstract game lifecycle.
 */
public abstract class AbstractGameLifecycle implements GameLifecycle {

    private Game game;

    /**
     * If you override this, keep in mind to call {@code super.start(Game)} so {@code game} gets initialized.
     *
     * @param game the game
     */
    @Override
    public void start(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        //Every GameLifecycle should update the game.
        game.update();
    }
}
