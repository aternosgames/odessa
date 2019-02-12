package games.aternos.odessa.game;

import games.aternos.odessa.game.phase.GamePhase;

public interface GameFactory {

  StartPhaseFactory phases();

  interface StartPhaseFactory {

    NextPhaseFactory startPhase(Class<? extends GamePhase> gamePhaseClass);

    interface NextPhaseFactory{

      NextPhaseFactory nextPhase(Class<? extends GamePhase> gamePhaseClass);

    }

  }

}
