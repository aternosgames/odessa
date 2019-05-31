package games.aternos.odessa.engine.lobby.command.odessa.subcommand;

import games.aternos.odessa.engine.lobby.GameLobbySystem;
import games.aternos.odessa.engine.subcommand.PlayerSubCommand;
import games.aternos.odessa.gameapi.Debug;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class CreateArenaSubCommand extends PlayerSubCommand {

    private final GameLobbySystem gameLobbySystem;

    public CreateArenaSubCommand(GameLobbySystem gameLobbySystem) {
        super("createarena");
        this.gameLobbySystem = gameLobbySystem;
    }

    @Override
    public void run(String[] args, Player player) {
        if (!this.gameLobbySystem.isActive()) {
            return;
        }
        Debug.$(Arrays.toString(args));
        if (!(args.length == 2)) {
            player.sendMessage("Odessa: /createarena <arenaname> <author>");
            return;
        }
        this.gameLobbySystem.getGameArenaService().getGameArenaIoConfiguration().createArena(args[0], args[1]);
        player.sendMessage("Odessa: Created Arena: " + args[0] + " author:" + args[1]);
    }
}
