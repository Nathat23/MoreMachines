package uk.antiperson.moremachines.item;

import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
import org.bukkit.inventory.meta.tags.ItemTagType;
import uk.antiperson.moremachines.MoreMachines;
import uk.antiperson.moremachines.config.machines.MachineConfig;
import uk.antiperson.moremachines.machines.Machine;
import uk.antiperson.moremachines.machines.MachineType;
import uk.antiperson.moremachines.utils.Utilities;

public class ItemHandler {

    private MoreMachines mm;
    private NamespacedKey typeKey;
    private NamespacedKey levelKey;
    private NamespacedKey rangeKey;

    public ItemHandler(MoreMachines mm) {
        this.mm = mm;
        this.typeKey = new NamespacedKey(mm, "type");
        this.levelKey = new NamespacedKey(mm, "level");
        this.rangeKey = new NamespacedKey(mm, "range");
    }

    /**
     * Creates an ItemStack for a machine.
     *
     * @param machine machine to create ItemStack for.
     * @return ItemStack with machine NBT.
     */
    public ItemStack createItem(Machine machine) {
        ItemStack is = new ItemStack(machine.getConfig().getItemMaterial(), 1);
        ItemMeta itemMeta = is.getItemMeta();
        CustomItemTagContainer container = itemMeta.getCustomTagContainer();
        container.setCustomTag(typeKey, ItemTagType.STRING, machine.getType().toString());
        container.setCustomTag(levelKey, ItemTagType.INTEGER, machine.getLevel());
        container.setCustomTag(rangeKey, ItemTagType.INTEGER, machine.getRange());

        itemMeta.setDisplayName(Utilities.translateColorCodes(machine.getConfig().getItemName()));
        itemMeta.setLore(Utilities.parseStrings(machine.getConfig().getItemLore(), machine));
        is.setItemMeta(itemMeta);
        return is;
    }

    /**
     * Creates an ItemStack for a machinetype.
     *
     * @param type the type of machine to create ItemStack for.
     * @return ItemStack with machine NBT.
     */
    public ItemStack createItem(MachineType type) {
        MachineConfig config = mm.getConfigManager().getMachine(type);
        ItemStack is = new ItemStack(config.getItemMaterial(), 1);
        ItemMeta itemMeta = is.getItemMeta();
        CustomItemTagContainer container = itemMeta.getCustomTagContainer();
        container.setCustomTag(typeKey, ItemTagType.STRING, type.toString());
        container.setCustomTag(levelKey, ItemTagType.INTEGER, config.getDefaultLevel());
        container.setCustomTag(rangeKey, ItemTagType.INTEGER, config.getDefaultRange());

        itemMeta.setDisplayName(Utilities.translateColorCodes(config.getItemName()));
        itemMeta.setLore(Utilities.parseStrings(config.getItemLore(), type, config.getDefaultRange(), config.getDefaultLevel()));
        is.setItemMeta(itemMeta);
        return is;
    }

    /**
     * Creates a machine from an ItemStack.
     *
     * @param block     block to create machine.
     * @param itemStack itemstack with machine details.
     */
    public void createMachine(Block block, ItemStack itemStack) {
        CustomItemTagContainer container = itemStack.getItemMeta().getCustomTagContainer();
        String typeString = container.getCustomTag(typeKey, ItemTagType.STRING);
        MachineType machineType = MachineType.valueOf(typeString);
        int level = container.getCustomTag(levelKey, ItemTagType.INTEGER);
        int range = container.getCustomTag(rangeKey, ItemTagType.INTEGER);
        Machine machine = mm.getMachineManager().registerMachine(block, machineType);
        machine.setLevel(level);
        machine.setRange(range);
    }

    /**
     * Returns whether the provided ItemStack is a machine ItemStack.
     *
     * @param itemStack the ItemStack to check
     * @return whether the provided ItemStack is a machine ItemStack.
     */
    public boolean isMachineItem(ItemStack itemStack) {
        CustomItemTagContainer container = itemStack.getItemMeta().getCustomTagContainer();
        return container.hasCustomTag(typeKey, ItemTagType.STRING);
    }
}
