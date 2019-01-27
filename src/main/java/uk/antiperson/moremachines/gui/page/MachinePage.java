package uk.antiperson.moremachines.gui.page;

import uk.antiperson.moremachines.machines.Machine;

public abstract class MachinePage extends Page {

    private Machine machine;

    public MachinePage(InventoryPage page, Machine machine) {
        super(page);
        this.machine = machine;
    }

    public Machine getMachine() {
        return machine;
    }
}
