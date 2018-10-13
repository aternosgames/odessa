package games.aternos.odessa.core.phase;

import games.aternos.odessa.api.phase.TimedGamePhase;

public abstract class AbstractTimedGamePhase extends AbstractGamePhase implements TimedGamePhase {

    private int timer;

    @Override
    public void update() {
        super.update();
        timer++;
    }

    @Override
    public int getAge() { return this.timer; }
}
