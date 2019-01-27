package uk.antiperson.moremachines.gui;

public interface ActionListener {

    /**
     * Code that is executed when a GUI event takes place or other machine change occurs.
     *
     * @param event InventoryEvent with event
     */
    void onAction(InventoryEvent event);
}
