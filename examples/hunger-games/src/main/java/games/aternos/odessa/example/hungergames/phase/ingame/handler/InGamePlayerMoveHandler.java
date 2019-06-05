package games.aternos.odessa.example.hungergames.phase.ingame.handler;

import games.aternos.odessa.example.hungergames.phase.ingame.InGamePhase;
import games.aternos.odessa.gameapi.eventhook.Hook;
import games.aternos.odessa.gameapi.eventhook.handler.PlayerMoveEventHook;
import org.bukkit.event.player.PlayerMoveEvent;

import javax.annotation.Nonnull;

public class InGamePlayerMoveHandler {

  private final InGamePhase inGamePhase;

  public InGamePlayerMoveHandler(InGamePhase inGamePhase) {
    this.inGamePhase = inGamePhase;
    PlayerMoveEventHook.hooks.add(new PlayerMoveHandler("InGamePlayerMoveHandler"));
  }

  public class PlayerMoveHandler extends Hook {

    public PlayerMoveHandler(@Nonnull String hookID) {
      super(hookID);
    }

    @Override
    public void run(Object o) {
      PlayerMoveEvent playerMoveEvent = (PlayerMoveEvent) o;

      if (inGamePhase.getInGameState() == InGamePhase.InGameState.COUNTDOWN) {

        playerMoveEvent.getPlayer().teleport(playerMoveEvent.getFrom());
      }
    }
  }
}
