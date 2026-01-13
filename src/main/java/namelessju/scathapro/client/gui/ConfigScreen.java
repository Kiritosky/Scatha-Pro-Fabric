package namelessju.scathapro.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.network.chat.Component;
import namelessju.scathapro.client.manager.ConfigManager;
import namelessju.scathapro.client.ScathaProClientFabric;

public class ConfigScreen extends Screen {
    private final Screen parent;
    private final ConfigManager config;
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 20;
    private static final int SPACING = 5;

    public ConfigScreen(Screen parent) {
        super(Component.literal("Scatha-Pro Settings"));
        this.parent = parent;
        this.config = ScathaProClientFabric.getInstance().getConfigManager();
    }

    @Override
    protected void init() {
        super.init();
        int startY = this.height / 4;
        int centerX = this.width / 2;

        // Alerts
        this.addRenderableWidget(Button.builder(
            Component.literal("Toggle Alerts: " + (config.getBoolean("alerts.enableSpawningAlert", true) ? "ON" : "OFF")),
            button -> {
                boolean current = config.getBoolean("alerts.enableSpawningAlert", true);
                config.setBoolean("alerts.enableSpawningAlert", !current);
                button.setMessage(Component.literal("Toggle Alerts: " + (!current ? "ON" : "OFF")));
            }
        ).pos(centerX - BUTTON_WIDTH / 2, startY).width(BUTTON_WIDTH).height(BUTTON_HEIGHT).build());

        // Overlay
        this.addRenderableWidget(Button.builder(
            Component.literal("Toggle Overlay: " + (config.getBoolean("display.enableOverlay", true) ? "ON" : "OFF")),
            button -> {
                boolean current = config.getBoolean("display.enableOverlay", true);
                config.setBoolean("display.enableOverlay", !current);
                button.setMessage(Component.literal("Toggle Overlay: " + (!current ? "ON" : "OFF")));
            }
        ).pos(centerX - BUTTON_WIDTH / 2, startY + (BUTTON_HEIGHT + SPACING) * 1).width(BUTTON_WIDTH).height(BUTTON_HEIGHT).build());

        // Stats Display
        this.addRenderableWidget(Button.builder(
            Component.literal("Toggle Stats: " + (config.getBoolean("display.enableStats", true) ? "ON" : "OFF")),
            button -> {
                boolean current = config.getBoolean("display.enableStats", true);
                config.setBoolean("display.enableStats", !current);
                button.setMessage(Component.literal("Toggle Stats: " + (!current ? "ON" : "OFF")));
            }
        ).pos(centerX - BUTTON_WIDTH / 2, startY + (BUTTON_HEIGHT + SPACING) * 2).width(BUTTON_WIDTH).height(BUTTON_HEIGHT).build());

        // Done Button
        this.addRenderableWidget(Button.builder(
            Component.literal("Done"),
            button -> this.onClose()
        ).pos(centerX - BUTTON_WIDTH / 2, startY + (BUTTON_HEIGHT + SPACING) * 4).width(BUTTON_WIDTH).height(BUTTON_HEIGHT).build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(parent);
    }
}
