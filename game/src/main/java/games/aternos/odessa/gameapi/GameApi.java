package games.aternos.odessa.gameapi;

import edu.umd.cs.findbugs.annotations.NonNull;
import games.aternos.odessa.gameapi.game.Game;
import org.bukkit.plugin.java.JavaPlugin;

public class GameApi extends JavaPlugin {

  private static GameApi gameApi;

  private Game game;

  @Override
  public void onEnable(){
    gameApi = this;
  }

  public void registerGame(@NonNull Game game) throws Exception {

    if(this.getGame() != null){
      throw new Exception("Game Already Registered"); //todo: custom clean exceptions
    }
    this.setGame(game);
    this.getGame().initialize();

  }

  public Game getGame() {
    return game;
  }

  private void setGame(Game game) {
    this.game = game;
  }

  public static GameApi getGameApi() {
    return gameApi;
  }

}
