package games.aternos.odessa.example.hungergames.phase.ingame;

import games.aternos.odessa.engine.service.sidebar.Sidebar;
import games.aternos.odessa.engine.service.sidebar.SidebarService;
import games.aternos.odessa.gameapi.Debug;
import games.aternos.odessa.gameapi.game.GameConfiguration;
import games.aternos.odessa.gameapi.game.GameData;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InGameSidebar {

  private final SidebarService sidebarService;

  private final GameConfiguration gameConfiguration;

  private final GameData gameData;

  private final InGamePhase inGamePhase;

  public InGameSidebar(SidebarService sidebarService, InGamePhase gamePhase) {
    this.sidebarService = sidebarService;
    this.inGamePhase = gamePhase;
    this.gameData = gamePhase.getGame().getGameData();
    this.gameConfiguration = gamePhase.getGame().getGameConfiguration();
  }

  public void pushBoard() {
    for (Player p : this.gameData.getPlayers()) {
      this.sidebarService.createSidebarScoreboard(p, generateSidebar(p));
    }
    for (Player p : this.gameData.getSpectators()) {
      this.sidebarService.createSidebarScoreboard(p, generateSidebar(p));
    }
  }

  public void pushBoard(Player p) {
    this.sidebarService.createSidebarScoreboard(p, generateSidebar(p));
  }

  private Sidebar generateSidebar(Player p) {
    String boardName = ChatColor.BOLD + this.gameConfiguration.getGameName();
    return new Sidebar(boardName, scoreboardItems(p));
  }

  private List<String> scoreboardItems(Player p) {
    switch (this.gameData.getGamePlayerType(p)) {
      case PLAYER:
        return this.playerScoreboard(p);
      case SPECTATOR:
        return this.spectatorSidebar(p);
      default:
        Debug.$("Internal Error. Player Not Type");
        return Arrays.asList("Error", "ContactDev");
    }
  }

  private List<String> playerScoreboard(Player p) {
    List<String> items = sharedScoreboard();
    items.add(ChatColor.YELLOW + "Kit:");
    items.add(ChatColor.GRAY + "" + this.gameData.getSelectedPlayerKits().get(p).getKitName());
    return items;
  }

  private List<String> spectatorSidebar(Player p) {
    List<String> items = sharedScoreboard();
    return items;
  }

  private List<String> sharedScoreboard() {
    List<String> items = new ArrayList<>();
    items.add("           ");
    items.add(ChatColor.YELLOW + "In Game");
    items.add("            ");
    items.add(ChatColor.YELLOW + "Remaining Players:");
    items.add(ChatColor.GRAY + Integer.toString(this.gameData.getPlayers().size()));
    items.add("                ");
    items.add(ChatColor.YELLOW + "Deathmatch:");
    items.add(ChatColor.GRAY + this.inGamePhase.timeUntilDeathmatch());
    items.add("                ");
    return items;
  }
}
