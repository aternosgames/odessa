package games.aternos.odessa.api.phase;

public interface TimedGamePhase extends GamePhase {

    /**
     * Gets age of phase in seconds (counts how often {@code update()} has been called).
     *
     * @return the age
     */
    int getAge();
}
