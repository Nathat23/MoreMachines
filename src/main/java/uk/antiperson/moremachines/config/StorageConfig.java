package uk.antiperson.moremachines.config;

import org.bukkit.configuration.ConfigurationSection;
import uk.antiperson.moremachines.storage.StorageMethod;

public class StorageConfig {

    private ConfigWrapper wrapper;

    public StorageConfig(ConfigWrapper wrapper) {
        this.wrapper = wrapper;
    }

    /**
     * Gets the config section for storage.
     *
     * @return the config section for storage.
     */
    private ConfigurationSection getSection() {
        return wrapper.getConfig().getConfigurationSection("storage");
    }

    /**
     * Gets the storage method.
     *
     * @return the storage method.
     */
    public StorageMethod getStorageMethod() {
        return StorageMethod.valueOf(getSection().getString("type"));
    }

    /**
     * Gets the database port.
     *
     * @return the database port.
     */
    private int getPort() {
        return getSection().getInt("port");
    }

    /**
     * Gets the database ip address.
     *
     * @return the database ip address.
     */
    private String getIp() {
        return getSection().getString("ip");
    }

    /**
     * Gets the database name.
     *
     * @return the database name.
     */
    private String getName() {
        return getSection().getString("db-name");
    }

    /**
     * Gets the database address.
     *
     * @return the database address.
     */
    public String getAddress() {
        return getIp() + ":" + getPort() + "/" + getName();
    }

    /**
     * Gets the database username.
     *
     * @return the database username.
     */
    public String getUsername() {
        return getSection().getString("username");
    }

    /**
     * Gets the database password.
     *
     * @return the database password.
     */
    public String getPassword() {
        return getSection().getString("password");
    }
}
