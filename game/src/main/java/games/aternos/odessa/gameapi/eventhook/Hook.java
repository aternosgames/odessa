package games.aternos.odessa.gameapi.eventhook;


import org.bukkit.event.Event;

abstract public class Hook<T extends Event> {

    private Class<T> event;

    public Hook(Class<T> event) {
        this.event = event;
    }

    public abstract <event> void run(event event);

}
