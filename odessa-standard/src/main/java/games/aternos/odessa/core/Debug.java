package games.aternos.odessa.core;

import org.bukkit.Bukkit;

public class Debug {

  public static void $(String s) {

    boolean DEBUG = true;
    if (DEBUG) {
      Bukkit.getServer().getLogger().info("ODESSA DEBUG: " + s);
    }

  }


}
