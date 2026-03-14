package me.funtime.funtimecustom;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.metadata.FixedMetadataValue;
import java.util.ArrayList;
import java.util.List;

public class HologramManager {

    private final FunTimeCustom plugin;
    private final List<ArmorStand> holograms = new ArrayList<>();

    public HologramManager(FunTimeCustom plugin) {
        this.plugin = plugin;
    }

    public void createHologram(Location location, String text) {
        if (!plugin.getConfigManager().useHolograms()) return;

        ArmorStand as = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        as.setVisible(false);
        as.setGravity(false);
        as.setCanPickupItems(false);
        as.setCustomNameVisible(true);
        as.setCustomName(text);
        as.setMarker(true);
        as.setMetadata("funtime_hologram", new FixedMetadataValue(plugin, true));
        holograms.add(as);
    }

    public void removeAll() {
        for (ArmorStand as : holograms) {
            as.remove();
        }
        holograms.clear();
    }
}