package uk.antiperson.moremachines.gui.page;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import uk.antiperson.moremachines.gui.InventoryEvent;
import uk.antiperson.moremachines.gui.InventoryItem;

import java.util.HashMap;
import java.util.Map;

public class PageInventory {

    private Inventory inventory;
    private Map<Integer, InventoryItem> map;

    public PageInventory(InventoryPage page) {
        this.map = new HashMap<>();
        this.inventory = Bukkit.createInventory(null, page.getSlots(), page.getInvName());
    }

    /**
     * Sets the specified slot with this item.
     *
     * @param slotId the slot id to put this item.
     * @param item   the item.
     */
    public void setItem(int slotId, InventoryItem item) {
        map.put(slotId, item);
        inventory.setItem(slotId, item.getItemStack());
    }

    /**
     * Method called when a player clicks on an item in the machine's GUI.
     *
     * @param who         the player who clicked.
     * @param slotClicked the slot id of the slot that was clicked.
     */
    public void onClick(Player who, int slotClicked) {
        if (map.containsKey(slotClicked)) {
            InventoryItem item = map.get(slotClicked);
            InventoryEvent event = new InventoryEvent(who, item);
            item.getClickListener().onAction(event);
            setItem(slotClicked, event.getItem());
        }
    }

    /**
     * Method called when an update to a machine occurs.
     */
    public void onUpdate() {
        for (Map.Entry<Integer, InventoryItem> entry : map.entrySet()) {
            InventoryItem item = entry.getValue();
            if (item.getUpdateListener() != null) {
                InventoryEvent event = new InventoryEvent(null, item);
                item.getUpdateListener().onAction(event);
                setItem(entry.getKey(), event.getItem());
            }
        }
    }

    /**
     * Display this inventory to this player.
     *
     * @param player the player to display this inventory to.
     */
    public void display(Player player) {
        player.openInventory(inventory);
    }
}
