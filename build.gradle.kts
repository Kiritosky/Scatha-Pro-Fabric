plugins {
    id("fabric-loom") version "1.7.11"
    id("maven-publish")
    kotlin("jvm") version "1.9.23"
}

group = property("maven_group") as String
version = property("mod_version") as String

repositories {
    mavenCentral()
    maven("https://maven.fabricmc.net/")
    maven("https://repo.hypixel.net/repository/Hypixel/")
}

dependencies {
    // Minecraft
    minecraft("com.mojang:minecraft:${property("minecraft_version")}")
    mappings(loom.officialMojangMappings())

    // Fabric
    modImplementation("net.fabricmc:fabric-loader:${property("loader_version")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_version")}")

    // Kotlin support
    modImplementation("net.fabricmc:fabric-language-kotlin:${property("fabric_kotlin_version")}")

    // GSON for JSON config
    implementation("com.google.code.gson:gson:2.11.0")

    // Logging
    implementation("org.apache.logging.log4j:log4j-core:2.17.0")
}

tasks {
    processResources {
        inputs.property("version", project.version)
        inputs.property("description", "A Minecraft mod for improved Scatha farming on Hypixel Skyblock")

        filesMatching("fabric.mod.json") {
            expand(inputs.properties)
        }
    }

    withType(JavaCompile::class) {
        options.encoding = "UTF-8"
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }

    jar {
        from("LICENSE")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }

    repositories {
        // Add your Maven repository here
    }
}
