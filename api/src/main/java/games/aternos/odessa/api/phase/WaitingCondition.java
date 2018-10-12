package games.aternos.odessa.api.phase;

/**
 * Simple function interface for creating waiting conditions.
 */
@FunctionalInterface
public interface WaitingCondition {

    /**
     * Game will proceed if {@code check} returns true.
     *
     * @return the boolean
     */
    public boolean check();

}
