package namelessju.scathapro.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import namelessju.scathapro.ScathaProFabric;
import namelessju.scathapro.client.manager.ConfigManager;
import namelessju.scathapro.client.manager.InputManager;
import namelessju.scathapro.client.manager.ScathaTracker;
import namelessju.scathapro.client.event.ClientEventHandler;

@Environment(EnvType.CLIENT)
public class ScathaProClientFabric implements ClientModInitializer {
    private static ScathaProClientFabric instance;
    private ConfigManager configManager;
    private ScathaTracker scathaTracker;
    private InputManager inputManager;
    private ClientEventHandler eventHandler;

    @Override
    public void onInitializeClient() {
        instance = this;
        ScathaProFabric.log("Initializing Scatha-Pro client");

        // Initialize managers
        this.configManager = new ConfigManager();
        this.scathaTracker = new ScathaTracker(configManager);
        this.inputManager = new InputManager(this);
        this.eventHandler = new ClientEventHandler(this);

        // Register event handlers
        ClientTickEvents.START_CLIENT_TICK.register(this.eventHandler);
        HudRenderCallback.EVENT.register((drawContext, tickCounter) -> {
            this.eventHandler.onRenderHud();
        });
    }

    public static ScathaProClientFabric getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public ScathaTracker getScathaTracker() {
        return scathaTracker;
    }

    public InputManager getInputManager() {
        return inputManager;
    }

    public ClientEventHandler getEventHandler() {
        return eventHandler;
    }
}
