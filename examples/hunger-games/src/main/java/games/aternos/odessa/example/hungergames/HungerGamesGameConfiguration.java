package games.aternos.odessa.example.hungergames;

import games.aternos.odessa.example.hungergames.kit.HungerGamesKit;
import games.aternos.odessa.gameapi.game.GameConfiguration;
import games.aternos.odessa.gameapi.game.element.Kit;

import java.util.List;

public class HungerGamesGameConfiguration extends GameConfiguration {

  public HungerGamesGameConfiguration() {
    this.setGameName("HungerGames");
    this.setMaxPlayers(16);
    this.setMinPlayers(2);
    this.setGameKits(getKits());
  }
  private List<Kit> getKits() {
    HungerGamesKit hungerGamesKit = new HungerGamesKit();
    return hungerGamesKit.getKits();
  }

}
