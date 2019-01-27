package uk.antiperson.moremachines.gui.page;

public enum InventoryPage {
    MAIN(9, "Main"),
    MODIFY(9, "Modify");

    private final String invName;
    private final int slots;

    InventoryPage(int slots, String invName) {
        this.slots = slots;
        this.invName = invName;
    }

    /**
     * Gets the number of inventory slots.
     *
     * @return the number of inventory slots.
     */
    public int getSlots() {
        return slots;
    }

    /**
     * Gets the name of the inventory.
     *
     * @return the name of the inventory.
     */
    public String getInvName() {
        return invName;
    }
}
