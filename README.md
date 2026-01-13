# Scatha Pro

*Advanced Scatha farming companion for Hypixel Skyblock*

## Overview

Scatha Pro is a Fabric mod designed to enhance your Scatha farming experience on Hypixel Skyblock. It provides real-time statistics, smart alerts, and automation features to maximize your farming efficiency.

## Features

✨ **Core Features:**
- Real-time farming statistics and tracking
- Intelligent spawn alerts with customizable thresholds
- Advanced overlay system for quick information access
- Sound and visual notifications
- Auto-farm capabilities with safety checks
- Achievement system
- Comprehensive logging and debugging

## Installation

### Requirements
- Minecraft 1.21.1
- Fabric Loader 0.16.5 or later
- Fabric API
- Fabric Language Kotlin
- Java 21+

### Setup

1. **Install Fabric** using the [Fabric Installer](https://fabricmc.net/use/installer/)
2. **Download the mod** from [Releases](./releases)
3. **Place the JAR** in your `~/.minecraft/mods/` folder
4. **Launch** the game with the Fabric profile

## Configuration

Configuration files are stored in `.minecraft/config/scatha-pro/`

```json
{
  "enabled": true,
  "sound_alerts": true,
  "overlay_enabled": true,
  "auto_farm": false
}
```

## Building from Source

```bash
# Clone the repository
git clone https://github.com/Kiritosky/Scatha-Pro-Fabric
cd Scatha-Pro-Fabric

# Build the project
./gradlew build

# The built JAR will be in: build/libs/scatha-pro-*.jar
```

## Development

### Setting Up Your Environment

```bash
# Generate IDE run configurations
./gradlew idea  # For IntelliJ IDEA

# Start the client
./gradlew runClient

# Start the server
./gradlew runServer
```

### Project Structure

```
src/main/
├── java/me/kiritosky/scathapro/
│   ├── ScathaProMod.kt          # Main entry point
│   ├── ScathaProConfig.kt        # Configuration management
│   └── [other modules]/          # Feature modules
└── resources/
    ├── fabric.mod.json           # Mod metadata
    ├── scatha-pro.mixins.json   # Mixin configuration
    └── assets/                   # Textures, sounds, etc.
```

## Troubleshooting

### Common Issues

**Q: Mod won't load**
- Ensure you're on Minecraft 1.21.1
- Check that Fabric API is installed
- Verify Java 21+ is installed

**Q: Gradle build fails**
- Run `./gradlew clean` first
- Delete `.gradle/` folder
- Ensure JAVA_HOME points to Java 21+

**Q: IDE not recognizing mod sources**
- Run `./gradlew idea` (IntelliJ) or `./gradlew eclipse` (Eclipse)
- Refresh IDE project

## Contributing

Contributions are welcome! Please:
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support

- **Issues**: [GitHub Issues](https://github.com/Kiritosky/Scatha-Pro-Fabric/issues)
- **Discussions**: [GitHub Discussions](https://github.com/Kiritosky/Scatha-Pro-Fabric/discussions)

## Credits

- Built with [Fabric](https://fabricmc.net/)
- Developed for [Hypixel Skyblock](https://hypixel.net/)

---

**Made with ❤️ for the Hypixel Skyblock community**
