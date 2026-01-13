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
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ScathaTracker {
    private static final String DATA_DIR = "config/scathapro";
    private static final String DATA_FILE = "stats.json";

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final ConfigManager configManager;
    private final File dataFile;
    private JsonObject data;

    // Runtime tracking variables
    private int sessionScathaKills = 0;
    private int sessionRegularWormKills = 0;
    private int sessionRarePets = 0;
    private int sessionEpicPets = 0;
    private int sessionLegendaryPets = 0;
    private int farmingStreak = 0;
    private LocalDate lastFarmingDate = null;

    // Detection variables
    private long lastScathaSpawnTime = -1;
    private long lastWormSpawnTime = -1;
    private long lastScathaKillTime = -1;
    private int lastKnownScathaKills = 0;

    public ScathaTracker(ConfigManager configManager) {
        this.configManager = configManager;
        this.dataFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), DATA_DIR + "/" + DATA_FILE);
        loadData();
    }

    private void loadData() {
        if (dataFile.exists()) {
            try (FileReader reader = new FileReader(dataFile)) {
                data = gson.fromJson(reader, JsonObject.class);
                if (data == null) {
                    data = createDefaultData();
                }
            } catch (IOException e) {
                ScathaProFabric.logError("Failed to load tracking data: " + e.getMessage());
                data = createDefaultData();
            }
        } else {
            data = createDefaultData();
            saveData();
        }
    }

    private JsonObject createDefaultData() {
        JsonObject data = new JsonObject();
        data.addProperty("totalScathaKills", 0);
        data.addProperty("totalRegularWormKills", 0);
        data.addProperty("totalRarePets", 0);
        data.addProperty("totalEpicPets", 0);
        data.addProperty("totalLegendaryPets", 0);
        data.addProperty("bestStreakLength", 0);
        data.addProperty("lastPlayDate", LocalDate.now().toString());
        return data;
    }

    public void saveData() {
        try (FileWriter writer = new FileWriter(dataFile)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            ScathaProFabric.logError("Failed to save tracking data: " + e.getMessage());
        }
    }

    public void recordScathaKill() {
        sessionScathaKills++;
        lastScathaKillTime = System.currentTimeMillis();
        int total = data.get("totalScathaKills").getAsInt();
        data.addProperty("totalScathaKills", total + 1);
        saveData();
    }

    public void recordWormKill() {
        sessionRegularWormKills++;
        lastWormSpawnTime = System.currentTimeMillis();
        int total = data.get("totalRegularWormKills").getAsInt();
        data.addProperty("totalRegularWormKills", total + 1);
        saveData();
    }

    public void recordPetDrop(String rarity) {
        switch (rarity.toLowerCase()) {
            case "rare":
                sessionRarePets++;
                int rareTotal = data.get("totalRarePets").getAsInt();
                data.addProperty("totalRarePets", rareTotal + 1);
                break;
            case "epic":
                sessionEpicPets++;
                int epicTotal = data.get("totalEpicPets").getAsInt();
                data.addProperty("totalEpicPets", epicTotal + 1);
                break;
            case "legendary":
                sessionLegendaryPets++;
                int legTotal = data.get("totalLegendaryPets").getAsInt();
                data.addProperty("totalLegendaryPets", legTotal + 1);
                break;
        }
        saveData();
    }

    // Getters
    public int getSessionScathaKills() { return sessionScathaKills; }
    public int getSessionRegularWormKills() { return sessionRegularWormKills; }
    public int getSessionRarePets() { return sessionRarePets; }
    public int getSessionEpicPets() { return sessionEpicPets; }
    public int getSessionLegendaryPets() { return sessionLegendaryPets; }

    public int getTotalScathaKills() { return data.get("totalScathaKills").getAsInt(); }
    public int getTotalRegularWormKills() { return data.get("totalRegularWormKills").getAsInt(); }
    public int getTotalRarePets() { return data.get("totalRarePets").getAsInt(); }
    public int getTotalEpicPets() { return data.get("totalEpicPets").getAsInt(); }
    public int getTotalLegendaryPets() { return data.get("totalLegendaryPets").getAsInt(); }

    public long getLastScathaKillTime() { return lastScathaKillTime; }
    public long getLastWormSpawnTime() { return lastWormSpawnTime; }
    public long getLastScathaSpawnTime() { return lastScathaSpawnTime; }

    public void setLastScathaSpawnTime(long time) { this.lastScathaSpawnTime = time; }
    public void setLastWormSpawnTime(long time) { this.lastWormSpawnTime = time; }
}
