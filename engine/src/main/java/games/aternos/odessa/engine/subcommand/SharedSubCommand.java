package games.aternos.odessa.engine.subcommand;

import org.bukkit.command.CommandSender;

/**
 * Used for commands that can be ran by either console or players
 */
abstract public class SharedSubCommand extends SubCommand {
    public SharedSubCommand(String subCmd) {
        super(subCmd);
    }

    abstract public void run(String[] args, CommandSender commandSender);


}
