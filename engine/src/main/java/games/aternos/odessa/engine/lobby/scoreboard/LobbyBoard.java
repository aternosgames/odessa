package games.aternos.odessa.engine.lobby.scoreboard;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.lobby.LobbyControllerOwned;
import games.aternos.odessa.engine.lobby.LobbyState;
import games.aternos.odessa.engine.service.sidebar.Sidebar;
import games.aternos.odessa.engine.service.sidebar.SidebarService;
import games.aternos.odessa.gameapi.game.element.Kit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * The Scoreboard when the Lobby is active
 */
public class LobbyBoard extends LobbyControllerOwned {

  private final SidebarService sidebarService;

  public LobbyBoard(SidebarService sidebarService, LobbyController lobbyController) {
    super(lobbyController);
    this.sidebarService = sidebarService;
  }

  /**
   * Sends the scoreboard out to all Players
   */
  public void pushBoard() {
    for (Player p : this.getOwner().getGameLobbySystem().getGame().getGameData().getPlayers()) {
      this.getSidebarService().createSidebarScoreboard(p, generateSidebar(p));
    }
  }

  /**
   * Sends the scoreboard to one Player
   *
   * @param p Player
   */
  public void pushBoard(Player p) {
    this.getSidebarService().createSidebarScoreboard(p, generateSidebar(p));
  }

  /**
   * Generates the Player Sidebar
   *
   * @param p The Player
   * @return The Generated Sidebar
   */
  @NotNull
  private Sidebar generateSidebar(@Nonnull Player p) {

    String boardName = ChatColor.BOLD + getGameName() + " Lobby";
    return new Sidebar(boardName, scoreboardItems(p));

  }

  /**
   * Returns the Scoreboard items for a Player
   *
   * @param p Player
   * @return Scoreboard Items
   */
  @NotNull
  private List<String> scoreboardItems(@Nonnull Player p) {
    if (this.getOwner().getGameLobbySystem().getLobbyState().equals(LobbyState.WAITINGFORPLAYERS)) {
      return waitingForPlayersItems(p);
    } else if (this.getOwner().getGameLobbySystem().getLobbyState().equals(LobbyState.FINALCALL)) {
      return finalCallItems(p);
    }
    if (this.getOwner().getGameLobbySystem().getLobbyState().equals(LobbyState.COUNTDOWN)) {
      return countdownItems(p);
    }
    return null;
  }

  private List<String> waitingForPlayersItems(Player p) {
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

  private List<String> countdownItems(Player p) {
    List<String> items = new ArrayList<>();

    items.add("           ");
    items.add(ChatColor.YELLOW + "Countdown");
    currentPlayerBoardPiece(items);
    items.add(ChatColor.YELLOW + "Launching:");
    items.add(ChatColor.GRAY + "" + this.getOwner().getTick());
    return items;
  }

  private void currentPlayerBoardPiece(List<String> items) {
    items.add("            ");
    items.add(ChatColor.YELLOW + "Players:");
    items.add(ChatColor.GRAY + Integer.toString(getCurrentPlayerSize()) + " ");
    items.add("               ");
  }

  private List<String> finalCallItems(Player p) {
    List<String> items = new ArrayList<>();
    items.add("           ");
    items.add(ChatColor.YELLOW + "Final Call");
    currentPlayerBoardPiece(items);
    items.add(ChatColor.YELLOW + "Kit:");
    items.add(ChatColor.GRAY + "" + getSelectedKitName(p));
    items.add("                ");
    items.add(ChatColor.YELLOW + "Remaining:");
    items.add(ChatColor.GRAY + "" + this.getOwner().getTick());
    return items;
  }
  private String getGameName() {
    return this.getOwner().getGameLobbySystem().getGame().getGameConfiguration().getGameName();
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
