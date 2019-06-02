package games.aternos.odessa.example.hungergames.phase.ingame.handler;

import games.aternos.odessa.example.hungergames.phase.ingame.InGamePhase;
import games.aternos.odessa.gameapi.eventhook.Hook;
import games.aternos.odessa.gameapi.eventhook.handler.EntityDamageByEntityEventHook;
import games.aternos.odessa.gameapi.eventhook.handler.EntityDamageEventHook;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashMap;

public class InGamePlayerDeathHandler {

    private final InGamePhase gamePhase;

    private final HashMap<Player, Player> lastAttacker;

    public InGamePlayerDeathHandler(InGamePhase gamePhase) {
        this.gamePhase = gamePhase;
        EntityDamageEventHook.hooks.add(new EntityDamageHandler());
        EntityDamageByEntityEventHook.hooks.add(new EntityDamageByEntityHandler());
        lastAttacker = new HashMap<>();
    }

    public class EntityDamageHandler extends Hook {
        @Override
        public void run(Object o) {
            EntityDamageEvent event = (EntityDamageEvent) o;
            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                if (player.getHealth() - event.getDamage() <= 0) { // process death
                    event.setCancelled(true);
                    gamePhase.getPlayerService().dropItems(player, player.getLocation(), true); // drop items
                    gamePhase.getPlayerService().spectatorPlayer(player);
                    gamePhase.getGame().getGameData().getPlayers().remove(player);
                    gamePhase.getGame().getGameData().getSpectators().add(player);
                    gamePhase // teleport to random player
                            .getPlayerService()
                            .teleportPlayerToRandomFromList(
                                    player, gamePhase.getGame().getGameData().getPlayers());
                    gamePhase
                            .getPlayerService()
                            .giveKit(player, gamePhase.hungerGamesSpectatorKit());

                    gamePhase.getInGameSidebar().pushBoard(player);

                    if (lastAttacker.get(player) != null) {
                        // credit where credit is due
                        Bukkit.broadcastMessage(player.getName() + " Was Killed By: " + lastAttacker.get(player).getName());
                    } else {
                        Bukkit.broadcastMessage(player.getName() + " Was Killed.");
                    }

                }
            }
        }
    }

    /**
     * Allows kill crediting when applicable.
     */
    public class EntityDamageByEntityHandler extends Hook {

        @Override
        public void run(Object o) {
            EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) o;
            if (event.getDamager() != null && event.getEntity() != null && event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
                Player attacked = (Player) event.getEntity();
                Player attacker = (Player) event.getEntity();
                lastAttacker.put(attacked, attacker);
            }
        }
    }
}
