package namelessju.scathapro.client.overlay;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.window.Window;
import namelessju.scathapro.client.manager.ConfigManager;
import namelessju.scathapro.client.manager.ScathaTracker;

public class ScathaOverlay {
    private final ScathaTracker tracker;
    private final ConfigManager config;

    public ScathaOverlay(ScathaTracker tracker, ConfigManager config) {
        this.tracker = tracker;
        this.config = config;
    }

    public void render(Window window) {
        if (!config.getBoolean("display.enableStats", true)) {
            return;
        }

        int x = config.getInt("display.overlayX", 10);
        int y = config.getInt("display.overlayY", 10);
        float scale = config.getFloat("display.overlayScale", 1.0f);

        String[] statsLines = getStatsLines();
        renderStats(x, y, scale, statsLines);
    }

    private String[] getStatsLines() {
        return new String[]{
            "§6§lScatha-Pro",
            "§bSession: §f" + tracker.getSessionScathaKills() + " kills",
            "§bTotal: §f" + tracker.getTotalScathaKills() + " kills",
            "§6Rare: §f" + tracker.getSessionRarePets() + " §6Epic: §f" + tracker.getSessionEpicPets() + " §6Legendary: §f" + tracker.getSessionLegendaryPets()
        };
    }

    private void renderStats(int x, int y, float scale, String[] lines) {
        // This would be called from HUD rendering context
        // Implementation depends on GuiGraphics availability in the render event
    }
}
