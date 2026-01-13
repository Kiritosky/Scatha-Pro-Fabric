package namelessju.scathapro.client.manager;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import namelessju.scathapro.client.ScathaProClientFabric;
import org.lwjgl.glfw.GLFW;

public class InputManager {
    private final ScathaProClientFabric mod;
    private KeyBinding toggleOverlayKey;
    private KeyBinding toggleStatsKey;
    private KeyBinding resetStatsKey;

    public InputManager(ScathaProClientFabric mod) {
        this.mod = mod;
        registerKeybindings();
    }

    private void registerKeybindings() {
        toggleOverlayKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.scathapro.toggle_overlay",
                GLFW.GLFW_KEY_O,
                "category.scathapro"
        ));

        toggleStatsKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.scathapro.toggle_stats",
                GLFW.GLFW_KEY_P,
                "category.scathapro"
        ));

        resetStatsKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.scathapro.reset_stats",
                GLFW.GLFW_KEY_R,
                "category.scathapro"
        ));
    }

    public KeyBinding getToggleOverlayKey() { return toggleOverlayKey; }
    public KeyBinding getToggleStatsKey() { return toggleStatsKey; }
    public KeyBinding getResetStatsKey() { return resetStatsKey; }
}
