package games.aternos.odessa.example.hungergames.phase;

import edu.umd.cs.findbugs.annotations.NonNull;
import games.aternos.odessa.engine.service.player.PlayerService;
import games.aternos.odessa.gameapi.GameApi;
import games.aternos.odessa.gameapi.game.GameLifecycleManager;
import games.aternos.odessa.gameapi.game.GamePhase;

public class InGamePhase extends GamePhase {

  private PlayerService playerService;

  public InGamePhase(@NonNull GameLifecycleManager owner) {
    super(owner);
  }

  @Override
  public void initialize() {
    playerService = new PlayerService(GameApi.getGameApi());
  }

  @Override
  public void startPhase() {
    this.setActive(true);
  }

  @Override
  public void endPhase() {
    this.setActive(false);
  }

  @Override
  public void hook() {

  }

  public PlayerService getPlayerService() {
    return playerService;
  }
}