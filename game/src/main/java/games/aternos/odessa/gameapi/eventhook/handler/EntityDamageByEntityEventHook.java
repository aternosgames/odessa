package games.aternos.odessa.gameapi.eventhook.handler;

import games.aternos.odessa.gameapi.eventhook.Hook;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;
import java.util.List;

public class EntityDamageByEntityEventHook implements Listener {
  public static final List<Hook> hooks = new ArrayList<>();

  @EventHandler
  public void onEvent(EntityDamageByEntityEvent event) {
    for (Hook hook : hooks) {
      hook.run(event);
    }
  }
}
