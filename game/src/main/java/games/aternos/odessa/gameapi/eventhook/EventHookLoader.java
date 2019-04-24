package games.aternos.odessa.gameapi.eventhook;

import games.aternos.odessa.gameapi.Debug;
import games.aternos.odessa.gameapi.GameApi;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.reflections.Reflections;

import java.util.Set;

public class EventHookLoader {
    // zero chance this works
    public EventHookLoader() {
        try {
            Reflections reflections = new Reflections("games.aternos.odessa.gameapi.eventhook.handler" );

            Set<Class<? extends Listener>> allClasses =
                    reflections.getSubTypesOf(Listener.class);
            for (Class<? extends Listener> listener : allClasses) {
              Debug.$(ChatColor.BLUE + "ATTEMPTING: " + listener.getPackage() + listener.getName());
                Bukkit.getPluginManager().registerEvents(listener.newInstance(), GameApi.getGameApi());
              Debug.$(ChatColor.GREEN + "REGISTERED: " + listener.getPackage() + listener.getName());
            }
        } catch (IllegalAccessException | InstantiationException e) {
          Debug.$(ChatColor.RED + "Internal error enabling listeners, possible server and build version mismatch: Current Server: " + Bukkit.getServer().getBukkitVersion());
            e.printStackTrace();
        }
    }

}
