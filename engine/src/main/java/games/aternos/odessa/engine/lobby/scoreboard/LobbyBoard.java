package games.aternos.odessa.engine.lobby.scoreboard;

import games.aternos.odessa.engine.service.sidebar.Sidebar;
import games.aternos.odessa.engine.service.sidebar.SidebarService;

public class LobbyBoard {

  private final SidebarService sidebarService;

  private Sidebar lobbySidebar;

  public LobbyBoard(SidebarService sidebarService) {
    this.sidebarService = sidebarService;
  }

  private Sidebar getLobbySidebar() {
    return lobbySidebar;
  }

  private void setLobbySidebar(Sidebar lobbySidebar) {
    this.lobbySidebar = lobbySidebar;
  }
}
