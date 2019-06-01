package games.aternos.odessa.example.hungergames.phase.ingame.handler;

import games.aternos.odessa.example.hungergames.phase.ingame.InGamePhase;
import games.aternos.odessa.gameapi.eventhook.Hook;
import games.aternos.odessa.gameapi.eventhook.handler.PlayerJoinEventHook;
import org.bukkit.event.player.PlayerJoinEvent;

public class InGamePlayerJoinHandler {

    private final InGamePhase gamePhase;

    public InGamePlayerJoinHandler(InGamePhase gamePhase) {
        this.gamePhase = gamePhase;
        PlayerJoinEventHook.hooks.add(new PlayerJoinHandler());
    }

    public class PlayerJoinHandler extends Hook {

        @Override
        public void run(Object o) {

            PlayerJoinEvent event = (PlayerJoinEvent) o;

            event.setJoinMessage(null);
            event.getPlayer().sendActionBar("HungerGames Spectator");
            gamePhase.getPlayerService().spectatorPlayer(event.getPlayer());

            gamePhase.getGame().getGameData().addSpectator(event.getPlayer());

            gamePhase // teleport to random player
                    .getPlayerService()
                    .teleportPlayerToRandomFromList(
                            event.getPlayer(), gamePhase.getGame().getGameData().getPlayers());

            gamePhase.getPlayerService().giveKit(event.getPlayer(), gamePhase.hungerGamesSpectatorKit());
        }
    }
}
