package games.aternos.odessa.api.game;

import games.aternos.odessa.api.game.phase.GamePhase;
import org.bukkit.event.Listener;

import java.util.List;

public interface Game {

    void start();

    void stop();

    List<GamePhase> getGamePhases();

    void setGamePhases(List<GamePhase> gamePhases);

    GamePhase getActivePhase();

    void setActivePhase();

    GameData getGameData();

    void setGameData(GameData gameData);

    List<Listener> getGameListeners();

    void setGameListeners(List<Listener> gameListeners);

    default void addGameListener(Listener gameListener) {
        getGameListeners().add(gameListener);
    }

    default void addGamePhase(GamePhase gamePhase) {
        getGamePhases().add(gamePhase);
    }

}
