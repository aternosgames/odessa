package games.aternos.odessa.example.hungergames;

import games.aternos.odessa.example.hungergames.kit.HungerGamesKit;
import games.aternos.odessa.gameapi.game.GameConfiguration;
import games.aternos.odessa.gameapi.game.element.Kit;

import java.util.List;

public class HungerGamesGameConfiguration extends GameConfiguration {

    /**
     * Maximum amount a game can run 30 minutes
     */
    private static final Integer TIME_GAME_MAX = 1800;

    /**
     * Time until deathmatch = 15 minutes
     */
    private static final Integer TIME_FORCE_DEATHMATCH = 900;

    /**
     * The amount of players remaining until a deathmatch is started
     */
    private static final Integer PLAYER_FORCE_DEATHMATCH = 4;

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

    public static Integer getTimeGameMax() {
        return TIME_GAME_MAX;
    }

    public static Integer getTimeForceDeathmatch() {
        return TIME_FORCE_DEATHMATCH;
    }

    public static Integer getPlayerForceDeathmatch() {
        return PLAYER_FORCE_DEATHMATCH;
    }
}
