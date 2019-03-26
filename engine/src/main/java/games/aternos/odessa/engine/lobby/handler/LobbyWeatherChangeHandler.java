package games.aternos.odessa.engine.lobby.handler;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.lobby.LobbyControllerOwned;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class LobbyWeatherChangeHandler extends LobbyControllerOwned implements Listener {
  public LobbyWeatherChangeHandler(LobbyController owner) {
    super(owner);
  }

  @EventHandler
  public void onWeatherChangeLobby(WeatherChangeEvent event) {
    event.setCancelled(true);
  }
}
