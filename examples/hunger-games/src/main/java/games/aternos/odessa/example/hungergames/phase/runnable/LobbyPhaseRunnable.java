package games.aternos.odessa.example.hungergames.phase.runnable;

import games.aternos.odessa.gameapi.game.GamePhase;
import org.bukkit.scheduler.BukkitRunnable;

public class LobbyPhaseRunnable extends BukkitRunnable {

  private final GamePhase owner;

  public LobbyPhaseRunnable(GamePhase owner){
    this.owner = owner;
  }

  @Override
  public void run() {
    // todo: logic
  }

}
