package games.aternos.odessa.engine.lobby.command;

import games.aternos.odessa.engine.lobby.GameLobbySystem;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddSpawnCommand implements CommandExecutor {
  private final GameLobbySystem gameLobbySystem;

  public AddSpawnCommand(GameLobbySystem gameLobbySystem) {
    this.gameLobbySystem = gameLobbySystem;
  }

  @Override
  public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

    if (!commandSender.isOp()) {
      return true;
    }

    if (!(strings.length == 3)) {
      commandSender.sendMessage("Odessa: /addarenaspawn <mapname> <teamid> <spawnid>");
      return true;
    }

    Player p = (Player) commandSender;

    Location spawnLocation = p.getLocation().toBlockLocation();

    String mapName = strings[0];
    String teamId = strings[1];
    String spawnId = strings[2];

    if (!(teamId.matches("-?\\d+") && spawnId.matches("-?\\d+"))) {
      commandSender.sendMessage("Odessa: Check ints, error.");
      return true;
    }

    gameLobbySystem.getGameArenaService().getGameArenaIoConfiguration().addSpawn(spawnLocation, Integer.valueOf(teamId), Integer.valueOf(spawnId), mapName);

    commandSender.sendMessage("Odessa: Set Spawn.");
    return true;
  }
}
