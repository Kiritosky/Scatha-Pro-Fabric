package me.kiritosky.scathapro

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object ScathaProMod : ModInitializer {
    private val LOGGER = LoggerFactory.getLogger("Scatha Pro")

    override fun onInitialize() {
        LOGGER.info("Initializing Scatha Pro v\${Version.MOD_VERSION}")
        
        // Initialize mod components here
        ScathaProConfig.load()
        
        LOGGER.info("Scatha Pro initialized successfully!")
    }

    object Version {
        const val MOD_VERSION = "1.4.1"
        const val MINECRAFT_VERSION = "1.21.1"
    }
}
