package games.aternos.odessa.core;

import games.aternos.odessa.api.Game;

/**
 * The type seconds game lifecycle.
 */
public class ThreadedGameLifecycle extends AbstractGameLifecycle {

    public ThreadedGameLifecycle(Game game) {
        super(game);
    }

    @Override
    public void start() {
        //TODO: implement ExecutorService
    }

    @Override
    public void stop() {
        //TODO: implement ExecutorService
    }
}
