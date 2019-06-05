package games.aternos.odessa.example.hungergames.phase.endgame.runnable;

import games.aternos.odessa.gameapi.game.GamePhase;
import org.bukkit.scheduler.BukkitRunnable;

public class EndGameRunnable extends BukkitRunnable {
    private final GamePhase owner;

    public EndGameRunnable(GamePhase owner) {
        this.owner = owner;
    }

    @Override
    public void run() {
        this.owner.hook();
    }
}
