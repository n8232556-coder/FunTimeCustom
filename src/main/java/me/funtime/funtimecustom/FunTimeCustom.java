package me.funtime.funtimecustom;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.economy.Economy;

public final class FunTimeCustom extends JavaPlugin {

    private static FunTimeCustom instance;
    private Economy economy;
    private ConfigManager configManager;
    private MysticChestManager mysticChestManager;
    private BeaconManager beaconManager;
    private HologramManager hologramManager;
    private ParticleManager particleManager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        saveResource("chests.yml", false);
        saveResource("beacon.yml", false);

        configManager = new ConfigManager(this);
        particleManager = new ParticleManager(this);
        hologramManager = new HologramManager(this);

        if (!setupEconomy()) {
            getLogger().warning("Vault не найден! Функции монет будут отключены.");
        }

        mysticChestManager = new MysticChestManager(this);
        beaconManager = new BeaconManager(this);

        getCommand("funtime").setExecutor(new FunTimeCommand(this));

        mysticChestManager.startRandomSpawnTask();
        beaconManager.startRandomSpawnTask();

        getLogger().info("FunTimeCustom включён!");
    }

    @Override
    public void onDisable() {
        if (hologramManager != null) hologramManager.removeAll();
        if (mysticChestManager != null) mysticChestManager.removeAllChests();
        if (beaconManager != null) beaconManager.removeActiveBeacon();
        getLogger().info("FunTimeCustom выключен.");
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    public static FunTimeCustom getInstance() { return instance; }
    public Economy getEconomy() { return economy; }
    public ConfigManager getConfigManager() { return configManager; }
    public MysticChestManager getMysticChestManager() { return mysticChestManager; }
    public BeaconManager getBeaconManager() { return beaconManager; }
    public HologramManager getHologramManager() { return hologramManager; }
    public ParticleManager getParticleManager() { return particleManager; }
}