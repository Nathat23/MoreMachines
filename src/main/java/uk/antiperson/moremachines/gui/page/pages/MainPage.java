package uk.antiperson.moremachines.gui.page.pages;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import uk.antiperson.moremachines.gui.*;
import uk.antiperson.moremachines.gui.page.InventoryPage;
import uk.antiperson.moremachines.gui.page.MachinePage;
import uk.antiperson.moremachines.machines.Machine;
import uk.antiperson.moremachines.machines.MachineState;

import java.util.Collections;

public class MainPage extends MachinePage {

    public MainPage(Machine machine) {
        super(InventoryPage.MAIN, machine);
    }

    @Override
    public void fillInventory() {
        InventoryItem item = createItem();
        item.setClickListener(new ActionListener() {
            @Override
            public void onAction(InventoryEvent event) {
                getMachine().setRunning(!getMachine().isRunning());
                if (!getMachine().isRunning()) {
                    getMachine().setState(MachineState.PAUSED);
                }
                event.getItem().setItemStack(createItem().getItemStack());
            }
        });
        item.setUpdateListener(new ActionListener() {
            @Override
            public void onAction(InventoryEvent event) {
                event.getItem().setItemStack(createItem().getItemStack());
            }
        });
        getInventory().setItem(8, item);
        InventoryItem item1 = new InventoryItem(Material.GOLD_INGOT);
        item1.setDisplayName(ChatColor.GREEN + "Upgrade level");
        item1.setClickListener(new ActionListener() {
            @Override
            public void onAction(InventoryEvent event) {
                getMachine().setLevel(getMachine().getLevel() + 1);
            }
        });
        getInventory().setItem(3, item1);
        InventoryItem item2 = new InventoryItem(Material.IRON_INGOT);
        item2.setDisplayName(ChatColor.GREEN + "Upgrade range");
        item2.setClickListener(new ActionListener() {
            @Override
            public void onAction(InventoryEvent event) {
                getMachine().setRange(getMachine().getRange() + 1);
            }
        });
        getInventory().setItem(5, item2);
    }

    public InventoryItem createItem() {
        InventoryItem inventoryItem = getMachine().isRunning() ? new InventoryItem(Material.GREEN_WOOL) : new InventoryItem(Material.RED_WOOL);
        inventoryItem.setDisplayName(getMachine().isRunning() ? ChatColor.GREEN + "Running" : ChatColor.RED + "Stopped");
        inventoryItem.setLore(Collections.singletonList("Status: " + getMachine().getState()));
        return inventoryItem;
    }
}
