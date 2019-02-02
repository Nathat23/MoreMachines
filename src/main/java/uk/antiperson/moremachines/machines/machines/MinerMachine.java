package uk.antiperson.moremachines.machines.machines;

import org.bukkit.block.Block;
import uk.antiperson.moremachines.MoreMachines;
import uk.antiperson.moremachines.machines.MachineState;
import uk.antiperson.moremachines.machines.MachineType;
import uk.antiperson.moremachines.machines.unit.Unit;
import uk.antiperson.moremachines.utils.BasicLocation;

public class MinerMachine extends BlockMachine {

    public MinerMachine(MoreMachines mm, BasicLocation sourceLocation) {
        super(mm, sourceLocation, MachineType.MINER);
    }

    @Override
    public void tick() {
        Block block = getNextBlock();
        if (block == null) {
            setState(MachineState.FINISHED);
            setRunning(false);
            return;
        }
        BasicLocation location = new BasicLocation(block.getLocation());
        Unit unit = getUnitManager().getUnit(0);
        unit.move(location);
        block.breakNaturally();
    }

    /**
     * Gets next block that is breakable.
     *
     * @return next block that is breakable.
     */
    public Block getNextBlock() {
        return getBlock(block -> !block.isLiquid());
    }

}
