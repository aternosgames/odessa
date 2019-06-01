package games.aternos.odessa.gameapi.eventhook.handler;

import games.aternos.odessa.gameapi.eventhook.Hook;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerBedEnterEventHook implements Listener {
    public static final List<Hook> hooks = new ArrayList<>();

    @EventHandler
    public void onEvent(PlayerBedEnterEvent event) {
        for (Hook hook : hooks) {
            hook.run(event);
        }
    }
}
