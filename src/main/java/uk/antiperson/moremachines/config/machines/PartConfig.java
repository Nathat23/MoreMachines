package uk.antiperson.moremachines.config.machines;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import uk.antiperson.moremachines.utils.Utilities;

public class PartConfig {

    private UnitConfig unitConfig;
    private int partId;

    public PartConfig(UnitConfig unitConfig, int partId) {
        this.partId = partId;
        this.unitConfig = unitConfig;
    }

    /**
     * Gets the ConfigurationSection for this part.
     *
     * @return the ConfigurationSection for this part.
     */
    private ConfigurationSection getSection() {
        return unitConfig.getSection().getConfigurationSection(Integer.toString(partId));
    }

    /**
     * Gets the ItemStack for the hand item.
     *
     * @return the ItemStack for the hand item.
     */
    public ItemStack getHandItem() {
        return getItem("hand-item");
    }

    /**
     * Gets the ItemStack for the helmet item.
     *
     * @return the ItemStack for the helmet item.
     */
    public ItemStack getHeadItem() {
        return getItem("head-item");
    }

    /**
     * Gets the ItemStack for the chestplate item.
     *
     * @return the ItemStack for the chestplate item.
     */
    public ItemStack getChestItem() {
        return getItem("chest-item");
    }

    /**
     * Gets the ItemStack for the leg item.
     *
     * @return the ItemStack for the leg item.
     */
    public ItemStack getLegItem() {
        return getItem("leg-item");
    }

    /**
     * Gets the ItemStack for the foot item.
     *
     * @return the ItemStack for the foot item.
     */
    public ItemStack getFootItem() {
        return getItem("foot-item");
    }

    /**
     * Gets the machine block location X offset.
     *
     * @return the machine block location X offset.
     */
    public double getXOffset() {
        return getOffset("x");
    }

    /**
     * Gets the machine block location Y offset.
     *
     * @return the machine block location Y offset.
     */
    public double getYOffset() {
        return getOffset("y");
    }

    /**
     * Gets the machine block location Z offset.
     *
     * @return the machine block location Z offset.
     */
    public double getZOffset() {
        return getOffset("z");
    }

    /**
     * Gets whether the armorstand should be big or not.
     *
     * @return whether the armorstand should be big or not..
     */
    public boolean isBig() {
        return getSection().getBoolean("big");
    }

    /**
     * Gets the machine block location offset for the specified key.
     *
     * @return the machine block location offset for the specified key.
     */
    private double getOffset(String key) {
        return getSection().getDouble("offset." + key);
    }

    /**
     * Gets the ItemStack for specified key.
     *
     * @return the ItemStack for specified key.
     */
    private ItemStack getItem(String key) {
        if (!getSection().isString(key)) {
            return null;
        }
        String value = getSection().getString(key);
        String itemName = value.contains(":") ? value.split(":")[0] : value;
        String skullId = itemName.equals("PLAYER_HEAD") ? value.split(":")[1] : null;
        ItemStack stack = new ItemStack(Material.matchMaterial(itemName), 1);
        if (skullId != null) {
            stack.setItemMeta(Utilities.getSkullMeta(stack, skullId));
        }
        return stack;
    }
}
