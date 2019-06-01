package games.aternos.odessa.engine.subcommand;

import org.bukkit.entity.Player;

/** Used for subcommands that can only be used by players */
public abstract class PlayerSubCommand extends SubCommand {

  public PlayerSubCommand(String subCmd) {
    super(subCmd);
  }

  public abstract void run(String[] args, Player player);
}
