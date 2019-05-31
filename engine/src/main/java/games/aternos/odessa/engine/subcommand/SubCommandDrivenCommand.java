package games.aternos.odessa.engine.subcommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubCommandDrivenCommand implements CommandExecutor {

    private String command;

    private List<ConsoleSubCommand> consoleSubCommands;
    private List<PlayerSubCommand> playerSubCommands;
    private List<SharedSubCommand> sharedSubCommands;

    public SubCommandDrivenCommand(String command) {
        this.command = command;
        consoleSubCommands = new ArrayList<>();
        playerSubCommands = new ArrayList<>();
        sharedSubCommands = new ArrayList<>();
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.isOp()) { // permissions check, op by default, until we get the proper permissions database and such
            return true;
        }
        if (!(strings.length > 0)) { // is there anything to check to see if it's a subcmd?
            generateHelp(commandSender);
            return true;
        }

        String subCommand = strings[0];
        String[] newArgs = {};
        if (!(strings.length == 1)) {
            newArgs = Arrays.copyOfRange(strings, 1, strings.length);
        }
        if (commandSender instanceof Player) { // first if it's a player we check player commands
            for (PlayerSubCommand playerSubCommand : playerSubCommands) {
                if (playerSubCommand.getSubCmd().equalsIgnoreCase(subCommand)) {
                    playerSubCommand.run(newArgs, (Player) commandSender);
                    return true;
                }
            }
        } else if (commandSender instanceof ConsoleCommandSender) { // not there, okay, check console ones if console
            for (ConsoleSubCommand consoleSubCommand : consoleSubCommands) {
                if (consoleSubCommand.getSubCmd().equalsIgnoreCase(subCommand)) {
                    consoleSubCommand.run(newArgs, (ConsoleCommandSender) commandSender);
                    return true;
                }
            }
        }

        for (SharedSubCommand sharedSubCommand : sharedSubCommands) { // finally, check shared commands if not, there's no command!
            if (sharedSubCommand.getSubCmd().equalsIgnoreCase(subCommand)) {
                sharedSubCommand.run(newArgs, commandSender);
                return true;
            }
        }

        commandSender.sendMessage("Not Found or not in your bounds!!!!");
        generateHelp(commandSender);


        return true;
    }


    private void generateHelp(CommandSender sender) {
        sender.sendMessage("Ah no! Here's some help with that");
        for (ConsoleSubCommand consoleSubCommand : consoleSubCommands) {
            sender.sendMessage("CONSOLECMD> " + command + " " + consoleSubCommand.getSubCmd());
        }
        for (PlayerSubCommand playerSubCommand : playerSubCommands) {
            sender.sendMessage("PLAYERCMD> " + command + " " + playerSubCommand.getSubCmd());
        }
        for (SharedSubCommand sharedSubCommand : sharedSubCommands) {
            sender.sendMessage("SHAREDCMD> " + command + " " + sharedSubCommand.getSubCmd());
        }
    }

    public void addCommand(ConsoleSubCommand conSubCmd) {
        this.consoleSubCommands.add(conSubCmd);
    }

    public void addCommand(SharedSubCommand sharedSubCommand) {
        this.sharedSubCommands.add(sharedSubCommand);
    }

    public void addCommand(PlayerSubCommand playerSubCommand) {
        this.playerSubCommands.add(playerSubCommand);
    }

    public void removeCommand(ConsoleSubCommand conSubCmd) {
        this.consoleSubCommands.remove(conSubCmd);
    }

    public void removeCommand(SharedSubCommand sharedSubCommand) {
        this.sharedSubCommands.remove(sharedSubCommand);
    }

    public void removeCommand(PlayerSubCommand playerSubCommand) {
        this.playerSubCommands.remove(playerSubCommand);
    }

    public String getCommand() {
        return command;
    }
}