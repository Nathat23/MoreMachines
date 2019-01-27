package uk.antiperson.moremachines.gui;

import org.bukkit.entity.Player;

public class InventoryEvent {

    private InventoryItem item;
    private Player player;

    public InventoryEvent(Player player, InventoryItem item) {
        this.player = player;
        this.item = item;
    }

    /**
     * Gets the item in this event.
     *
     * @return the item in this event.
     */
    public InventoryItem getItem() {
        return item;
    }

    /**
     * Gets the player who clicked.
     *
     * @return the player who clicked.
     */
    public Player getPlayer() {
        return player;
    }

}
