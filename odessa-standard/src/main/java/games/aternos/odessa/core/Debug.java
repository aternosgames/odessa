package games.aternos.odessa.core;

import org.bukkit.Bukkit;

public class Debug {

  private static boolean DEBUG = true;

  public static void $(String s) {

    if (DEBUG) {
      Bukkit.getServer().getLogger().info("ODESSA DEBUG: " + s);
    }

  }


}
