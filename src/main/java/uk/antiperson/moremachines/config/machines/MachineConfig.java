package uk.antiperson.moremachines.config.machines;

import org.bukkit.Material;
import uk.antiperson.moremachines.config.ConfigManager;
import uk.antiperson.moremachines.config.ConfigFile;
import uk.antiperson.moremachines.machines.MachineType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MachineConfig {

    private ConfigFile file;
    private ConfigFile unitFile;
    private List<UnitConfig> units;

    public MachineConfig(ConfigManager cm, MachineType type) {
        this.units = new ArrayList<>();
        this.file = cm.getConfig("machines", type.toString());
        this.unitFile = cm.getConfig("machines" + File.separator + "parts", type.toString());
        file.reload();
        unitFile.reload();
    }

    /**
     * Gets the unit configs for this machine.
     *
     * @return the unit configs for this machine.
     */
    public List<UnitConfig> getUnits() {
        if (units.isEmpty()) {
            for (String s : getUnitFile().getConfig().getKeys(false)) {
                UnitConfig unit = new UnitConfig(getUnitFile(), Integer.valueOf(s));
                units.add(unit);
            }
        }
        return units;
    }

    /**
     * Gets whether the hologram has been enabled/disabled.
     *
     * @return whether the hologram has been enabled/disabled.
     */
    public boolean isHologramEnabled() {
        return getFile().getBoolean("hologram.use-hologram");
    }

    /**
     * Gets the display lines for the hologram.
     *
     * @return the display lines for the hologram.
     */
    public List<String> getHologramLines() {
        return getFile().getStringList("hologram.display");
    }

    /**
     * Gets whether the machine has been enabled/disabled.
     *
     * @return whether the machine has been enabled/disabled.
     */
    public boolean isEnabled() {
        return getFile().getBoolean("enabled");
    }

    /**
     * Gets the machine item material.
     *
     * @return the machine item material.
     */
    public Material getItemMaterial() {
        return Material.matchMaterial(getFile().getString("item.material"));
    }

    /**
     * Gets the display name of the machine item.
     *
     * @return the display name of the machine item.
     */
    public String getItemName() {
        return getFile().getString("item.display-name");
    }

    /**
     * Gets the lore of the machine item.
     *
     * @return the lore of the machine item.
     */
    public List<String> getItemLore() {
        return getFile().getStringList("item.lore");
    }

    /**
     * Gets the default level for this machine.
     *
     * @return the default level for this machine.
     */
    public int getDefaultLevel() {
        return getFile().getInt("default.level");
    }

    /**
     * Gets the default range for this machine.
     *
     * @return the default range for this machine.
     */
    public int getDefaultRange() {
        return getFile().getInt("default.range");
    }

    /**
     * Gets the maximum range of this machine.
     *
     * @return the maximum range of this machine.
     */
    public int getMaxRange() {
        return getFile().getInt("max.range");
    }

    /**
     * Gets the maximum level of this machine.
     *
     * @return the maximum level of this machine.
     */
    public int getMaxLevel() {
        return getFile().getInt("max.level");
    }

    /**
     * Gets the default range for this machine.
     *
     * @return the default range for this machine.
     */
    public int getDefaultTickInterval() {
        return getFile().getInt("default.tick-interval");
    }

    /**
     * Gets whether the recipe has been enabled/disabled.
     *
     * @return whether the recipe has been enabled/disabled.
     */
    public boolean isRecipeEnabled() {
        return getFile().getBoolean("recipe.use-recipe");
    }

    /**
     * Gets the key for the machine recipe.
     *
     * @return the key for the machine recipe.
     */
    public List<String> getRecipeKey() {
        return getFile().getStringList("recipe.key");
    }

    /**
     * Gets the shape of the machine recipe.
     *
     * @return the shape of the machine recipe.
     */
    public List<String> getRecipeShape() {
        return getFile().getStringList("recipe.shape");
    }

    /**
     * Gets the ConfigFile for this machine.
     *
     * @return the ConfigFile for this machine.
     */
    public ConfigFile getFile() {
        return file;
    }

    /**
     * Gets the UnitFile for this machine.
     *
     * @return the UnitFile for this machine.
     */
    private ConfigFile getUnitFile() {
        return unitFile;
    }
}
