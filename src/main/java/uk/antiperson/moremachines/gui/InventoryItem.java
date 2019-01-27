package uk.antiperson.moremachines.gui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class InventoryItem {

    private ItemStack stack;
    private ActionListener clickListener;
    private ActionListener updateListener;

    public InventoryItem(Material material) {
        stack = new ItemStack(material, 1);
    }

    /**
     * Gets the ItemStack for this item.
     *
     * @return the ItemStack for this item.
     */
    public ItemStack getItemStack() {
        return stack;
    }

    /**
     * Sets the ItemStack for this item.
     *
     * @param stack ItemStack for this item.
     */
    public void setItemStack(ItemStack stack) {
        this.stack = stack;
    }

    /**
     * Updates the display name of the ItemStack.
     *
     * @param name new display name of the ItemStack.
     */
    public void setDisplayName(String name) {
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.setDisplayName(name);
        stack.setItemMeta(itemMeta);
    }

    /**
     * Updates the lore of the ItemStack.
     *
     * @param lines new lore for the ItemStack.
     */
    public void setLore(List<String> lines) {
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.setLore(lines);
        stack.setItemMeta(itemMeta);
    }

    /**
     * Gets the click ActionListener for this item.
     * This ActionListener contains code that gets executed when the item in the gui is clicked on.
     *
     * @return the click ActionListener for this item.
     */
    public ActionListener getClickListener() {
        return clickListener;
    }

    /**
     * Gets the update ActionListener for this item.
     * This ActionListener contains code that gets executed when a machine state change occurs.
     *
     * @return the update ActionListener for this item.
     */
    public ActionListener getUpdateListener() {
        return updateListener;
    }

    /**
     * Sets the click ActionListener for this item.
     *
     * @param clickListener the click ActionListener for this item.
     */
    public void setClickListener(ActionListener clickListener) {
        this.clickListener = clickListener;
    }

    /**
     * Sets the update ActionListener for this item.
     *
     * @param updateListener the update ActionListener for this item.
     */
    public void setUpdateListener(ActionListener updateListener) {
        this.updateListener = updateListener;
    }
}
