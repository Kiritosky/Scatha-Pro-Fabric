package namelessju.scathapro.client.alert;

import net.minecraft.client.Minecraft;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.network.chat.Component;
import namelessju.scathapro.ScathaProFabric;
import namelessju.scathapro.client.manager.ConfigManager;

import java.util.Queue;
import java.util.LinkedList;

public class AlertManager {
    private final ConfigManager config;
    private final Queue<Alert> alertQueue = new LinkedList<>();
    private int alertDisplayTicks = 0;
    private static final int ALERT_DURATION = 100; // 5 seconds at 20 ticks/sec

    public AlertManager(ConfigManager config) {
        this.config = config;
    }

    public void queueAlert(AlertType type, String message) {
        if (!config.getBoolean("alerts.enableSpawningAlert", true)) {
            return;
        }

        Alert alert = new Alert(type, message);
        alertQueue.add(alert);
        playAlertSound();
        ScathaProFabric.logDebug("Alert queued: " + message);
    }

    private void playAlertSound() {
        String alertMode = config.getString("alerts.alertMode", "sound");
        float volume = config.getFloat("alerts.alertVolume", 1.0f);

        if (alertMode.equals("sound") || alertMode.equals("both")) {
            Minecraft client = Minecraft.getInstance();
            if (client.getSoundManager() != null) {
                // Play a bell sound or custom sound
                client.getSoundManager().play(
                    net.minecraft.client.resources.sounds.SimpleSoundInstance.forUI(
                        SoundEvents.BELL_BLOCK,
                        volume
                    )
                );
            }
        }
    }

    public void tick() {
        if (!alertQueue.isEmpty()) {
            alertDisplayTicks++;
            if (alertDisplayTicks >= ALERT_DURATION) {
                alertQueue.poll();
                alertDisplayTicks = 0;
            }
        }
    }

    public Alert getCurrentAlert() {
        return alertQueue.peek();
    }

    public static class Alert {
        public final AlertType type;
        public final String message;
        public final long timestamp;

        public Alert(AlertType type, String message) {
            this.type = type;
            this.message = message;
            this.timestamp = System.currentTimeMillis();
        }
    }

    public enum AlertType {
        SCATHA_SPAWN("\u00a74\u00a7lScatha Spawning!"),
        SCATHA_KILL("\u00a72\u00a7lScatha Defeated!"),
        PET_DROP_RARE("\u00a7bRare Pet!"),
        PET_DROP_EPIC("\u00a75Epic Pet!"),
        PET_DROP_LEGENDARY("\u00a76\u00a7lLegendary Pet!");

        public final String defaultMessage;

        AlertType(String defaultMessage) {
            this.defaultMessage = defaultMessage;
        }
    }
}
