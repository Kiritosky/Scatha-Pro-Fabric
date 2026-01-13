package namelessju.scathapro.client.detection;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import java.util.regex.Pattern;

public class AreaDetection {
    private static final Pattern CRYSTAL_HOLLOWS_PATTERN = Pattern.compile(
        "(?i)(?:Crystal Hollows|Fairylossom)"
    );

    private static final Pattern DEEP_CAVERNS_PATTERN = Pattern.compile(
        "(?i)(?:Deep Caverns|Dwarven Mines)"
    );

    private static final Pattern SKYBLOCK_PATTERN = Pattern.compile(
        "(?i)(?:Hypixel SkyBlock|SkyBlock|Skyblock)"
    );

    public enum SkyblockArea {
        CRYSTAL_HOLLOWS("Crystal Hollows"),
        DEEP_CAVERNS("Deep Caverns"),
        DWARVEN_MINES("Dwarven Mines"),
        GARDEN("Garden"),
        UNKNOWN("Unknown");

        public final String displayName;

        SkyblockArea(String displayName) {
            this.displayName = displayName;
        }
    }

    public static SkyblockArea detectCurrentArea(String scoreboardTitle) {
        if (scoreboardTitle == null) {
            return SkyblockArea.UNKNOWN;
        }

        String cleanTitle = cleanString(scoreboardTitle);

        if (CRYSTAL_HOLLOWS_PATTERN.matcher(cleanTitle).find()) {
            return SkyblockArea.CRYSTAL_HOLLOWS;
        } else if (DEEP_CAVERNS_PATTERN.matcher(cleanTitle).find()) {
            return SkyblockArea.DEEP_CAVERNS;
        } else if (cleanTitle.contains("Dwarven") || cleanTitle.contains("Mines")) {
            return SkyblockArea.DWARVEN_MINES;
        } else if (cleanTitle.contains("Garden")) {
            return SkyblockArea.GARDEN;
        }

        return SkyblockArea.UNKNOWN;
    }

    public static boolean isInSkyblock(Minecraft client) {
        if (client.player == null || client.player.getLevel() == null) {
            return false;
        }

        // Check if player is on Hypixel server
        if (client.getConnection() == null) {
            return false;
        }

        // This would need actual Hypixel detection logic
        // For now, we check if the world is not null and other conditions
        return client.player.getLevel().isClientSide;
    }

    public static boolean isInCrystalHollows(SkyblockArea area) {
        return area == SkyblockArea.CRYSTAL_HOLLOWS;
    }

    private static String cleanString(String input) {
        // Remove color codes and special characters
        return input.replaceAll("\u00a7[0-9a-fk-or]", "")
                   .replaceAll("[^a-zA-Z0-9\\s]", "");
    }
}
