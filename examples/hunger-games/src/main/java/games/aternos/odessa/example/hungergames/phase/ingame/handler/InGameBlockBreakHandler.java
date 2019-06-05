package games.aternos.odessa.example.hungergames.phase.ingame.handler;

import games.aternos.odessa.example.hungergames.phase.ingame.InGamePhase;
import games.aternos.odessa.gameapi.eventhook.Hook;
import games.aternos.odessa.gameapi.eventhook.handler.BlockBreakEventHook;
import org.bukkit.event.block.BlockBreakEvent;

import javax.annotation.Nonnull;

public class InGameBlockBreakHandler {

    private final InGamePhase inGamePhase;

    public InGameBlockBreakHandler(InGamePhase inGamePhase) {
        this.inGamePhase = inGamePhase;
        BlockBreakEventHook.hooks.add(new BlockBreakHandler("InGameBlockBreakHandler"));
    }

    public class BlockBreakHandler extends Hook {

        public BlockBreakHandler(@Nonnull String hookID) {
            super(hookID);
        }

        @Override
        public void run(Object o) {
            ((BlockBreakEvent) o).setCancelled(true);
        }
    }
}
