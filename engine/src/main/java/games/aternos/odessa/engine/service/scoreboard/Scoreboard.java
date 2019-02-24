package games.aternos.odessa.engine.service.scoreboard;

import edu.umd.cs.findbugs.annotations.NonNull;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

  private final ScoreboardService owner;

  private List<Player> usersinBoard;

  private List<String> elements;

  public Scoreboard(@Nonnull ScoreboardService owner) {
    this.owner = owner;
    this.usersinBoard = new ArrayList<>();
    this.elements = new ArrayList<>();
  }

  public Scoreboard(@Nonnull ScoreboardService owner, @Nonnull List<Player> usersinBoard, @NonNull List<String> elements) {

    this.owner = owner;
    this.usersinBoard = usersinBoard;
    this.elements = elements;

  }

  public void addPlayer(@NonNull Player player) {
    this.usersinBoard.add(player);
  }

  public void removePlayer(@NonNull Player player) {
    this.usersinBoard.remove(player);
  }

  public void update(@NonNull List<String> newElements) {
    this.elements = newElements;
    for (Player p : usersinBoard) {
      this.owner.setUserScoreboard(this, p);
    }
  }

  public void update() {
    for (Player p : usersinBoard) {
      this.owner.setUserScoreboard(this, p);
    }
  }

  public ScoreboardService getOwner() {
    return owner;
  }

  public List<Player> getUsersinBoard() {
    return usersinBoard;
  }

  public void setUsersinBoard(List<Player> usersinBoard) {
    this.usersinBoard = usersinBoard;
  }

  public List<String> getElements() {
    return elements;
  }

  public void setElements(List<String> elements) {
    this.elements = elements;
  }
}
