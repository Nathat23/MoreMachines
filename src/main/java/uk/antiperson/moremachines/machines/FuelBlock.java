package uk.antiperson.moremachines.machines;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Furnace;
import org.bukkit.inventory.ItemStack;

public class FuelBlock extends MachineBlock {

    public FuelBlock(Machine machine) {
        super(machine, BlockFace.EAST);
    }

    public Furnace getFurnace() {
        if (getBlock().getType() == Material.FURNACE) {
            return (Furnace) getBlock().getState();
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
        getMachine().setRunning(false);
        getMachine().setState(MachineState.NO_FUEL);
    }
}
