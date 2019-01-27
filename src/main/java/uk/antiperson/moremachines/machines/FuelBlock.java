package uk.antiperson.moremachines.machines;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Furnace;
import org.bukkit.inventory.ItemStack;
import uk.antiperson.moremachines.utils.BasicLocation;

public class FuelBlock {

    private Machine machine;
    private BasicLocation location;

    public FuelBlock(Machine machine) {
        this.machine = machine;
        this.location = new BasicLocation(machine.getMachineBlock().getRelative(BlockFace.EAST).getLocation());
    }

    public BasicLocation getLocation() {
        return location;
    }

    public Furnace getFurnace() {
        Block rel = location.toBukkit().getBlock();
        if (rel.getType() == Material.FURNACE) {
            return (Furnace) rel.getState();
        }
        return null;
    }

    public void doFuel() {
        if (getFurnace() != null) {
            Furnace furnace = getFurnace();
            if (furnace.getInventory().getFuel() != null) {
                if (furnace.getBurnTime() == 0) {
                    furnace.getInventory().setSmelting(new ItemStack(Material.OAK_WOOD));
                    return;
                }
            }
            if (furnace.getBurnTime() != 0) {
                return;
            }
        }
        machine.setRunning(false);
        machine.setState(MachineState.NO_FUEL);
    }
}
