package games.aternos.odessa.engine.lobby.command;

import games.aternos.odessa.engine.lobby.GameLobbySystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLobbyLocationCommand implements CommandExecutor {

  private final GameLobbySystem gameLobbySystem;

  public SetLobbyLocationCommand(GameLobbySystem gameLobbySystem) {
    this.gameLobbySystem = gameLobbySystem;
  }

  @Override
  public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

    if (!commandSender.isOp()) {
      return true;
    }

    Player p = (Player) commandSender;
    this.gameLobbySystem.getLobbyIoConfiguration().setLobbySpawn(p.getLocation());
    p.sendMessage("Odessa: Set Lobby Spawn");


    return true;
  }
}
