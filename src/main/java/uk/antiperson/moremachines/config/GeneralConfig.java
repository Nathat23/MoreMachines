package uk.antiperson.moremachines.config;

import org.bukkit.configuration.file.FileConfiguration;

public class GeneralConfig {

    private ConfigWrapper file;
    private StorageConfig storageConfig;

    public GeneralConfig(ConfigManager cm) {
        file = cm.getSpecificConfig("config");
        file.reload();
        storageConfig = new StorageConfig(file);
    }

    public StorageConfig getStorageConfig() {
        return storageConfig;
    }

    public boolean isWorldGuardAllowed() {
        return getConfig().getBoolean("compat.worldguard");
    }

    private FileConfiguration getConfig() {
        return file.getConfig();
    }
}
