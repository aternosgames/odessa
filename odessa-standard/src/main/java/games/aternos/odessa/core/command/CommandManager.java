package games.aternos.odessa.core.command;

import games.aternos.odessa.api.engine.SubCommandable;
import games.aternos.odessa.core.engine.command.GameSetCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandManager implements CommandExecutor {

  private static final CommandManager instance = new CommandManager();

  private HashMap<String, SubCommandable> commands;

  private CommandManager() {
    commands = new HashMap<>();
    commands.put("set", new GameSetCommand());
  }

  public CommandManager getInstance() {
    return instance;
  }

  @Override
  public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
    if (!(commandSender instanceof Player)) {
      return true;
    }

    if (command.getName().equalsIgnoreCase("game")) {

      if (args == null || args.length < 1) {
        this.sendHelp((Player) commandSender);
        return true;
      }

      for (String cmd : this.commands.keySet()) {

        if (args[0].equals(cmd)) {
          this.commands.get(cmd).onCommand((Player) commandSender, args);
          return true;
        }

      }

      // no subcmd
      this.sendHelp((Player) commandSender);
      return true;
    }

    return false;
  }

  // TODO: Proper formatting, formated message util
  private void sendHelp(Player player) {
    player.sendMessage("Help:");
    player.sendMessage("/game set <game>");
  }
}
