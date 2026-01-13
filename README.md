# Scatha-Pro (Fabric 1.21.10)

A feature-rich Minecraft mod for enhanced Scatha farming on Hypixel Skyblock. This is a complete rewrite of the original Forge 1.8.9 mod for Fabric 1.21.10.

## Features

### Alerts & Notifications
- **Scatha Spawn Alerts** - Get notified instantly when Scatha spawns
- **Kill Alerts** - Receive alerts when you defeat Scatha
- **Pet Drop Alerts** - Get notified on rare, epic, and legendary pet drops
- **Customizable Alert Modes** - Choose between sound, title, or chat notifications
- **Volume Control** - Adjust alert volume to your preference

### Statistics Tracking
- **Session Stats** - Track kills and drops for the current session
- **Total Stats** - Monitor lifetime statistics
- **Pet Drop Tracking** - Separate tracking for rare, epic, and legendary drops
- **Worm Kill Tracking** - Keep track of regular worm kills
- **Persistent Data** - Stats are saved automatically

### Display & Overlays
- **Customizable Overlay** - Drag-and-drop overlay showing current stats
- **Multiple Display Formats** - Choose what information to display
- **Adjustable Scale** - Resize the overlay to your preference
- **Position Memory** - Remember your overlay position between sessions

### Configuration
- **In-Game Settings** - Access settings with keybinds
- **Config Files** - Edit `config/scathapro/config.json` for advanced options
- **Persistent Configuration** - All settings saved automatically

### Achievements
- **First Kill** - Get your first Scatha kill
- **Milestone Kills** - Achievements at 10, 100, and 1000 kills
- **Pet Drops** - Unlock achievements for pet drops
- **Farming Streaks** - Achieve multi-day farming streaks

## Installation

1. Download the latest release from [Modrinth](https://modrinth.com/mod/scatha-pro)
2. Ensure you have Fabric Loader installed
3. Place the `.jar` file in your `mods` folder
4. Launch Minecraft with the Fabric profile
5. Join a Hypixel Skyblock server in Crystal Hollows

## Keybinds

- **O** - Toggle Overlay
- **P** - Toggle Statistics Display
- **R** - Reset Statistics (requires confirmation)

Keybinds can be customized in Minecraft's Controls menu under "Scatha-Pro"

## Configuration

Configuration files are located in `%appdata%/.minecraft/config/scathapro/` (Windows) or `~/.minecraft/config/scathapro/` (Linux/Mac).

### config.json Structure

```json
{
  "alerts": {
    "enableSpawningAlert": true,
    "enableKillAlert": true,
    "alertMode": "sound",
    "alertVolume": 1.0
  },
  "display": {
    "enableOverlay": true,
    "enableStats": true,
    "overlayX": 10,
    "overlayY": 10,
    "overlayScale": 1.0
  },
  "tracking": {
    "trackKills": true,
    "trackDrops": true,
    "trackStats": true
  },
  "advanced": {
    "debugMode": false,
    "checkForUpdates": true
  }
}
```

## File Structure

- `config/scathapro/config.json` - Main configuration file
- `config/scathapro/stats.json` - Tracking data and statistics
- `config/scathapro/achievements.json` - Achievement progress

## Permissions

⚠️ **Important**: This mod is based on the original Scatha-Pro by NamelessJu

- ✅ You may use this mod for private use
- ✅ You may bundle this mod in modpacks with proper credit
- ✅ You may modify this mod for personal use
- ❌ You may NOT publish this mod or derivatives without permission
- ❌ You may NOT claim this as your own work

## Support & Issues

If you encounter issues:

1. Check the [GitHub Issues](https://github.com/Kiritosky/Scatha-Pro-Fabric/issues)
2. Ensure you have the latest version
3. Verify you're on Minecraft 1.21.10 with Fabric
4. Check the logs for error messages

## Changelog

### v1.4.0
- Complete rewrite for Fabric 1.21.10
- Modern UI and configuration system
- Improved statistics tracking
- New alert system with sound support
- Achievement system
- Enhanced overlay rendering

## Credits

- **Original Creator**: NamelessJu
- **Fabric Port**: Kirito
- **Testing & Feedback**: Hypixel Community

## License

Custom License - See LICENSE file for details

---

**Happy Scatha Farming!** 🐛⚔️
