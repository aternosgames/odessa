package games.aternos.odessa.engine.lobby.command.odessa;

import games.aternos.odessa.engine.lobby.GameLobbySystem;
import games.aternos.odessa.engine.lobby.command.odessa.subcommand.AddSpawnSubCommand;
import games.aternos.odessa.engine.lobby.command.odessa.subcommand.CreateArenaSubCommand;
import games.aternos.odessa.engine.lobby.command.odessa.subcommand.SetLobbySpawnSubCommand;
import games.aternos.odessa.engine.lobby.command.odessa.subcommand.StartGameSubCommand;
import games.aternos.odessa.engine.subcommand.SubCommandDrivenCommand;

public class OdessaCommand extends SubCommandDrivenCommand {

    private final GameLobbySystem gameLobbySystem;

    public OdessaCommand(GameLobbySystem gameLobbySystem) {
        super("/odessa");
        this.gameLobbySystem = gameLobbySystem;
        registerSub();
    }

    private void registerSub() {
        this.addCommand(new AddSpawnSubCommand(this.gameLobbySystem));
        this.addCommand(new CreateArenaSubCommand(this.gameLobbySystem));
        this.addCommand(new SetLobbySpawnSubCommand(this.gameLobbySystem));
        this.addCommand(new StartGameSubCommand(this.gameLobbySystem));
    }
}