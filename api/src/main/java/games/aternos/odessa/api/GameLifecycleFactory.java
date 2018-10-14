package games.aternos.odessa.api;

public interface GameLifecycleFactory<T extends Game> {

    GameLifecycle create(T game);

}
