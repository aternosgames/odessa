package games.aternos.odessa.api.engine;

import org.bukkit.entity.Player;

public interface SecurityManager {

  boolean hasPermission(Player p, String command);


}
