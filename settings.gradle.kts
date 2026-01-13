pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net")
        gradlePluginPortal()
        mavenCentral()
    }
    plugins {
        id("fabric-loom") version "1.5.13"
        kotlin("jvm") version "1.9.23"
    }
}

rootProject.name = "scatha-pro"
