package namelessju.scathapro.client.detection;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MessageDetection {
    // Kill detection patterns
    private static final Pattern[] SCATHA_KILL_PATTERNS = {
        Pattern.compile("(?i)You killed (\\S+)"),
        Pattern.compile("(?i)\u00a7.+\u00a7.You dealt the final blow to (\\S+)"),
        Pattern.compile("(?i)You defeated (\\S+)"),
        Pattern.compile("(?i)\u00a7f\\[FINAL KILL\\]")
    };

    // Spawn detection patterns
    private static final Pattern[] SPAWN_PATTERNS = {
        Pattern.compile("(?i)A Scatha has spawned"),
        Pattern.compile("(?i)Scatha has spawned in your server"),
        Pattern.compile("(?i)Scatha is here")
    };

    // Pet drop patterns
    private static final Pattern[] PET_DROP_PATTERNS = {
        Pattern.compile("(?i)\u00a7[bcd]\\+[A-Za-z ]+\u00a77DROP"),
        Pattern.compile("(?i)\u00a76\u00a7l[A-Za-z ]+\u00a7r was dropped"),
        Pattern.compile("(?i)You obtained \\[.*\\] Scatha Pet")
    };

    // Rarity detection
    private static final Pattern RARE_PATTERN = Pattern.compile("(?i)\u00a79\\[RARE\\]");
    private static final Pattern EPIC_PATTERN = Pattern.compile("(?i)\u00a75\\[EPIC\\]");
    private static final Pattern LEGENDARY_PATTERN = Pattern.compile("(?i)\u00a76\\[LEGENDARY\\]");

    public static boolean isScathaKill(String message) {
        String clean = cleanMessage(message);
        for (Pattern pattern : SCATHA_KILL_PATTERNS) {
            if (pattern.matcher(clean).find()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isScathaSpawn(String message) {
        String clean = cleanMessage(message);
        for (Pattern pattern : SPAWN_PATTERNS) {
            if (pattern.matcher(clean).find()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPetDrop(String message) {
        String clean = cleanMessage(message);
        for (Pattern pattern : PET_DROP_PATTERNS) {
            if (pattern.matcher(clean).find()) {
                return true;
            }
        }
        return false;
    }

    public static String getPetRarity(String message) {
        if (LEGENDARY_PATTERN.matcher(message).find()) {
            return "LEGENDARY";
        } else if (EPIC_PATTERN.matcher(message).find()) {
            return "EPIC";
        } else if (RARE_PATTERN.matcher(message).find()) {
            return "RARE";
        }
        return null;
    }

    private static String cleanMessage(String message) {
        return message.replaceAll("\u00a7[0-9a-fk-or]", "");
    }

    public static String extractUsername(String message) {
        Pattern pattern = Pattern.compile("(?i)You (?:killed|defeated) (\\S+)");
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
