package uk.antiperson.moremachines.gui.page;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import uk.antiperson.moremachines.gui.*;

public abstract class Page implements AbstractPage {

    private PageInventory inventory;

    public Page(InventoryPage page) {
        this.inventory = new PageInventory(page);
    }

    public void createInventory() {
        InventoryItem item = new InventoryItem(Material.DARK_OAK_DOOR);
        item.setDisplayName(ChatColor.YELLOW + "Exit");
        item.setClickListener(event -> event.getPlayer().closeInventory());
        inventory.setItem(0, item);
        fillInventory();
    }

    /**
     * Gets the inventory for this page.
     *
     * @return the inventory for this page.
     */
    public PageInventory getInventory() {
        return inventory;
    }
}
