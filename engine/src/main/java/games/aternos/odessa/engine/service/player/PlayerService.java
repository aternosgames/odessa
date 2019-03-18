package games.aternos.odessa.engine.service.player;

import games.aternos.odessa.engine.service.Service;
import games.aternos.odessa.gameapi.GameApi;
import org.bukkit.entity.Player;

public class PlayerService extends Service {
  public PlayerService(GameApi gameApi) {
    super(gameApi);
  }

  public void clearPlayer(Player p) {
    p.getInventory().setHelmet(null);
    p.getInventory().setChestplate(null);
    p.getInventory().setLeggings(null);
    p.getInventory().setBoots(null);
    p.getInventory().clear();
  }

  public void healPlayer(Player p) {
    p.setHealth(20);
    p.setFoodLevel(20);
  }


}
