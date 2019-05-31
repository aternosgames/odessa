package games.aternos.odessa.engine.lobby.command.subcommand;

/**
 * The base of all subcommand types
 */
abstract public class SubCommand {

    private final String subCmd;

    public SubCommand(String subCmd) {
        this.subCmd = subCmd;
    }

    public String getSubCmd() {
        return subCmd;
    }

}
