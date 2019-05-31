package games.aternos.odessa.example.hungergames.phase.lobby;

import edu.umd.cs.findbugs.annotations.NonNull;
import games.aternos.odessa.engine.lobby.GameLobbySystem;
import games.aternos.odessa.example.hungergames.phase.ingame.InGamePhase;
import games.aternos.odessa.example.hungergames.phase.lobby.runnable.LobbyPhaseRunnable;
import games.aternos.odessa.gameapi.GameApi;
import games.aternos.odessa.gameapi.game.Game;
import games.aternos.odessa.gameapi.game.GameLifecycleManager;
import games.aternos.odessa.gameapi.game.GamePhase;

public class LobbyPhase extends GamePhase {

  private GameLobbySystem gameLobbySystem;


  public LobbyPhase(@NonNull GameLifecycleManager owner, Game game) {
    super(owner, game);
    this.setNextPhase(new InGamePhase(this.getOwner(), this.getGame()));
  }

  @Override
  public void initialize() {
    this.setGamePhaseRunnable(new LobbyPhaseRunnable(this));
    gameLobbySystem = new GameLobbySystem(this.getOwner(), GameApi.getGameApi(), GameApi.getGameApi().getGame().getGameConfiguration());
  }

  @Override
  public void startPhase() {
    this.setActive(true);
    this.setGamePhaseRunnableTask(this.getGamePhaseRunnable().runTaskTimer(GameApi.getGameApi(), 0, 20L));
    this.gameLobbySystem.startLobby();
  }

  @Override
  public void endPhase() {
    this.setActive(false);
    this.getGamePhaseRunnableTask().cancel();
    this.gameLobbySystem.stopLobby();
    gameLobbySystem = null;
  }

  @Override
  public void hook() {
    this.gameLobbySystem.getLobbyController().lobbyTick();
  }
}
