package uk.antiperson.moremachines.gui;

import org.bukkit.entity.Player;
import uk.antiperson.moremachines.gui.page.InventoryPage;
import uk.antiperson.moremachines.gui.page.pages.MainPage;
import uk.antiperson.moremachines.gui.page.Page;
import uk.antiperson.moremachines.machines.Machine;

public class Gui {

    private Page currentPage;

    /**
     * Changes the page.
     *
     * @param machine the machine to show info of.
     * @param page    the page to show.
     */
    public void changePage(Machine machine, InventoryPage page) {
        switch (page) {
            case MAIN:
                currentPage = new MainPage(machine);
                break;
            case MODIFY:
                throw new UnsupportedOperationException("not implemented!");
        }
    }

    /**
     * Shows the gui to this player.
     *
     * @param machine the machine to show info of.
     * @param player  the player to show gui to.
     */
    public void showGui(Machine machine, Player player) {
        if (currentPage == null) {
            changePage(machine, InventoryPage.MAIN);
        }
        currentPage.createInventory();
        currentPage.getInventory().display(player);
    }

    /**
     * Gets the page currently being viewed.
     *
     * @return the page currently being viewed.
     */
    public Page getCurrentPage() {
        return currentPage;
    }
}
