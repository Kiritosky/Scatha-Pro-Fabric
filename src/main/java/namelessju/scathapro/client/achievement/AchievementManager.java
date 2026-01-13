package namelessju.scathapro.client.achievement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;
import namelessju.scathapro.ScathaProFabric;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AchievementManager {
    private static final String DATA_DIR = "config/scathapro";
    private static final String ACHIEVEMENTS_FILE = "achievements.json";

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final File achievementsFile;
    private JsonObject achievements;
    private final Map<String, Boolean> unlockedAchievements = new HashMap<>();

    public AchievementManager() {
        this.achievementsFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), DATA_DIR + "/" + ACHIEVEMENTS_FILE);
        loadAchievements();
    }

    private void loadAchievements() {
        if (achievementsFile.exists()) {
            try (FileReader reader = new FileReader(achievementsFile)) {
                achievements = gson.fromJson(reader, JsonObject.class);
                if (achievements == null) {
                    achievements = createDefaultAchievements();
                }
                // Load into map
                achievements.keySet().forEach(key -> 
                    unlockedAchievements.put(key, achievements.get(key).getAsBoolean())
                );
            } catch (IOException e) {
                ScathaProFabric.logError("Failed to load achievements: " + e.getMessage());
                achievements = createDefaultAchievements();
            }
        } else {
            achievements = createDefaultAchievements();
            saveAchievements();
        }
    }

    private JsonObject createDefaultAchievements() {
        JsonObject achievements = new JsonObject();
        achievements.addProperty("first_kill", false);
        achievements.addProperty("ten_kills", false);
        achievements.addProperty("hundred_kills", false);
        achievements.addProperty("thousand_kills", false);
        achievements.addProperty("first_pet_drop", false);
        achievements.addProperty("rare_pet_drop", false);
        achievements.addProperty("epic_pet_drop", false);
        achievements.addProperty("legendary_pet_drop", false);
        achievements.addProperty("three_day_streak", false);
        achievements.addProperty("seven_day_streak", false);
        return achievements;
    }

    public void saveAchievements() {
        try (FileWriter writer = new FileWriter(achievementsFile)) {
            gson.toJson(achievements, writer);
        } catch (IOException e) {
            ScathaProFabric.logError("Failed to save achievements: " + e.getMessage());
        }
    }

    public void unlockAchievement(String id) {
        if (!unlockedAchievements.getOrDefault(id, false)) {
            unlockedAchievements.put(id, true);
            achievements.addProperty(id, true);
            saveAchievements();
            ScathaProFabric.log("Achievement unlocked: " + id);
        }
    }

    public boolean isUnlocked(String id) {
        return unlockedAchievements.getOrDefault(id, false);
    }

    public void checkAndUnlockAchievements(int totalKills, int petDropCount) {
        if (totalKills >= 1) unlockAchievement("first_kill");
        if (totalKills >= 10) unlockAchievement("ten_kills");
        if (totalKills >= 100) unlockAchievement("hundred_kills");
        if (totalKills >= 1000) unlockAchievement("thousand_kills");
        if (petDropCount >= 1) unlockAchievement("first_pet_drop");
    }
}
