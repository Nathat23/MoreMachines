package uk.antiperson.moremachines.config.machines;

import org.bukkit.configuration.ConfigurationSection;
import uk.antiperson.moremachines.config.ConfigFile;

import java.util.ArrayList;
import java.util.List;

public class UnitConfig {

    private List<PartConfig> parts;
    private ConfigFile unitFile;
    private int unitId;

    public UnitConfig(ConfigFile unitFile, int unitId) {
        this.unitFile = unitFile;
        this.unitId = unitId;
        this.parts = new ArrayList<>();
    }

    /**
     * Gets a list of all the parts for this unit.
     *
     * @return a list of all the parts for this unit.
     */
    public List<PartConfig> getParts() {
        if (parts.isEmpty()) {
            for (String s : getSection().getKeys(false)) {
                PartConfig part = new PartConfig(this, Integer.valueOf(s));
                parts.add(part);
            }
        }
        return parts;
    }

    /**
     * Gets the ConfigurationSection for this unit in the parts file.
     *
     * @return the ConfigurationSection for this unit in the parts file.
     */
    public ConfigurationSection getSection() {
        return unitFile.getConfig().getConfigurationSection(Integer.toString(unitId));
    }

}
