package uk.antiperson.moremachines.listeners;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import uk.antiperson.moremachines.MoreMachines;
import uk.antiperson.moremachines.machines.Machine;
import uk.antiperson.moremachines.machines.MachineType;

public class MachineListener implements Listener {

    private MoreMachines mm;

    public MachineListener(MoreMachines mm) {
        this.mm = mm;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!mm.getMachineManager().isMachineBlock(event.getBlock())) {
            return;
        }
        Machine machine = mm.getMachineManager().getMachine(event.getBlock());
        mm.getMachineManager().remove(machine);
        ItemStack is = mm.getItemHandler().createItem(machine);
        event.setDropItems(false);
        machine.getWorld().dropItemNaturally(event.getBlock().getLocation(), is);
    }

    @EventHandler
    public void onBlockInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }
        if (!mm.getMachineManager().isMachineBlock(event.getClickedBlock())) {
            return;
        }
        if(event.getPlayer().isSneaking()){
            return;
        }
        event.setUseInteractedBlock(Event.Result.DENY);
        Machine machine = mm.getMachineManager().getMachine(event.getClickedBlock());
        mm.getGuiManager().openGui(machine, event.getPlayer());
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (mm.getItemHandler().isMachineItem(event.getItemInHand())) {
            mm.getItemHandler().createMachine(event.getBlock(), event.getItemInHand());
        } else if (event.getBlock().getType() == Material.COBBLESTONE) {
            mm.getMachineManager().registerMachine(event.getBlock(), MachineType.MINER);
        }
    }
}
