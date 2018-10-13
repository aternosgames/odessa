package games.aternos.odessa.api.spawn;

import java.util.Collection;

import org.bukkit.entity.Player;

public interface PlayerSpawnStrategy extends SpawnStrategy<Player> {

    @Override
    void spawn(Collection<Player> players);

}