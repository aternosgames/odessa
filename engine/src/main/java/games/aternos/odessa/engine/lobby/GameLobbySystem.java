package games.aternos.odessa.engine.lobby;

import games.aternos.odessa.engine.lobby.arena.ArenaVoteGUI;
import games.aternos.odessa.engine.lobby.ioconfiguration.LobbyIoConfiguration;
import games.aternos.odessa.engine.lobby.kit.KitSelectionGUI;
import games.aternos.odessa.engine.lobby.scoreboard.LobbyBoard;
import games.aternos.odessa.engine.service.arena.GameArenaService;
import games.aternos.odessa.engine.service.ioconfiguration.IoConfigurationService;
import games.aternos.odessa.engine.service.player.PlayerService;
import games.aternos.odessa.engine.service.sidebar.SidebarService;
import games.aternos.odessa.gameapi.GameApi;
import games.aternos.odessa.gameapi.game.Game;
import games.aternos.odessa.gameapi.game.GameConfiguration;
import games.aternos.odessa.gameapi.game.GameLifecycleManager;
import games.aternos.odessa.gameapi.game.element.Arena;
import org.bukkit.Bukkit;

import javax.annotation.Nonnull;
import java.util.HashMap;

/**
 * Uniformed Lobby System that can be called into action by a game.
 */
public class GameLobbySystem {

  private final GameLifecycleManager gameLifecycleManager;
  private final GameApi gameApi;
  private final Game game;
  private final GameConfiguration gameConfiguration;
  private final LobbyBoard lobbyBoard;
  private final LobbyIoConfiguration lobbyIoConfiguration;
  private final PlayerService playerService;
  private final KitSelectionGUI kitSelectionGUI;
  private final GameArenaService gameArenaService;
  private final HashMap<Arena, Integer> arenaVote;
  private final ArenaVoteGUI arenaVoteGUI;


  private LobbyController lobbyController;
  private LobbyState lobbyState;
  private boolean active;

  public GameLobbySystem(@Nonnull GameLifecycleManager gameLifecycleManager, @Nonnull GameApi gameApi, @Nonnull GameConfiguration gameConfiguration) {
    this.lobbyController = new LobbyController(this);
    this.gameLifecycleManager = gameLifecycleManager;
    this.gameApi = gameApi;
    game = gameApi.getGame();
    this.gameConfiguration = gameConfiguration;
    SidebarService sidebarService = new SidebarService(this.gameApi, Bukkit.getScoreboardManager());
    this.lobbyBoard = new LobbyBoard(sidebarService, this.getLobbyController());
    IoConfigurationService ioConfigurationService = new IoConfigurationService(this.gameApi);
    this.lobbyIoConfiguration = new LobbyIoConfiguration(ioConfigurationService);
    this.playerService = new PlayerService(this.gameApi);
    this.kitSelectionGUI = new KitSelectionGUI(this.getLobbyController());
    this.gameArenaService = new GameArenaService(this.gameApi, ioConfigurationService, this.getGame().getGameConfiguration().getGameName());
    this.arenaVote = new HashMap<>();
    this.arenaVoteGUI = new ArenaVoteGUI(this);
  }

  /**
   * Starts the Lobby instance
   */
  public void startLobby() {
    this.active = true;
    this.getGame().getGameConfiguration().setGameArenas(this.gameArenaService.getArenas());
    this.getLobbyController().registerLobbyListeners();
    this.getLobbyController().registerLobbyCommands();
    this.lobbyState = LobbyState.WAITINGFORPLAYERS;
  }

  /**
   * Stops the lobby
   */
  public void stopLobby() {
      this.getLobbyController().removeLobbyListeners();
    this.active = false;
  }

  /**
   * @return The Most voted lobby
   */
  public Arena computeArenaVoted(){
    if(this.arenaVote.isEmpty()){
      return this.getGame().getGameConfiguration().getGameArenas().get(0);
    }

    Arena mostVoted = null;
    int mostVotes = 0;
    for(Arena a : this.arenaVote.keySet()){
      if(mostVoted == null){
        mostVoted = a;
        mostVotes = this.arenaVote.get(a);
      }else{
        if(this.arenaVote.get(a) > mostVotes){
          mostVoted = a;
          mostVotes = this.arenaVote.get(a);
        }
      }
    }
    return mostVoted;
  }

  public LobbyBoard getLobbyBoard() {
    return lobbyBoard;
  }

  public LobbyController getLobbyController() {
    return lobbyController;
  }

  public void setLobbyController(LobbyController lobbyController) {
    this.lobbyController = lobbyController;
  }

  public LobbyIoConfiguration getLobbyIoConfiguration() {
    return lobbyIoConfiguration;
  }

  public GameConfiguration getGameConfiguration() {
    return gameConfiguration;
  }

  public GameLifecycleManager getGameLifecycleManager() {
    return gameLifecycleManager;
  }

  public GameApi getGameApi() {
    return gameApi;
  }

  public Game getGame() {
    return game;
  }

  public PlayerService getPlayerService() {
    return playerService;
  }

  public KitSelectionGUI getKitSelectionGUI() {
    return kitSelectionGUI;
  }

  public GameArenaService getGameArenaService() {
    return gameArenaService;
  }

  public ArenaVoteGUI getArenaVoteGUI(){return this.arenaVoteGUI;}

  public HashMap<Arena, Integer> getArenaVote() {
    return arenaVote;
  }

  public LobbyState getLobbyState() {
    return lobbyState;
  }

  public void setLobbyState(LobbyState lobbyState) {
    this.lobbyState = lobbyState;
  }

  public boolean isActive() {
    return active;
  }
}
