package games.aternos.odessa.engine.subcommand;

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
