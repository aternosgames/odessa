package games.aternos.odessa.api.spawn;

import java.util.Collection;

public interface SpawnStrategy<T> {

    void spawn(Collection<T> elements);

}
