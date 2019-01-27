package uk.antiperson.moremachines.gui;

import org.bukkit.entity.Player;
import uk.antiperson.moremachines.gui.page.MachinePage;
import uk.antiperson.moremachines.machines.Machine;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GuiManager {

    private Map<UUID, Gui> map;

    public GuiManager() {
        this.map = new HashMap<>();
    }

    /**
     * Opens the gui for this player.
     *
     * @param machine the machine the gui is for.
     * @param player  the player to show it to.
     */
    public void openGui(Machine machine, Player player) {
        Gui gui = new Gui();
        gui.showGui(machine, player);
        map.put(player.getUniqueId(), gui);
    }

    /**
     * Gets the gui for this player.
     *
     * @param player the player to get the gui of.
     * @return the gui for this player.
     */
    public Gui getGui(Player player) {
        return map.get(player.getUniqueId());
    }

    /**
     * Updates all the open GUIs for this machine.
     *
     * @param machine machine to update GUIs of.
     */
    public void update(Machine machine) {
        for (Gui gui : map.values()) {
            if (gui.getCurrentPage() instanceof MachinePage) {
                MachinePage machinePage = (MachinePage) gui.getCurrentPage();
                if (machinePage.getMachine().getUniqueId().equals(machine.getUniqueId())) {
                    machinePage.getInventory().onUpdate();
                }
            }
        }
    }

    /**
     * Removes the players associated gui.
     *
     * @param player the player.
     */
    public void remove(Player player) {
        map.remove(player.getUniqueId());
    }

    /**
     * Returns whether the player has a gui open.
     *
     * @param player the player to check.
     * @return whether the player has a gui open.
     */
    public boolean hasGuiOpen(Player player) {
        return getGui(player) != null;
    }

}
