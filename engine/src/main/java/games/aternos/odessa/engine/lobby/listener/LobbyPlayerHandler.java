package games.aternos.odessa.engine.lobby.listener;

import games.aternos.odessa.engine.lobby.LobbyController;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class LobbyPlayerHandler implements Listener {

  private final LobbyController lobbyController;

  public LobbyPlayerHandler(@Nonnull LobbyController lobbyController) {
    this.lobbyController = lobbyController;
  }

  private static List<Player> play = new ArrayList<>();
  private static List<String> strings = new ArrayList<>();
  private static int i = 1;

  @EventHandler
  public void playerJoinLobby(PlayerJoinEvent event) {
    event.setJoinMessage(ChatColor.BLUE + "Lobby> " + ChatColor.GRAY + " +" + event.getPlayer().getName());
    lobbyController.getGameLobbySystem().getGame().getGameData().addPlayer(event.getPlayer());
    this.lobbyController.getGameLobbySystem().getLobbyBoard().pushBoard();
  }

  @EventHandler
  public void playerLeaveLobby(PlayerQuitEvent event) {
    event.setQuitMessage(ChatColor.BLUE + "Lobby> " + ChatColor.GRAY + " -" + event.getPlayer().getName());
    lobbyController.getGameLobbySystem().getGame().getGameData().removePlayer(event.getPlayer());
    this.lobbyController.getGameLobbySystem().getLobbyBoard().pushBoard();
  }

}


