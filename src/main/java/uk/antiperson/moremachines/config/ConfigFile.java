package uk.antiperson.moremachines.config;

import org.bukkit.configuration.file.FileConfiguration;
import uk.antiperson.moremachines.MoreMachines;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ConfigFile {

    private ConfigWrapper globalFile;
    private ConfigWrapper specificFile;
    private MoreMachines mm;
    private boolean hasGlobal;

    public ConfigFile(MoreMachines mm, String folder, String fileName) {
        this.mm = mm;
        specificFile = new ConfigWrapper(mm, folder + File.separator + fileName);
        globalFile = new ConfigWrapper(mm, folder + File.separator + "global");
    }

    /**
     * Gets a boolean from the applicable config.
     *
     * @param path config path
     * @return boolean value of the specified config path.
     */
    public boolean getBoolean(String path) {
        return getApplicableConfig(path).getBoolean(path);
    }

    /**
     * Gets a string from the applicable config.
     *
     * @param path config path.
     * @return string value of the specified config path.
     */
    public String getString(String path) {
        return getApplicableConfig(path).getString(path);
    }

    /**
     * Gets an int from the applicable config.
     *
     * @param path config path
     * @return int value of the specified config path.
     */
    public int getInt(String path) {
        return getApplicableConfig(path).getInt(path);
    }

    /**
     * Gets a string list from the applicable config.
     *
     * @param path config path.
     * @return string list value of the specified config path.
     */
    public List<String> getStringList(String path) {
        return getApplicableConfig(path).getStringList(path);
    }

    /**
     * Gets the applicable FileConfiguration for the specified path.
     *
     * @param path config path.
     * @return the applicable FileConfiguration.
     */
    private FileConfiguration getApplicableConfig(String path) {
        return getSpecificConfig().contains(path) || !hasGlobal ? getSpecificConfig() : getGlobalConfig();
    }

    /**
     * Returns the specific FileConfiguration only.
     *
     * @return the specific FileConfiguration.
     */
    public FileConfiguration getConfig() {
        return hasGlobal ? null : getSpecificConfig();
    }

    /**
     * Returns the global files FileConfiguration.
     *
     * @return the global files FileConfiguration.
     */
    private FileConfiguration getGlobalConfig() {
        return globalFile.getConfig();
    }

    /**
     * Returns the specific files FileConfiguration.
     *
     * @return the specific files FileConfiguration.
     */
    private FileConfiguration getSpecificConfig() {
        return specificFile.getConfig();
    }

    /**
     * Returns the specific files ConfigWrapper.
     *
     * @return the specific files ConfigWrapper.
     */
    private ConfigWrapper getSpecificFile() {
        return specificFile;
    }

    /**
     * Returns the global files ConfigWrapper.
     *
     * @return the global files ConfigWrapper.
     */
    private ConfigWrapper getGlobalFile() {
        return globalFile;
    }

    /**
     * Determine whether there is a global config, and if so load it.
     */
    public void reload() {
        getSpecificFile().reload();
        try {
            String path = mm.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
            JarFile file = new JarFile(path);
            JarEntry entry = file.getJarEntry(getGlobalFile().getPath());
            if (entry != null) {
                hasGlobal = true;
                getGlobalFile().reload();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
