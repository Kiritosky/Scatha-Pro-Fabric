plugins {
    id("fabric-loom")
    id("maven-publish")
    kotlin("jvm")
}

group = property("maven_group") as String
version = property("mod_version") as String

repositories {
    mavenCentral()
    maven("https://maven.fabricmc.net")
    maven("https://repo.hypixel.net/repository/Hypixel/")
    maven("https://maven.teamresourceful.com/repository/maven-public")
}

dependencies {
    // Minecraft
    minecraft("com.mojang:minecraft:${property("minecraft_version")}")
    mappings(loom.officialMojangMappings())

    // Fabric API & Loader
    modImplementation("net.fabricmc:fabric-loader:${property("loader_version")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_version")}")

    // Kotlin Language Support
    modImplementation("net.fabricmc:fabric-language-kotlin:${property("fabric_kotlin_version")}")

    // JSON Processing
    implementation("com.google.code.gson:gson:2.11.0")

    // Logging (using SLF4J instead of Log4j for better compatibility)
    implementation("org.slf4j:slf4j-api:1.7.36")
}

loom {
    splitEnvironmentSourceSets()
}

tasks {
    processResources {
        inputs.property("version", project.version)
        inputs.property("name", property("mod_name"))
        inputs.property("description", property("mod_description"))
        inputs.property("author", property("mod_author"))

        filesMatching("fabric.mod.json") {
            expand(
                mapOf(
                    "version" to project.version,
                    "name" to property("mod_name"),
                    "description" to property("mod_description"),
                    "author" to property("mod_author")
                )
            )
        }
    }

    withType(JavaCompile::class) {
        options.encoding = "UTF-8"
        options.release.set(21)
    }

    withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class) {
        kotlinOptions {
            jvmTarget = "21"
            freeCompilerArgs = listOf("-Xjvm-default=all")
        }
    }

    jar {
        from("LICENSE") {
            rename { "${it}_${project.name}" }
        }
    }

    remapJar {
        dependsOn(jar)
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21

    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }

    repositories {
        // Configure your Maven repository here if needed
        // maven {
        //     url = uri("...")
        // }
    }
}
