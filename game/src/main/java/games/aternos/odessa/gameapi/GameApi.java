package games.aternos.odessa.gameapi;

import edu.umd.cs.findbugs.annotations.NonNull;
import games.aternos.odessa.gameapi.game.Game;
import org.bukkit.plugin.java.JavaPlugin;

public class GameApi extends JavaPlugin {

  private static GameApi gameApi;

  @Override
  public void onEnable(){
    gameApi = this;
  }

  public void registerGame(@NonNull Game game){

  }

  public static GameApi getGameApi() {
    return gameApi;
  }

}
