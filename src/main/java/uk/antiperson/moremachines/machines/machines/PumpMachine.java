package uk.antiperson.moremachines.machines.machines;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;
import uk.antiperson.moremachines.MoreMachines;
import uk.antiperson.moremachines.machines.BlockMachine;
import uk.antiperson.moremachines.machines.MachineState;
import uk.antiperson.moremachines.machines.MachineType;
import uk.antiperson.moremachines.machines.unit.Unit;
import uk.antiperson.moremachines.utils.BasicLocation;

import java.util.List;

public class PumpMachine extends BlockMachine {

    public PumpMachine(MoreMachines mm, BasicLocation location) {
        super(mm, location, MachineType.PUMP);
    }

    @Override
    public void tick() {
        Block block = getNextLiquid();
        if (block == null) {
            setState(MachineState.FINISHED);
            setRunning(false);
            return;
        }
        BasicLocation location = new BasicLocation(block.getLocation());
        Unit unit = getUnitManager().getUnit(0);
        unit.move(location);
        block.setType(Material.AIR);
    }

    /**
     * Gets the next block that is a liquid.
     *
     * @return the next block that is a liquid.
     */
    public Block getNextLiquid() {
        List<Block> blocks = generateBlocks(Block::isLiquid);
        for (Block block : blocks) {
            Levelled levelled = (Levelled) block.getBlockData();
            if (levelled.getLevel() != 0) {
                continue;
            }
            return block;
        }
        return null;
    }

}
