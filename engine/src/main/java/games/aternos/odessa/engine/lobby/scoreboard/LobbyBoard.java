package games.aternos.odessa.engine.lobby.scoreboard;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.lobby.LobbyControllerOwned;
import games.aternos.odessa.engine.service.sidebar.Sidebar;
import games.aternos.odessa.engine.service.sidebar.SidebarService;
import games.aternos.odessa.gameapi.game.element.Kit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LobbyBoard extends LobbyControllerOwned {

  private final SidebarService sidebarService;
  private Sidebar lobbySidebar;

  public LobbyBoard(SidebarService sidebarService, LobbyController lobbyController) {
    super(lobbyController);
    this.sidebarService = sidebarService;
  }

  public void pushBoard() {
    for (Player p : this.getOwner().getGameLobbySystem().getGame().getGameData().getPlayers()) {
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
    return this.getOwner().getGameLobbySystem().getGame().getGameConfiguration().getGameName();
  }

  private Sidebar getLobbySidebar() {
    return lobbySidebar;
  }

  private void setLobbySidebar(Sidebar lobbySidebar) {
    this.lobbySidebar = lobbySidebar;
  }

  private Integer getCurrentPlayerSize() {
    return this.getOwner().getGameLobbySystem().getGame().getGameData().getPlayers().size();
  }

  private Integer getNeededPlayers() {
    return this.getOwner().getGameLobbySystem().getGame().getGameConfiguration().getMinPlayers() - getCurrentPlayerSize();
  }

  private String getSelectedKitName(Player p) {
    Kit kit = this.getOwner().getGameLobbySystem().getGame().getGameData().getSelectedPlayerKits().get(p);
    if (kit == null) {
      kit = this.getOwner().getGameLobbySystem().getGame().getGameConfiguration().getGameKits().get(0);
    }
    return kit.getKitName();
  }


  private SidebarService getSidebarService() {
    return sidebarService;
  }

}
