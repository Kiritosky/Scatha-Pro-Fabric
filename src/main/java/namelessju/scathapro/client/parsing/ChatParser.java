package namelessju.scathapro.client.parsing;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.network.chat.Component;
import namelessju.scathapro.ScathaProFabric;
import namelessju.scathapro.client.manager.ScathaTracker;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ChatParser {
    private final ScathaTracker tracker;

    // Chat message patterns for Hypixel Skyblock
    private static final Pattern SCATHA_SPAWN_PATTERN = Pattern.compile("(?:A Scatha has spawned|Scatha spawning)");
    private static final Pattern SCATHA_KILL_PATTERN = Pattern.compile("(?:You killed Scatha|You dealt the final hit to Scatha)");
    private static final Pattern WORM_SPAWN_PATTERN = Pattern.compile("A Worm has spawned");
    private static final Pattern PET_DROP_PATTERN = Pattern.compile("§(?:6|c|d)([A-Za-z]+)(?:\\s+.*)?Scatha (?:Pet|was dropped)");
    private static final Pattern RARE_PET_PATTERN = Pattern.compile("(?:RARE|rare) (?:pet|Scatha Pet)");
    private static final Pattern EPIC_PET_PATTERN = Pattern.compile("(?:EPIC|epic) (?:pet|Scatha Pet)");
    private static final Pattern LEGENDARY_PET_PATTERN = Pattern.compile("(?:LEGENDARY|legendary) (?:pet|Scatha Pet)");

    public ChatParser(ScathaTracker tracker) {
        this.tracker = tracker;
    }

    public void processChatMessages() {
        Minecraft client = Minecraft.getInstance();
        if (client.gui == null || client.gui.getChat() == null) {
            return;
        }

        GuiNewChat chat = client.gui.getChat();
        // Note: In 1.21.10, direct chat access has changed
        // This is a simplified version - actual implementation may need adjustment based on final API
    }

    public void parseMessage(String message) {
        // Remove color codes for pattern matching
        String cleanMessage = cleanColorCodes(message);

        // Check for Scatha spawn
        if (matchesPattern(cleanMessage, SCATHA_SPAWN_PATTERN)) {
            ScathaProFabric.log("Scatha spawn detected!");
            tracker.setLastScathaSpawnTime(System.currentTimeMillis());
            triggerAlert("Scatha Spawn Detected!");
        }

        // Check for Scatha kill
        if (matchesPattern(cleanMessage, SCATHA_KILL_PATTERN)) {
            ScathaProFabric.log("Scatha kill detected!");
            tracker.recordScathaKill();
            triggerAlert("Scatha Defeated!");
        }

        // Check for worm spawn
        if (matchesPattern(cleanMessage, WORM_SPAWN_PATTERN)) {
            ScathaProFabric.logDebug("Worm spawn detected");
            tracker.setLastWormSpawnTime(System.currentTimeMillis());
        }

        // Check for pet drops
        if (matchesPattern(cleanMessage, LEGENDARY_PET_PATTERN)) {
            ScathaProFabric.log("Legendary pet drop detected!");
            tracker.recordPetDrop("legendary");
            triggerAlert("Legendary Pet Drop!");
        } else if (matchesPattern(cleanMessage, EPIC_PET_PATTERN)) {
            ScathaProFabric.logDebug("Epic pet drop detected");
            tracker.recordPetDrop("epic");
        } else if (matchesPattern(cleanMessage, RARE_PET_PATTERN)) {
            ScathaProFabric.logDebug("Rare pet drop detected");
            tracker.recordPetDrop("rare");
        }
    }

    private boolean matchesPattern(String message, Pattern pattern) {
        return pattern.matcher(message).find();
    }

    private String cleanColorCodes(String message) {
        // Remove Minecraft color codes (§c, §6, etc.)
        return message.replaceAll("§[0-9a-fk-or]", "");
    }

    private void triggerAlert(String message) {
        Minecraft client = Minecraft.getInstance();
        if (client.player != null) {
            // Title alert
            client.gui.setTitle(Component.literal("§6" + message));
            // Sound alert could be added here
        }
    }
}
