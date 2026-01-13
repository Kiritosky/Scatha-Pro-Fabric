package me.kiritosky.scathapro

import com.google.gson.GsonBuilder
import java.io.File
import org.slf4j.LoggerFactory

object ScathaProConfig {
    private val LOGGER = LoggerFactory.getLogger("ScathaProConfig")
    private val gson = GsonBuilder().setPrettyPrinting().create()
    private lateinit var configFile: File
    
    private var config: MutableMap<String, Any> = mutableMapOf(
        "enabled" to true,
        "sound_alerts" to true,
        "overlay_enabled" to true,
        "auto_farm" to false
    )

    fun load() {
        LOGGER.info("Loading Scatha Pro configuration")
        // Configuration loading logic will be implemented here
    }

    fun save() {
        LOGGER.info("Saving Scatha Pro configuration")
        // Configuration saving logic will be implemented here
    }

    fun get(key: String): Any? = config[key]

    fun set(key: String, value: Any) {
        config[key] = value
        save()
    }
}
