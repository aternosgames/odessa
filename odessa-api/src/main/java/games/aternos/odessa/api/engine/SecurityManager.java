package games.aternos.odessa.api.engine;

import org.bukkit.entity.Player;

public interface SecurityManager {

  void hasPermission(Player p, String command);


}
