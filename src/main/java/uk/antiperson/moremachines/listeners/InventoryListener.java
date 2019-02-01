package uk.antiperson.moremachines.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import uk.antiperson.moremachines.MoreMachines;
import uk.antiperson.moremachines.gui.Gui;

public class InventoryListener implements Listener {

    private MoreMachines mm;

    public InventoryListener(MoreMachines mm) {
        this.mm = mm;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!mm.getGuiManager().hasGuiOpen(player)) {
            return;
        }
        event.setCancelled(true);
        Gui gui = mm.getGuiManager().getGui(player);
        gui.getCurrentPage().getInventory().onClick(player, event.getSlot());
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (!mm.getGuiManager().hasGuiOpen(player)) {
            return;
        }
        mm.getGuiManager().remove(player);
    }
}
