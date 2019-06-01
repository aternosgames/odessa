package games.aternos.odessa.gameapi;

import org.bukkit.Bukkit;

public class Debug {

    /**
     * Controls if debug output is printed.
     */
    private static boolean debug = true;

  public static void $(String message) {
      if (debug) {
          Bukkit.getLogger().info(message);
    }
  }
}
