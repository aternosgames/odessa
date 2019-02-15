package games.aternos.odessa.gameapi.game;

import java.util.List;

public interface GameLifecycleManager {

  List<GamePhase> registeredPhases();

  void registerPhase(GamePhase gamePhase);

  GamePhase activePhase();

  void setActivePhase(GamePhase gamePhase);

  void nextPhase();



}
