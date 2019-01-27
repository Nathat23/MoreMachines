package uk.antiperson.moremachines.machines.machines;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import uk.antiperson.moremachines.MoreMachines;
import uk.antiperson.moremachines.machines.BlockMachine;
import uk.antiperson.moremachines.machines.MachineType;
import uk.antiperson.moremachines.machines.unit.Unit;
import uk.antiperson.moremachines.utils.BasicLocation;

import java.util.List;

public class HarvesterMachine extends BlockMachine {

    public HarvesterMachine(MoreMachines mm, BasicLocation location) {
        super(mm, location, MachineType.HARVESTER);
    }

    @Override
    public void tick() {
        Block block = getNextCrop();
        if (block != null) {
            BasicLocation location = new BasicLocation(block.getLocation());
            Unit unit = getUnitManager().getUnit(0);
            unit.move(location);
            block.getDrops().forEach(drop -> block.getWorld().dropItemNaturally(block.getLocation(), drop));
            Ageable ageable = (Ageable) block.getBlockData();
            ageable.setAge(0);
            block.setBlockData(ageable);
        }
    }

    private Block getNextCrop() {
        List<Block> blocks = generateBlocks(block -> block.getBlockData() instanceof Ageable);
        for (Block block : blocks) {
            Ageable ageable = (Ageable) block.getBlockData();
            if (ageable.getAge() == ageable.getMaximumAge()) {
                return block;
            }
        }
        return null;
    }

}
