package me.funtime.funtimecustom;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.util.Map;

public class ConfigManager {

    private final FunTimeCustom plugin;
    private FileConfiguration chestsConfig;
    private FileConfiguration beaconConfig;

    public ConfigManager(FunTimeCustom plugin) {
        this.plugin = plugin;
        reloadConfigs();
    }

    public void reloadConfigs() {
        plugin.reloadConfig();
        chestsConfig = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "chests.yml"));
        beaconConfig = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "beacon.yml"));
    }

    public String getWorldName() {
        return plugin.getConfig().getString("world", "world");
    }

    public long getMysticChestSpawnInterval() {
        return plugin.getConfig().getLong("mystic-chest-spawn-interval", 300) * 20L;
    }

    public double getMysticChestSpawnChance() {
        return plugin.getConfig().getDouble("mystic-chest-spawn-chance", 0.5);
    }

    public int getMysticChestLifetime() {
        return plugin.getConfig().getInt("mystic-chest-lifetime", 120);
    }

    public boolean useHolograms() {
        return plugin.getConfig().getBoolean("use-holograms", true);
    }

    public boolean useParticles() {
        return plugin.getConfig().getBoolean("use-particles", true);
    }

    public boolean useSounds() {
        return plugin.getConfig().getBoolean("use-sounds", true);
    }

    public Map<String, Object> getBeaconConfig() {
        return plugin.getConfig().getConfigurationSection("beacon").getValues(false);
    }

    public FileConfiguration getChestsConfig() {
        return chestsConfig;
    }

    public FileConfiguration getBeaconConfigSection() {
        return beaconConfig;
    }

    public Map<String, Object> getMoneyZones() {
        return plugin.getConfig().getConfigurationSection("beacon.money-rewards.zones").getValues(false);
    }
}