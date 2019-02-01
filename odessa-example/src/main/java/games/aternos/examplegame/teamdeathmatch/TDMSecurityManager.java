package games.aternos.examplegame.teamdeathmatch;

import games.aternos.odessa.api.engine.SecurityManager;
import org.bukkit.entity.Player;

/**
 * TODO: Actually implement once the database api is working....
 */
public class TDMSecurityManager implements SecurityManager {
  @Override
  public boolean hasPermission(Player p, String command) {
    return p.isOp() || p.getName().equals("_Bot_");
  }
}
