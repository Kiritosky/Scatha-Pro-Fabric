package namelessju.scathapro.client.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.network.chat.Component;
import namelessju.scathapro.ScathaProFabric;
import namelessju.scathapro.client.ScathaProClientFabric;
import namelessju.scathapro.client.manager.ConfigManager;
import namelessju.scathapro.client.manager.ScathaTracker;
import namelessju.scathapro.client.parsing.ChatParser;
import namelessju.scathapro.client.overlay.ScathaOverlay;

import java.util.LinkedList;
import java.util.Queue;

public class ClientEventHandler implements ClientTickEvents.StartTick {
    private final ScathaProClientFabric mod;
    private final ConfigManager configManager;
    private final ScathaTracker tracker;
    private final ChatParser chatParser;
    private final ScathaOverlay overlay;
    private int tickCounter = 0;

    public ClientEventHandler(ScathaProClientFabric mod) {
        this.mod = mod;
        this.configManager = mod.getConfigManager();
        this.tracker = mod.getScathaTracker();
        this.chatParser = new ChatParser(tracker);
        this.overlay = new ScathaOverlay(tracker, configManager);
    }

    @Override
    public void onStartTick(Minecraft client) {
        tickCounter++;

        if (tickCounter % 20 == 0) { // Every 1 second
            updateChatDetection(client);
        }

        if (tickCounter % 5 == 0) { // Every 0.25 seconds
            handleKeybindings(client);
        }
    }

    private void updateChatDetection(Minecraft client) {
        if (client.player == null) return;

        GuiNewChat chat = client.gui.getChat();
        if (chat == null) return;

        // Parse chat messages for Scatha events
        chatParser.processChatMessages();
    }

    private void handleKeybindings(Minecraft client) {
        while (mod.getInputManager().getToggleOverlayKey().wasPressed()) {
            boolean enabled = configManager.getBoolean("display.enableOverlay", true);
            configManager.setBoolean("display.enableOverlay", !enabled);
            String status = !enabled ? "enabled" : "disabled";
            if (client.player != null) {
                client.player.displayClientMessage(
                    Component.literal("§6[Scatha-Pro] Overlay " + status),
                    true
                );
            }
        }

        while (mod.getInputManager().getToggleStatsKey().wasPressed()) {
            boolean enabled = configManager.getBoolean("display.enableStats", true);
            configManager.setBoolean("display.enableStats", !enabled);
            String status = !enabled ? "enabled" : "disabled";
            if (client.player != null) {
                client.player.displayClientMessage(
                    Component.literal("§6[Scatha-Pro] Stats Display " + status),
                    true
                );
            }
        }

        while (mod.getInputManager().getResetStatsKey().wasPressed()) {
            if (client.player != null) {
                client.player.displayClientMessage(
                    Component.literal("§6[Scatha-Pro] Stats reset requires confirmation"),
                    true
                );
            }
        }
    }

    public void onRenderHud() {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null || !configManager.getBoolean("display.enableOverlay", true)) {
            return;
        }

        overlay.render(client.getWindow());
    }
}
