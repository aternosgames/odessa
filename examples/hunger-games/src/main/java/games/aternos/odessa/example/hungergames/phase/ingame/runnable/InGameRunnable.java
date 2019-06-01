package games.aternos.odessa.example.hungergames.phase.ingame.runnable;

import games.aternos.odessa.gameapi.game.GamePhase;
import org.bukkit.scheduler.BukkitRunnable;

public class InGameRunnable extends BukkitRunnable {

  private final GamePhase owner;

  public InGameRunnable(GamePhase owner) {
    this.owner = owner;
  }

  @Override
  public void run() {
    this.owner.hook();
  }
}
