package games.aternos.odessa.api.game.phase;

public interface SelfTerminatingConditionalGamePhase extends GamePhase {

  @Override
  default void update() {
    if (check()) {
      end();
    }
  }

  boolean check();

}
