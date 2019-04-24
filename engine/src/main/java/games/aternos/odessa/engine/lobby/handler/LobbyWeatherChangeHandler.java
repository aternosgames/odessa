package games.aternos.odessa.engine.lobby.handler;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.lobby.LobbyControllerOwned;
import games.aternos.odessa.gameapi.eventhook.Hook;
import games.aternos.odessa.gameapi.eventhook.handler.WeatherChangeEventHook;
import org.bukkit.event.weather.WeatherChangeEvent;

public class LobbyWeatherChangeHandler extends LobbyControllerOwned {
  public LobbyWeatherChangeHandler(LobbyController owner) {
    super(owner);
      WeatherChangeEventHook.hooks.add(new WeatherChangeHandler());
  }

    public class WeatherChangeHandler extends Hook {

        @Override
        public void run(Object o) {
            ((WeatherChangeEvent) o).setCancelled(true);
    }
  }
}
