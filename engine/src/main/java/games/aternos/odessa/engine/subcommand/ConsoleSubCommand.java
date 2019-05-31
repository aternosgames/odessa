package games.aternos.odessa.engine.subcommand;

import org.bukkit.command.ConsoleCommandSender;

/**
 * Used for console only subcommands
 */
abstract public class ConsoleSubCommand extends SubCommand {

    public ConsoleSubCommand(String subCmd) {
        super(subCmd);
    }

    abstract public void run(String[] args, ConsoleCommandSender consoleCommandSender);
}
