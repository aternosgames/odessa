package games.aternos.odessa.api.spawn;

import org.bukkit.entity.Player;

import java.util.List;

public interface SpawnStrategy {

    void spawnPlayers(List<Player> playerList);

}
