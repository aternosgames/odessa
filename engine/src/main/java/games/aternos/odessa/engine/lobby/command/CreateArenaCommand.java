package games.aternos.odessa.engine.lobby.command;

import games.aternos.odessa.engine.lobby.GameLobbySystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CreateArenaCommand implements CommandExecutor {

  private final GameLobbySystem gameLobbySystem;

  public CreateArenaCommand(GameLobbySystem gameLobbySystem) {
    this.gameLobbySystem = gameLobbySystem;
  }

  @Override
  public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
    if(!this.gameLobbySystem.isActive()){
      return true;
    }
    if (!commandSender.isOp()) {
      return true;
    }

    if (!(strings.length == 2)) {
      commandSender.sendMessage("Odessa: /createarena <arenaname> <author>");
      return true;
    }
    this.gameLobbySystem.getGameArenaService().getGameArenaIoConfiguration().createArena(strings[0], strings[1]);
    commandSender.sendMessage("Odessa: Created Arena: " + strings[0] + " author:" + strings[1]);

    return true;
  }
}
