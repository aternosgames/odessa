package games.aternos.odessa.api.game.phase;

public interface LimitedGamePhase extends GamePhase {

    /**
     * Returns the time the Limited Phase is alive.
     */
    Integer getTimeActive();

    /**
     * Sets the time the Phase is alive.
     */
    void setTimeActive(Integer active);

    /**
     * Returns the maximum time the Phase can be active.
     */
    Integer maxActiveTime();

    /**
     * Sets the Maximum active time of a phase.
     */
    void setMaxActiveTime(Integer maxActiveTime);


}
