package me.funtime.funtimecustom;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class ParticleManager {

    private final FunTimeCustom plugin;

    public ParticleManager(FunTimeCustom plugin) {
        this.plugin = plugin;
    }

    public void spawnMysticChestParticles(Location location) {
        if (!plugin.getConfigManager().useParticles()) return;
        // Фиолетовые частицы вокруг сундука
        location.getWorld().spawnParticle(Particle.SPELL_MOB, location.clone().add(0.5, 1, 0.5), 30, 0.5, 0.5, 0.5, 0, new Particle.DustOptions(org.bukkit.Color.fromRGB(128, 0, 128), 1));
    }

    public void spawnBeaconParticles(Location location) {
        if (!plugin.getConfigManager().useParticles()) return;
        // Частицы для маяка (можно свои)
        location.getWorld().spawnParticle(Particle.FLAME, location.clone().add(0, 1, 0), 10, 0.2, 0.2, 0.2, 0.1);
    }

    public void playChestOpenEffect(Player player, Location location) {
        if (!plugin.getConfigManager().useParticles()) return;
        player.spawnParticle(Particle.VILLAGER_HAPPY, location.clone().add(0.5, 1, 0.5), 10, 0.2, 0.2, 0.2, 0.1);
    }
}