package games.aternos.odessa.engine.lobby.handler;

import games.aternos.odessa.engine.lobby.LobbyController;
import games.aternos.odessa.engine.lobby.LobbyControllerOwned;
import games.aternos.odessa.gameapi.eventhook.Hook;
import games.aternos.odessa.gameapi.eventhook.handler.BlockBreakEventHook;
import org.bukkit.event.block.BlockBreakEvent;

import javax.annotation.Nonnull;

public class LobbyBlockBreakEventHandler extends LobbyControllerOwned {

    public LobbyBlockBreakEventHandler(LobbyController owner) {
        super(owner);
        BlockBreakEventHook.hooks.add(new BlockBreakEventHandler("LobbyBlockBreakEventHandler"));
    }

    public class BlockBreakEventHandler extends Hook {

        public BlockBreakEventHandler(@Nonnull String hookID) {
            super(hookID);
        }

        @Override
        public void run(Object o) {
            BlockBreakEvent event = (BlockBreakEvent) o;
            event.setCancelled(true);
        }
    }
}
