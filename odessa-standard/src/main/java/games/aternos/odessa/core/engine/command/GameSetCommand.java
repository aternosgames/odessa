package games.aternos.odessa.core.engine.command;

import games.aternos.odessa.api.engine.SubCommandable;
import games.aternos.odessa.api.game.Game;
import games.aternos.odessa.core.OdessaEngine;
import games.aternos.odessa.core.engine.GameManager;
import org.bukkit.entity.Player;

public class GameSetCommand implements SubCommandable {
  @Override
  public void onCommand(Player p, String[] args) {

    if (!GameManager.getInstance().getActiveGame().getSecurityManager().hasPermission(p, "set")) {
      return;
    }

    if (args.length != 2) {
      sendHelp(p);
      return;
    }

    Game setTo = null;

    for (Game g : GameManager.getInstance().getGameList()) {

      if (g.getGameData().getAbbreviatedGameName().equalsIgnoreCase(args[1])) {
        setTo = g;
      }
    }

    if (setTo == null) {
      sendHelp(p);
      return;
    }

    GameManager.getInstance().setActiveGame(setTo);
    GameManager.getInstance().getActiveGame().start(OdessaEngine.getOdessaEngine());

  }


  private void sendHelp(Player p) {
    p.sendMessage("/game set <name>");
    p.sendMessage("Possible Games: " + getAllGames());
  }

  private String getAllGames() {

    StringBuilder allGames = new StringBuilder();

    for (Game g : GameManager.getInstance().getGameList()) {
      allGames.append(g.getGameData().getAbbreviatedGameName()).append(", ");
    }
    return allGames.toString();
  }
}
