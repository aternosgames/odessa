package games.aternos.odessa.engine.service.scoreboard;

import games.aternos.odessa.engine.Service;
import games.aternos.odessa.gameapi.GameApi;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Score;

public class ScoreboardService extends Service {
  protected ScoreboardService(GameApi gameApi) {
    super(gameApi);
  }

  public void setUserScoreboard(Scoreboard scoreboard, Player p) {

    String displayName = scoreboard.getElements().get(0); // first item


    if (p.getScoreboard() == null || p.getScoreboard().getObjectives().size() != 1) {
      p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }
    if (p.getScoreboard().getObjective(p.getUniqueId().toString().substring(0, 16)) == null) {
      p.getScoreboard().registerNewObjective(p.getUniqueId().toString().substring(0, 16), "dummy");
      p.getScoreboard().getObjective(p.getUniqueId().toString().substring(0, 16)).setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    for (String s : scoreboard.getElements()) {

      int index = scoreboard.getElements().indexOf(s);

      Score score = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(s);
      score.setScore(index);

    }

  }

}
