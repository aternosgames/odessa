package games.aternos.odessa.engine.lobby.command.odessa.subcommand;

import games.aternos.odessa.engine.lobby.GameLobbySystem;
import games.aternos.odessa.engine.subcommand.PlayerSubCommand;
import org.bukkit.entity.Player;

public class SetLobbySpawnSubCommand extends PlayerSubCommand {

    private final GameLobbySystem gameLobbySystem;

    public SetLobbySpawnSubCommand(GameLobbySystem gameLobbySystem) {
        super("setlobby");
        this.gameLobbySystem = gameLobbySystem;
    }

    @Override
    public void run(String[] args, Player player) {
        if (!this.gameLobbySystem.isActive()) {
            return;
        }

        this.gameLobbySystem.getLobbyIoConfiguration().setLobbySpawn(player.getLocation());
        player.sendMessage("Odessa: Set Lobby Spawn");
    }
}
