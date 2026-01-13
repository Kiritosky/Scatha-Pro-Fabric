package namelessju.scathapro;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScathaProFabric implements ModInitializer {
    public static final String MODID = "scathapro";
    public static final String VERSION = "1.4.0";
    public static final String TRUE_MODNAME = "Scatha-Pro";

    private static final Logger LOGGER = LogManager.getLogger(MODID);

    @Override
    public void onInitialize() {
        LOGGER.info("[{}] Initializing version {}", TRUE_MODNAME, VERSION);
    }

    public static void log(String message) {
        LOGGER.info("[{}] {}", TRUE_MODNAME, message);
    }

    public static void logWarning(String message) {
        LOGGER.warn("[{}] {}", TRUE_MODNAME, message);
    }

    public static void logError(String message) {
        LOGGER.error("[{}] {}", TRUE_MODNAME, message);
    }

    public static void logDebug(String message) {
        LOGGER.debug("[{}] {}", TRUE_MODNAME, message);
    }
}
