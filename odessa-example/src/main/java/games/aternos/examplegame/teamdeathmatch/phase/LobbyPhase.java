package games.aternos.examplegame.teamdeathmatch.phase;

import games.aternos.odessa.api.game.phase.SelfTerminatingConditionalGamePhase;
import games.aternos.odessa.core.OdessaEngine;
import games.aternos.odessa.core.engine.GameManager;
import games.aternos.odessa.core.engine.lobby.GameLobby;

public class LobbyPhase implements SelfTerminatingConditionalGamePhase {

  private GameLobby gameLobby;
  private String phaseName;
  private boolean isAlive;

  @Override
  public void start() {
    phaseName = "Lobby";
    gameLobby = new GameLobby();
    gameLobby.startLobby(GameManager.getInstance().getActiveGame(), OdessaEngine.getOdessaEngine());
    isAlive = true;
  }


  @Override
  public boolean check() {
    if (gameLobby.canStartGame()) {
      this.end();
      return true;
    } else {
      return false;
    }
  }

  @Override
  public String getPhaseName() {
    return phaseName;
  }

  @Override
  public void setPhaseName(String s) {
    this.phaseName = s;
  }

  @Override
  public void end() {
    isAlive = false;
    // TODO: Next Phase
  }

  @Override
  public boolean isAlive() {
    return this.isAlive;
  }

  @Override
  public void setAlive(Boolean alive) {
    this.isAlive = alive;
  }
}
