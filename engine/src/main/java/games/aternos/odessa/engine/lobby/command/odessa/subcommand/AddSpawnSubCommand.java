package games.aternos.odessa.engine.lobby.command.odessa.subcommand;

import games.aternos.odessa.engine.lobby.GameLobbySystem;
import games.aternos.odessa.engine.subcommand.PlayerSubCommand;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class AddSpawnSubCommand extends PlayerSubCommand {

    private final GameLobbySystem gameLobbySystem;

    public AddSpawnSubCommand(GameLobbySystem gameLobbySystem) {
        super("addarenaspawn");
        this.gameLobbySystem = gameLobbySystem;
    }

    @Override
    public void run(String[] args, Player player) {
        if (args.length != 3) {
            player.sendMessage("Ah no!: /odessa addarenaspawn <mapname> <teamid> <spawnid>");
            return;
        }

        Location spawnLocation = player.getLocation().toBlockLocation();

        String mapName = args[0];
        String teamId = args[1];
        String spawnId = args[2];

        if (!(teamId.matches("-?\\d+") && spawnId.matches("-?\\d+"))) {
            player.sendMessage("Odessa: Check ints, error.");
            return;
        }

        this.gameLobbySystem.getGameArenaService().getGameArenaIoConfiguration().addSpawn(spawnLocation, Integer.valueOf(teamId), Integer.valueOf(spawnId), mapName);

        player.sendMessage("Odessa: Set Spawn.");
    }
}
