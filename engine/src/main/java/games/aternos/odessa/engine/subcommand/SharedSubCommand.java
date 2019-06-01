package games.aternos.odessa.engine.subcommand;

import org.bukkit.command.CommandSender;

/** Used for commands that can be ran by either console or players */
public abstract class SharedSubCommand extends SubCommand {
  public SharedSubCommand(String subCmd) {
    super(subCmd);
  }

  public abstract void run(String[] args, CommandSender commandSender);
}
