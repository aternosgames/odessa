package games.aternos.odessa.example.hungergames.phase.ingame.handler;

import games.aternos.odessa.example.hungergames.phase.ingame.InGamePhase;
import games.aternos.odessa.gameapi.Debug;
import games.aternos.odessa.gameapi.eventhook.Hook;
import games.aternos.odessa.gameapi.eventhook.handler.PlayerQuitEventHook;
import org.bukkit.event.player.PlayerQuitEvent;

import javax.annotation.Nonnull;

public class InGamePlayerLeaveHandler {

    private final InGamePhase inGamePhase;

    public InGamePlayerLeaveHandler(InGamePhase inGamePhase) {
        this.inGamePhase = inGamePhase;
        PlayerQuitEventHook.hooks.add(new PlayerLeavehandler("InGamePlayerLeaveHandler"));
    }

    public class PlayerLeavehandler extends Hook {

        public PlayerLeavehandler(@Nonnull String hookID) {
            super(hookID);
        }

        @Override
        public void run(Object o) {
            PlayerQuitEvent event = (PlayerQuitEvent) o;

            switch (inGamePhase.getGame().getGameData().getGamePlayerType(event.getPlayer())) {
                case PLAYER:
                    inGamePhase
                            .getPlayerService()
                            .dropItems(
                                    ((PlayerQuitEvent) o).getPlayer(),
                                    ((PlayerQuitEvent) o).getPlayer().getLocation(),
                                    true);
                    inGamePhase
                            .getGame()
                            .getGameData()
                            .getPlayers()
                            .remove(((PlayerQuitEvent) o).getPlayer());
                    event.setQuitMessage(
                            event.getPlayer().getName() + " Left the game, their items have been dropped!!");
                    break;
                case SPECTATOR:
                    inGamePhase.getPlayerService().clearPlayer(event.getPlayer());
                    inGamePhase.getGame().getGameData().getSpectators().remove(event.getPlayer());
                    event.setQuitMessage(null);
                    break;
                default:
                    event.setQuitMessage(null);
                    Debug.$("Player not in either group ???????");
            }
        }
    }
}
