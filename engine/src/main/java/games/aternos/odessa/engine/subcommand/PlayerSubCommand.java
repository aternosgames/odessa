package games.aternos.odessa.engine.subcommand;

import org.bukkit.entity.Player;

/**
 * Used for subcommands that can only be used by players
 */
abstract public class PlayerSubCommand extends SubCommand {

    public PlayerSubCommand(String subCmd) {
        super(subCmd);
    }

    abstract public void run(String[] args, Player player);


}
