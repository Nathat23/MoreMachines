package uk.antiperson.moremachines.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import uk.antiperson.moremachines.MoreMachines;

import java.io.File;

public class ConfigWrapper {

    private FileConfiguration fileConfiguration;
    private File file;
    private MoreMachines mm;
    private String path;

    public ConfigWrapper(MoreMachines mm, String path) {
        this.path = path + ".yml";
        this.file = new File(mm.getDataFolder(), this.path);
        this.mm = mm;
    }

    /**
     * Loads the file, and copies it from the jar file if it doesn't exist.
     */
    public void reload() {
        if (!file.exists()) {
            mm.saveResource(path, false);
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Gets the FileConfiguration for this file.
     *
     * @return the FileConfiguration for this file.
     */
    public FileConfiguration getConfig() {
        return fileConfiguration;
    }

    /**
     * Gets the File for this config.
     *
     * @return the file for this config.
     */
    public File getFile() {
        return file;
    }

    /**
     * Gets the file path for this file.
     *
     * @return the FileConfiguration for this file.
     */
    public String getPath() {
        return path;
    }
}
