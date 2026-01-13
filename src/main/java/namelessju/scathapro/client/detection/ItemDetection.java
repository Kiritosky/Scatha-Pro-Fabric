package namelessju.scathapro.client.detection;

import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.CompoundTag;
import java.util.regex.Pattern;

public class ItemDetection {
    private static final Pattern SCATHA_PET_PATTERN = Pattern.compile(
        "(?i)(?:Scatha Pet|\\[EPIC\\] Scatha|\\[RARE\\] Scatha|\\[LEGENDARY\\] Scatha)"
    );

    private static final Pattern RARE_PET_PATTERN = Pattern.compile("\\[RARE\\]");
    private static final Pattern EPIC_PET_PATTERN = Pattern.compile("\\[EPIC\\]");
    private static final Pattern LEGENDARY_PET_PATTERN = Pattern.compile("\\[LEGENDARY\\]");

    public static boolean isScathaPet(ItemStack itemStack) {
        if (itemStack.isEmpty()) {
            return false;
        }

        String displayName = getDisplayName(itemStack);
        return displayName != null && SCATHA_PET_PATTERN.matcher(displayName).find();
    }

    public static String getPetRarity(ItemStack itemStack) {
        if (!isScathaPet(itemStack)) {
            return null;
        }

        String displayName = getDisplayName(itemStack);
        if (displayName == null) {
            return null;
        }

        if (LEGENDARY_PET_PATTERN.matcher(displayName).find()) {
            return "LEGENDARY";
        } else if (EPIC_PET_PATTERN.matcher(displayName).find()) {
            return "EPIC";
        } else if (RARE_PET_PATTERN.matcher(displayName).find()) {
            return "RARE";
        }

        return null;
    }

    private static String getDisplayName(ItemStack itemStack) {
        CompoundTag tag = itemStack.getTag();
        if (tag != null && tag.contains("display")) {
            CompoundTag displayTag = tag.getCompound("display");
            if (displayTag.contains("Name")) {
                return displayTag.getString("Name");
            }
        }
        return null;
    }

    public static boolean hasScathaLore(ItemStack itemStack) {
        CompoundTag tag = itemStack.getTag();
        if (tag == null || !tag.contains("display")) {
            return false;
        }

        CompoundTag displayTag = tag.getCompound("display");
        if (!displayTag.contains("Lore")) {
            return false;
        }

        // Check if lore contains Scatha-related text
        return true;
    }
}
