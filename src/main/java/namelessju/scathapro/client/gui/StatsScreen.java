package namelessju.scathapro.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import namelessju.scathapro.client.manager.ScathaTracker;
import namelessju.scathapro.client.ScathaProClientFabric;

public class StatsScreen extends Screen {
    private final Screen parent;
    private final ScathaTracker tracker;
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 20;

    public StatsScreen(Screen parent) {
        super(Component.literal("Scatha-Pro Statistics"));
        this.parent = parent;
        this.tracker = ScathaProClientFabric.getInstance().getScathaTracker();
    }

    @Override
    protected void init() {
        super.init();
        int centerX = this.width / 2;
        int bottomY = this.height - 30;

        this.addRenderableWidget(Button.builder(
            Component.literal("Back"),
            button -> this.onClose()
        ).pos(centerX - BUTTON_WIDTH / 2, bottomY).width(BUTTON_WIDTH).height(BUTTON_HEIGHT).build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);

        int y = 40;
        guiGraphics.drawString(this.font, "§6Session Statistics", this.width / 2 - 50, y, 0xFFFF00);
        y += 15;
        guiGraphics.drawString(this.font, "Scatha Kills: " + tracker.getSessionScathaKills(), 20, y, 0xFFFFFF);
        y += 12;
        guiGraphics.drawString(this.font, "Worm Kills: " + tracker.getSessionRegularWormKills(), 20, y, 0xFFFFFF);
        y += 12;
        guiGraphics.drawString(this.font, "Rare Pets: " + tracker.getSessionRarePets(), 20, y, 0xFFFFFF);
        y += 12;
        guiGraphics.drawString(this.font, "Epic Pets: " + tracker.getSessionEpicPets(), 20, y, 0xFFFFFF);
        y += 12;
        guiGraphics.drawString(this.font, "Legendary Pets: " + tracker.getSessionLegendaryPets(), 20, y, 0xFFFFFF);

        y += 20;
        guiGraphics.drawString(this.font, "§6Total Statistics", this.width / 2 - 50, y, 0xFFFF00);
        y += 15;
        guiGraphics.drawString(this.font, "Total Scatha Kills: " + tracker.getTotalScathaKills(), 20, y, 0xFFFFFF);
        y += 12;
        guiGraphics.drawString(this.font, "Total Worm Kills: " + tracker.getTotalRegularWormKills(), 20, y, 0xFFFFFF);
        y += 12;
        guiGraphics.drawString(this.font, "Total Rare Pets: " + tracker.getTotalRarePets(), 20, y, 0xFFFFFF);
        y += 12;
        guiGraphics.drawString(this.font, "Total Epic Pets: " + tracker.getTotalEpicPets(), 20, y, 0xFFFFFF);
        y += 12;
        guiGraphics.drawString(this.font, "Total Legendary Pets: " + tracker.getTotalLegendaryPets(), 20, y, 0xFFFFFF);

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(parent);
    }
}
