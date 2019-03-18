package games.aternos.odessa.engine.lobby.scoreboard;

import games.aternos.odessa.engine.lobby.GameLobbySystem;
import games.aternos.odessa.engine.service.sidebar.Sidebar;
import games.aternos.odessa.engine.service.sidebar.SidebarService;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class LobbyBoard {

  private final SidebarService sidebarService;

  private final GameLobbySystem gameLobbySystem;

  private Sidebar lobbySidebar;

  public LobbyBoard(SidebarService sidebarService, GameLobbySystem gameLobbySystem) {
    this.sidebarService = sidebarService;
    this.gameLobbySystem = gameLobbySystem;
  }

  public void pushBoard() {
    this.getSidebarService().createSidebarScoreboard(generateSidebar(), this.gameLobbySystem.getGame().getGameData().getPlayers());
  }

  private Sidebar generateSidebar() {


    String boardName = ChatColor.BOLD + getGameName() + " Lobby";
    return new Sidebar(boardName, scoreboardItems());

  }

  private List<String> scoreboardItems() {
    List<String> items = new ArrayList<>();

    items.add("           ");
    items.add(ChatColor.YELLOW + "Waiting...");
    items.add("            ");
    items.add(ChatColor.YELLOW + "Players:");
    items.add(ChatColor.GRAY + "" + getCurrentPlayerSize());
    items.add("             ");
    items.add(ChatColor.YELLOW + "Needed:");
    items.add(ChatColor.GRAY + "" + getNeededPlayers());

    return items;
  }

  private String getGameName() {
    return this.getGameLobbySystem().getGame().getGameConfiguration().getGameName();
  }

  private Sidebar getLobbySidebar() {
    return lobbySidebar;
  }

  private Integer getCurrentPlayerSize() {
    return this.getGameLobbySystem().getGame().getGameData().getPlayers().size();
  }

  private Integer getNeededPlayers() {
    return getCurrentPlayerSize() - this.getGameLobbySystem().getGame().getGameConfiguration().getMinPlayers();
  }

  private void setLobbySidebar(Sidebar lobbySidebar) {
    this.lobbySidebar = lobbySidebar;
  }

  private GameLobbySystem getGameLobbySystem() {
    return gameLobbySystem;
  }

  public SidebarService getSidebarService() {
    return sidebarService;
  }

}
