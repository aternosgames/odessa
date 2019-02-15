package games.aternos.odessa.gameapi;

import org.bukkit.Bukkit;

public class Debug {

  public static void $(String message) {
    Bukkit.getLogger().info(message);
  }

}
