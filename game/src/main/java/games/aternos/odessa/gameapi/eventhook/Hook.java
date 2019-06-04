package games.aternos.odessa.gameapi.eventhook;

import games.aternos.odessa.gameapi.Debug;
import org.bukkit.ChatColor;

import javax.annotation.Nonnull;

public abstract class Hook {

    private String hookID;

    public Hook(@Nonnull String hookID) {
    Debug.$(
        ChatColor.GREEN
            + "Hook Created: "
            + this.getClass().getPackage()
            + "."
            + this.getClass().getName());
  }

  public abstract void run(Object o);

    @Nonnull
    public String getHookID() {
        return hookID;
    }
}
