package games.aternos.odessa.api.game.phase;

public interface SelfTerminatingConditionalGamePhase extends GamePhase {

  @Override
  default void update() {
    if (this.check()) {
      this.end();
    }
  }

  boolean check();

}
