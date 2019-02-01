package games.aternos.odessa.api.engine;

import org.bukkit.entity.Player;

public interface SubCommandable {

  boolean onCommand(Player p, String[] args);

}
