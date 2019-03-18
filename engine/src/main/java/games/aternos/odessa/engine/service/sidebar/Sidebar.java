package games.aternos.odessa.engine.service.sidebar;

import java.util.List;

public class Sidebar {

  private String boardName;

  private List<String> objects;

  private int scorebase;

  public Sidebar(String boardName, List<String> objects) {
    this.boardName = boardName;
    this.objects = objects;
    this.scorebase = 100;
  }

  public String getBoardName() {
    return boardName;
  }

  public void setBoardName(String boardName) {
    this.boardName = boardName;
  }

  public List<String> getObjects() {
    return objects;
  }

  public void setObjects(List<String> objects) {
    this.objects = objects;
  }

  public Integer getNextScore() {
    scorebase = scorebase - 1;

    if (scorebase == 0) {
      scorebase = 1000;
    }

    return scorebase;
  }
}
