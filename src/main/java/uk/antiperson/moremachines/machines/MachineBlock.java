package uk.antiperson.moremachines.machines;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public abstract class MachineBlock {

    private Machine machine;
    private BlockFace face;
    public MachineBlock(Machine machine, BlockFace face) {
        this.machine = machine;
        this.face = face;
    }

    /**
     * Gets the block.
     * @return the block.
     */
    public Block getBlock() {
        return machine.getMachineBlock().getRelative(face);
    }

    /**
     * Gets the machine for which this block is apart of.
     * @return the machine for which this block is apart of.
     */
    public Machine getMachine() {
        return machine;
    }
}
