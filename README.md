# VanillaPatPat

A PaperMC/Purpur server plugin that replicates the mob-petting mechanics of the popular [PatPat](https://github.com/LopyMine/PatPat) mod — without requiring any client-side mods for players.

## How It Works

1. **Sneak** (Shift) in front of any living mob.
2. **Right-click** with an **empty hand**.
3. Your character will pat the mob, spawning heart particles and playing a purr sound!

## Features

- ✅ 100% server-side — no client-side mods required
- ✅ Compatible with any living mob (animals, monsters, etc.)
- ✅ 500ms anti-spam cooldown
- ✅ Heart particles over the mob's head
- ✅ Purring sound effect (`ENTITY_CAT_PURR`)
- ✅ Action Bar notification message

## Compatibility

| Minecraft Version | Status |
|---|---|
| 1.21.11 | ✅ Primary target (Paper API 1.21.11) |
| 1.20.6 | ✅ 1.0.0 (Legacy API); use **1.0.1** for 1.21+ |

**Supported Server Software:** Paper, Purpur, Spigot (and forks).

## Installation

1. Download the `.jar` from the [Releases](../../releases) section.
2. Place it in your server's `plugins/` directory.
3. Restart the server.
4. Done! No additional configuration required.

## Credits

Inspired by the original **PatPat** mod by [LopyMine](https://github.com/LopyMine/PatPat). This plugin is an independent server-side re-implementation built using the Bukkit/Paper API.

## License

MIT
