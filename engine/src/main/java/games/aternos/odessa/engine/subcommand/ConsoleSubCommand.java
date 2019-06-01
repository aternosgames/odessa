package games.aternos.odessa.engine.subcommand;

import org.bukkit.command.ConsoleCommandSender;

/**
 * Used for console only subcommands
 */
public abstract class ConsoleSubCommand extends SubCommand {

    public ConsoleSubCommand(String subCmd) {
        super(subCmd);
    }

    public abstract void run(String[] args, ConsoleCommandSender consoleCommandSender);
}
