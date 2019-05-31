package games.aternos.odessa.engine.lobby.command.odessa.subcommand;

import games.aternos.odessa.engine.lobby.GameLobbySystem;
import games.aternos.odessa.engine.subcommand.SharedSubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class StartGameSubCommand extends SharedSubCommand {
    private final GameLobbySystem gameLobbySystem;

    public StartGameSubCommand(GameLobbySystem gameLobbySystem) {
        super("startgame");
        this.gameLobbySystem = gameLobbySystem;
    }

    @Override
    public void run(String[] args, CommandSender commandSender) {
        if (this.gameLobbySystem.isActive()) {
            this.gameLobbySystem.getGame().getGameData().setGameArena(this.gameLobbySystem.computeArenaVoted());
            this.gameLobbySystem.getGameLifecycleManager().nextPhase();
            Bukkit.broadcastMessage(commandSender.getName() + " Forcestarted the game");
        } else {
            commandSender.sendMessage("Game not in lobby");

        }
    }
}
