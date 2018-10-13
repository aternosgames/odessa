package games.aternos.odessa.api.lifecycle;

import games.aternos.odessa.api.Game;

public interface GameLifecycleFactory<T extends Game> {

    GameLifecycle create(T game);

}
