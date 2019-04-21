package games.aternos.odessa.engine.service.sidebar;

import games.aternos.odessa.engine.service.Service;
import games.aternos.odessa.gameapi.GameApi;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.HashMap;
import java.util.List;

public class SidebarService extends Service {
  /**
   * HashMap with Player Boards inside. Static to remain consistent across all instances and to therefore prevent errors.
   */
  private static final HashMap<Player, Scoreboard> playerBoards = new HashMap<>();
  /**
   * Scoreboard Manager Instance. Generally from Bukkit#getScoreboardManager
   */
  private final ScoreboardManager scoreboardManager;

  public SidebarService(GameApi gameApi, ScoreboardManager scoreboardManager) {
    super(gameApi);
    this.scoreboardManager = scoreboardManager;
  }

  private static HashMap<Player, Scoreboard> getPlayerBoards() {
    return playerBoards;
  }

  /**
   * Creates a Global Sidebar Scoreboard
   */
  public void createSidebarScoreboard(Sidebar sidebar, List<Player> players) {
    for (Player p : players) {
      createSidebarScoreboard(p, sidebar);
    }
  }

  /**
   * Creates an individual Sidebar Scorebaord
   *
   * @param p Player
   */
  public void createSidebarScoreboard(Player p, Sidebar sidebar) {

    if (!this.isInPlayerBoards(p)) {
      this.addToPlayerBoards(p, scoreboardManager.getNewScoreboard());
    } else {
      this.getPlayerScoreboard(p).getObjective(DisplaySlot.SIDEBAR).unregister();
    }
    Scoreboard s = this.getPlayerScoreboard(p);
    p.setScoreboard(s);

    Objective obj = s.registerNewObjective("foo", "dummy");
    obj.setDisplaySlot(DisplaySlot.SIDEBAR);
    obj.setDisplayName(sidebar.getBoardName());

    for (String object : sidebar.getObjects()) {
      obj.getScore(object).setScore(sidebar.getNextScore());
    }
  }

  private void addToPlayerBoards(Player p, Scoreboard s) {
    playerBoards.put(p, s);
  }

  public void removeFromPlayerBoards(Player p) {
    playerBoards.remove(p);
  }

  private boolean isInPlayerBoards(Player p) {
    return playerBoards.keySet().contains(p);
  }

  private Scoreboard getPlayerScoreboard(Player p) {
    return getPlayerBoards().get(p);
  }
}
