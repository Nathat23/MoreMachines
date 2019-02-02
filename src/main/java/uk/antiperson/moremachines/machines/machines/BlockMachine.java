package uk.antiperson.moremachines.machines.machines;

import org.bukkit.Material;
import org.bukkit.block.Block;
import uk.antiperson.moremachines.MoreMachines;
import uk.antiperson.moremachines.machines.Machine;
import uk.antiperson.moremachines.machines.MachineState;
import uk.antiperson.moremachines.machines.MachineType;
import uk.antiperson.moremachines.utils.BasicLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BlockMachine extends Machine {

    private List<Block> blocksToBreak;
    private MoreMachines mm;

    public BlockMachine(MoreMachines mm, BasicLocation sourceBlock, MachineType type) {
        super(mm, sourceBlock, type);
        this.mm = mm;
        this.blocksToBreak = new ArrayList<>();
    }

    /**
     * Gets all the valid blocks in the machines range.
     *
     * @param filter the filter to filter blocks with.
     * @return all the valid blocks in the machines range.
     */
    public List<Block> generateBlocks(Predicate<Block> filter) {
        ArrayList<Block> list = new ArrayList<>();
        for (int y = getRange(); y >= -getRange(); y--) {
            for (int x = -getRange(); x <= getRange(); x++) {
                for (int z = -getRange(); z <= getRange(); z++) {
                    Block block = getMachineBlock().getRelative(x, y, z);
                    if (block.isEmpty()) {
                        continue;
                    }
                    if (block.getType() == Material.BEDROCK) {
                        continue;
                    }
                    if (!filter.test(block)) {
                        continue;
                    }
                    if (mm.getCompatManager().notAllowed(block.getLocation())) {
                        setState(MachineState.INVALID_REGION);
                        return list;
                    }
                    if (block.getLocation().equals(getMachineLocation().toBukkit())) {
                        continue;
                    }
                    if (block.getLocation().equals(getFuel().getBlock().getLocation())) {
                        continue;
                    }
                    list.add(block);
                }
            }
        }
        return list;
    }


    /**
     * Gets the next block that is not filtered.
     *
     * @param filter the filter to remove block if matches.
     * @return next block.
     */
    public Block getBlock(Predicate<Block> filter) {
        if (blocksToBreak == null || blocksToBreak.size() == 0) {
            blocksToBreak = generateBlocks(filter);
        }
        if (blocksToBreak.size() == 0) {
            return null;
        }
        Block block = blocksToBreak.get(0);
        blocksToBreak.remove(0);
        return block;
    }


}
