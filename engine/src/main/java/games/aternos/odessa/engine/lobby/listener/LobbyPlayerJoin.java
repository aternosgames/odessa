package games.aternos.odessa.engine.lobby.listener;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.service.scoreboard.Scoreboard;
import games.aternos.odessa.engine.service.scoreboard.ScoreboardService;
import games.aternos.odessa.gameapi.GameApi;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LobbyPlayerJoin implements Listener {

  private final LobbyController lobbyController;

  public LobbyPlayerJoin(@Nonnull LobbyController lobbyController) {
    this.lobbyController = lobbyController;
  }

  private static List<Player> play = new ArrayList<>();
  private static List<String> strings = new ArrayList<>();
  private static Scoreboard scoreboard = new Scoreboard(new ScoreboardService(GameApi.getGameApi()), play, strings);
  private static int i = 1;

  @EventHandler
  public void playerJoinLobby(PlayerJoinEvent event) {
    lobbyController.getGameLobbySystem().getGame().getGameData().addPlayer(event.getPlayer());
    // todo...then what? :: Spawns inventory etc.
    Random r = new Random();
    strings.add("" + r.nextInt(5));
    scoreboard.addPlayer(event.getPlayer());

    scoreboard.update(strings);
  }

  @EventHandler
  public void onLeave(PlayerQuitEvent e) {
    scoreboard.removePlayer(e.getPlayer());
  }

}


