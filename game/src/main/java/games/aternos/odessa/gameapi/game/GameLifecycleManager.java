package games.aternos.odessa.gameapi.game;

import java.util.List;

abstract public class GameLifecycleManager {
  GamePhase activePhase;

  public GameLifecycleManager(GamePhase initalPhase){
    setActivePhase(initalPhase);
  }

  public GamePhase getActivePhase(){
    return this.activePhase;
  }

  public void setActivePhase(GamePhase gamePhase){
    this.activePhase = gamePhase;
    this.activePhase.startPhase();
  }

  public void nextPhase(){
   this.activePhase.endPhase();
   activePhase = this.activePhase.nextPhase();
  }



}
