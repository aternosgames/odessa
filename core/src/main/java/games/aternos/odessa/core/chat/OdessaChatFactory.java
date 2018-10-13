package games.aternos.odessa.core.chat;

import games.aternos.odessa.api.chat.ChatFactory;
import org.bukkit.entity.Player;

import java.util.Random;

/**
 * The default odessa chat factory. Should be used for all odessa games.
 * <p>
 * TODO: All chat messages should be obtained from database using localization later!
 */
public class OdessaChatFactory implements ChatFactory {

    private String gameExplanationMessage;

    /**
     * Instantiates a new Odessa chat factory.
     *
     * @param gameExplanationMessage the game explanation message
     */
    public OdessaChatFactory(String gameExplanationMessage) {
        this.gameExplanationMessage = gameExplanationMessage;
    }

    private final String[] joinMessagePool = new String[]{
        "§9%s §7wants to play!",
        "§9%s §7joined the battle.",
        "§7Look, §9%s §7wants to battle!"
    };

    @Override
    public String getPlayerJoinMessage(String player) {
        String randomMessage = joinMessagePool[new Random().nextInt(joinMessagePool.length)];
        return String.format(randomMessage, player);
    }

    @Override
    public String getCountdownMessage(int secondsLeft) {
        return String.format("§7There are §9%s §7left until game starts!", secondsLeft);
    }

    @Override
    public String getGameStartMessage() {
        return "§9The game is starting!";
    }

    @Override
    public String getGameExplanationMessage() {
        return gameExplanationMessage;
    }

    @Override
    public String getFormattedPlayerName(Player player) {
        return "§6LVL §7| " + player.getName();
    }

    /**
     * Build default odessa chat factory using custom game explanation message.
     *
     * @param gameExplanationMessage the game explanation message
     * @return the chat factory
     */
    public static ChatFactory build(String gameExplanationMessage) {
        return new OdessaChatFactory(gameExplanationMessage);
    }
}
