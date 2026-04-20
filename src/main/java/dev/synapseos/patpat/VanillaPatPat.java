package dev.synapseos.patpat;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VanillaPatPat extends JavaPlugin implements Listener {

    private final Map<UUID, Long> cooldowns = new HashMap<>();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("VanillaPatPat has been enabled. Acaricia a los mobs con cariño!");
    }

    @Override
    public void onDisable() {
        cooldowns.clear();
        getLogger().info("VanillaPatPat disabled.");
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }

        Player player = event.getPlayer();
        Entity clicked = event.getRightClicked();

        // Requires sneaking to not interfere with normal mob interactions (like Villagers)
        if (!player.isSneaking()) {
            return;
        }

        // Must have empty hand
        if (!player.getInventory().getItemInMainHand().getType().isAir()) {
            return;
        }

        if (!(clicked instanceof LivingEntity)) {
            return;
        }

        UUID playerId = player.getUniqueId();
        long now = System.currentTimeMillis();
        // 500ms cooldown for patting
        if (cooldowns.containsKey(playerId) && now - cooldowns.get(playerId) < 500) {
            return; 
        }

        cooldowns.put(playerId, now);
        event.setCancelled(true);

        // 1. Play swing animation
        player.swingMainHand();

        // 2. Play sound (Wolf pant and Cat purr are good options)
        player.playSound(clicked.getLocation(), Sound.ENTITY_CAT_PURR, 1.0f, 1.0f);

        // 3. Spawn hearts around the entity's head
        Location headLocation = clicked.getLocation().add(0, clicked.getHeight(), 0);
        clicked.getWorld().spawnParticle(
                Particle.HEART, 
                headLocation, 
                3, // count
                0.3, 0.3, 0.3, // offset
                0.0 // speed
        );

        // 4. Send action bar
        Component msg = Component.text("¡Acariciaste a ")
                .append(clicked.name())
                .append(Component.text("!"))
                .color(NamedTextColor.LIGHT_PURPLE);
        
        player.sendActionBar(msg);
    }
}
