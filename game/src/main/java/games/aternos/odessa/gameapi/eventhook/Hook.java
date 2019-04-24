package games.aternos.odessa.gameapi.eventhook;


import games.aternos.odessa.gameapi.Debug;
import org.bukkit.ChatColor;

abstract public class Hook {

    public Hook() {
        Debug.$(ChatColor.GREEN + "Hook Created: " + this.getClass().getPackage() + "." + this.getClass().getName());

    }

    public abstract void run(Object o);


}
