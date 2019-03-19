package games.aternos.odessa.engine.lobby.scoreboard;

import games.aternos.odessa.engine.lobby.GameLobbySystem;
import games.aternos.odessa.engine.service.sidebar.Sidebar;
import games.aternos.odessa.engine.service.sidebar.SidebarService;
import games.aternos.odessa.gameapi.game.element.Kit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

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
    for (Player p : this.gameLobbySystem.getGame().getGameData().getPlayers()) {
      this.getSidebarService().createSidebarScoreboard(p, generateSidebar(p));
    }
  }

  public void pushBoard(Player p) {
    this.getSidebarService().createSidebarScoreboard(p, generateSidebar(p));
  }

  private Sidebar generateSidebar(Player p) {

    String boardName = ChatColor.BOLD + getGameName() + " Lobby";
    return new Sidebar(boardName, scoreboardItems(p));

  }

  private List<String> scoreboardItems(Player p) {
    List<String> items = new ArrayList<>();

    items.add("           ");
    items.add(ChatColor.YELLOW + "Waiting...");
    items.add("            ");
    items.add(ChatColor.YELLOW + "Players:");
    items.add(ChatColor.GRAY + Integer.toString(getCurrentPlayerSize()) + " ");
    items.add("             ");
    items.add(ChatColor.YELLOW + "Needed:");
    items.add(ChatColor.GRAY + "" + getNeededPlayers());
    items.add("               ");
    items.add(ChatColor.YELLOW + "Kit:");
    items.add(ChatColor.GRAY + "" + getSelectedKitName(p));
    return items;
  }

  private String getGameName() {
    return this.getGameLobbySystem().getGame().getGameConfiguration().getGameName();
  }

  private Sidebar getLobbySidebar() {
    return lobbySidebar;
  }

  private void setLobbySidebar(Sidebar lobbySidebar) {
    this.lobbySidebar = lobbySidebar;
  }

  private Integer getCurrentPlayerSize() {
    return this.getGameLobbySystem().getGame().getGameData().getPlayers().size();
  }

  private Integer getNeededPlayers() {
    return this.getGameLobbySystem().getGame().getGameConfiguration().getMinPlayers() - getCurrentPlayerSize();
  }

  private String getSelectedKitName(Player p) {
    Kit kit = this.getGameLobbySystem().getGame().getGameData().getSelectedPlayerKits().get(p);
    if (kit == null) {
      kit = this.getGameLobbySystem().getGame().getGameConfiguration().getGameKits().get(0);
    }
    return kit.getKitName();
  }

  private GameLobbySystem getGameLobbySystem() {
    return gameLobbySystem;
  }

  private SidebarService getSidebarService() {
    return sidebarService;
  }

}
