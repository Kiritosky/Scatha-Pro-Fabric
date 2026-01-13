package namelessju.scathapro.client.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;
import namelessju.scathapro.ScathaProFabric;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigManager {
    private static final String CONFIG_DIR = "config/scathapro";
    private static final String CONFIG_FILE = "config.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private JsonObject config;
    private final File configDir;
    private final File configFile;

    public ConfigManager() {
        this.configDir = new File(FabricLoader.getInstance().getConfigDir().toFile(), CONFIG_DIR);
        this.configFile = new File(configDir, CONFIG_FILE);

        if (!configDir.exists()) {
            configDir.mkdirs();
        }

        loadConfig();
    }

    private void loadConfig() {
        if (configFile.exists()) {
            try (FileReader reader = new FileReader(configFile)) {
                config = gson.fromJson(reader, JsonObject.class);
                if (config == null) {
                    config = createDefaultConfig();
                }
            } catch (IOException e) {
                ScathaProFabric.logError("Failed to load config: " + e.getMessage());
                config = createDefaultConfig();
            }
        } else {
            config = createDefaultConfig();
            saveConfig();
        }
    }

    private JsonObject createDefaultConfig() {
        JsonObject config = new JsonObject();

        // Alerts
        JsonObject alerts = new JsonObject();
        alerts.addProperty("enableSpawningAlert", true);
        alerts.addProperty("enableKillAlert", true);
        alerts.addProperty("alertMode", "sound"); // sound, title, chat
        alerts.addProperty("alertVolume", 1.0f);
        config.add("alerts", alerts);

        // Display
        JsonObject display = new JsonObject();
        display.addProperty("enableOverlay", true);
        display.addProperty("enableStats", true);
        display.addProperty("overlayX", 10);
        display.addProperty("overlayY", 10);
        display.addProperty("overlayScale", 1.0f);
        config.add("display", display);

        // Tracking
        JsonObject tracking = new JsonObject();
        tracking.addProperty("trackKills", true);
        tracking.addProperty("trackDrops", true);
        tracking.addProperty("trackStats", true);
        config.add("tracking", tracking);

        // Advanced
        JsonObject advanced = new JsonObject();
        advanced.addProperty("debugMode", false);
        advanced.addProperty("checkForUpdates", true);
        config.add("advanced", advanced);

        return config;
    }

    public void saveConfig() {
        try (FileWriter writer = new FileWriter(configFile)) {
            gson.toJson(config, writer);
        } catch (IOException e) {
            ScathaProFabric.logError("Failed to save config: " + e.getMessage());
        }
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        String[] keys = key.split("\\.");
        JsonObject current = config;
        for (int i = 0; i < keys.length - 1; i++) {
            if (current.has(keys[i])) {
                current = current.getAsJsonObject(keys[i]);
            } else {
                return defaultValue;
            }
        }
        if (current.has(keys[keys.length - 1])) {
            return current.get(keys[keys.length - 1]).getAsBoolean();
        }
        return defaultValue;
    }

    public int getInt(String key, int defaultValue) {
        String[] keys = key.split("\\.");
        JsonObject current = config;
        for (int i = 0; i < keys.length - 1; i++) {
            if (current.has(keys[i])) {
                current = current.getAsJsonObject(keys[i]);
            } else {
                return defaultValue;
            }
        }
        if (current.has(keys[keys.length - 1])) {
            return current.get(keys[keys.length - 1]).getAsInt();
        }
        return defaultValue;
    }

    public float getFloat(String key, float defaultValue) {
        String[] keys = key.split("\\.");
        JsonObject current = config;
        for (int i = 0; i < keys.length - 1; i++) {
            if (current.has(keys[i])) {
                current = current.getAsJsonObject(keys[i]);
            } else {
                return defaultValue;
            }
        }
        if (current.has(keys[keys.length - 1])) {
            return current.get(keys[keys.length - 1]).getAsFloat();
        }
        return defaultValue;
    }

    public String getString(String key, String defaultValue) {
        String[] keys = key.split("\\.");
        JsonObject current = config;
        for (int i = 0; i < keys.length - 1; i++) {
            if (current.has(keys[i])) {
                current = current.getAsJsonObject(keys[i]);
            } else {
                return defaultValue;
            }
        }
        if (current.has(keys[keys.length - 1])) {
            return current.get(keys[keys.length - 1]).getAsString();
        }
        return defaultValue;
    }

    public void setBoolean(String key, boolean value) {
        setConfigValue(key, value);
    }

    public void setInt(String key, int value) {
        setConfigValue(key, value);
    }

    public void setFloat(String key, float value) {
        setConfigValue(key, value);
    }

    public void setString(String key, String value) {
        setConfigValue(key, value);
    }

    private void setConfigValue(String key, Object value) {
        String[] keys = key.split("\\.");
        JsonObject current = config;
        for (int i = 0; i < keys.length - 1; i++) {
            if (!current.has(keys[i])) {
                current.add(keys[i], new JsonObject());
            }
            current = current.getAsJsonObject(keys[i]);
        }
        if (value instanceof Boolean) {
            current.addProperty(keys[keys.length - 1], (Boolean) value);
        } else if (value instanceof Integer) {
            current.addProperty(keys[keys.length - 1], (Integer) value);
        } else if (value instanceof Float) {
            current.addProperty(keys[keys.length - 1], (Float) value);
        } else if (value instanceof String) {
            current.addProperty(keys[keys.length - 1], (String) value);
        }
        saveConfig();
    }
}
