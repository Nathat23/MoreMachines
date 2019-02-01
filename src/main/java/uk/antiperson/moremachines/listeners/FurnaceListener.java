package uk.antiperson.moremachines.listeners;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import uk.antiperson.moremachines.MoreMachines;
import uk.antiperson.moremachines.machines.Machine;
import uk.antiperson.moremachines.machines.MachineState;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FurnaceListener implements Listener {

    private MoreMachines mm;
    private Map<UUID, UUID> open;
    public FurnaceListener(MoreMachines mm) {
        this.mm = mm;
        open = new HashMap<>();
    }

    @EventHandler
    public void onFurnaceSmelt(FurnaceSmeltEvent event) {
        if (mm.getMachineManager().isMachineFuel(event.getBlock())) {
            Furnace furnace = (Furnace) event.getBlock().getState();
            if (furnace.getInventory().getFuel() != null) {
                furnace.getInventory().setSmelting(new ItemStack(Material.OAK_WOOD));
            }
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFurnaceBurn(FurnaceBurnEvent event) {
        if (mm.getMachineManager().isMachineFuel(event.getBlock())) {
            event.setBurnTime(event.getBurnTime() / 2);
        }
    }

    @EventHandler
    public void onFurnaceClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {
            return;
        }
        if (event.getClickedInventory().getType() != InventoryType.FURNACE) {
            return;
        }
        if (!open.containsKey(event.getWhoClicked().getUniqueId())) {
            return;
        }
        if (event.getSlot() == 1) {
            Machine machine = mm.getMachineManager().getMachine(open.get(event.getWhoClicked().getUniqueId()));
            if (machine.getState() == MachineState.NO_FUEL) {
                machine.setRunning(true);
            }
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onFurnaceInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (event.getClickedBlock().getType() != Material.FURNACE) {
            return;
        }
        if (!mm.getMachineManager().isMachineFuel(event.getClickedBlock())) {
            return;
        }
        Machine machine = mm.getMachineManager().getMachine(event.getClickedBlock().getRelative(BlockFace.WEST));
        open.put(event.getPlayer().getUniqueId(), machine.getUniqueId());
    }

    @EventHandler
    public void onFurnaceClose(InventoryCloseEvent event) {
        if (event.getInventory().getType() != InventoryType.FURNACE) {
            return;
        }
        if (!open.containsKey(event.getPlayer().getUniqueId())) {
            return;
        }
        open.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() != Material.FURNACE) {
            return;
        }
        if (!mm.getMachineManager().isMachineFuel(event.getBlock())) {
            return;
        }
        Furnace furnace = (Furnace) event.getBlock().getState();
        furnace.getInventory().setSmelting(null);
    }
}
