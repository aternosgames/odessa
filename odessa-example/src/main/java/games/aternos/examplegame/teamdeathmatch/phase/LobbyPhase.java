package games.aternos.examplegame.teamdeathmatch.phase;

import games.aternos.odessa.api.game.phase.GamePhaseRunnable;
import games.aternos.odessa.api.game.phase.SelfTerminatingConditionalGamePhase;
import games.aternos.odessa.core.OdessaEngine;
import games.aternos.odessa.core.engine.GameManager;
import games.aternos.odessa.core.engine.lobby.GameLobby;

public class LobbyPhase implements SelfTerminatingConditionalGamePhase {

  private GameLobby gameLobby;
  private String phaseName;
  private boolean isAlive;
  private GamePhaseRunnable gamePhaseRunable;

  /**
   * Called at the created of lobby phase. Starts up the Game Lobby from engine.
   */
  @Override
  public void start() {
    phaseName = "Lobby";
    gameLobby = new GameLobby();
    gameLobby.startLobby(GameManager.getInstance().getActiveGame(), OdessaEngine.getOdessaEngine());
    isAlive = true;
    this.gamePhaseRunable = (GamePhaseRunnable) new LobbyPhaseRunnable(this).runTaskTimer(OdessaEngine.getOdessaEngine(), 0L, 20L);
  }

  /**
   * Called by LobbyPhaseRunnable to check if the phase can end.
   */
  @Override
  public void update() {
    if (this.check()) {
      this.end();
    }
  }

  /**
   * Returns if the game lobby from engine says it can move on...has players etc.
   */
  @Override
  public boolean check() {
    return gameLobby.canStartGame();
  }

  /**
   * Returns the phase name.
   */
  @Override
  public String getPhaseName() {
    return phaseName;
  }

  /**
   * Sets the phase name.
   */
  @Override
  public void setPhaseName(String s) {
    this.phaseName = s;
  }

  /**
   * Ends the game phase. Moves on to the next.
   */
  @Override
  public void end() {
    this.isAlive = false;
    this.getGamePhaseRunable().cancel();
    // TODO: Next Phase
  }

  /**
   * Returns if the Game Phase is active.
   */
  @Override
  public boolean isAlive() {
    return this.isAlive;
  }

  /**
   * Sets if the Game Phase is active and the runnable is alive.
   */
  @Override
  public void setAlive(Boolean alive) {
    this.isAlive = alive;
  }

  /**
   * Returns the Game Phase Runnable of the Phase...the heartbeat.
   */
  @Override
  public GamePhaseRunnable getGamePhaseRunable() {
    return this.gamePhaseRunable;
  }

  /**
   * Sets the Game Phase Runable.
   */
  @Override
  public void setGamePhaseRunable(GamePhaseRunnable gamePhaseRunable) {
    this.gamePhaseRunable = gamePhaseRunable;
  }
}
