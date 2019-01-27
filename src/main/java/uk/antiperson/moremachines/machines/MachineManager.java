package uk.antiperson.moremachines.machines;

import org.bukkit.block.Block;
import uk.antiperson.moremachines.MoreMachines;
import uk.antiperson.moremachines.machines.machines.GrinderMachine;
import uk.antiperson.moremachines.machines.machines.HarvesterMachine;
import uk.antiperson.moremachines.machines.machines.MinerMachine;
import uk.antiperson.moremachines.machines.machines.PumpMachine;
import uk.antiperson.moremachines.utils.BasicLocation;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class MachineManager {

    private Set<Machine> loadedMachines;
    private MoreMachines mm;

    public MachineManager(MoreMachines mm) {
        this.mm = mm;
        this.loadedMachines = new HashSet<>();
    }

    public Set<Machine> getLoadedMachines() {
        return loadedMachines;
    }

    public Machine registerMachine(BasicLocation location, MachineType type) {
        Machine machine = null;
        switch (type) {
            case PUMP:
                machine = new PumpMachine(mm, location);
                break;
            case MINER:
                machine = new MinerMachine(mm, location);
                break;
            case HARVESTER:
                machine = new HarvesterMachine(mm, location);
                break;
            case GRINDER:
                machine = new GrinderMachine(mm, location);
                break;
        }
        loadedMachines.add(machine);
        machine.getHologram().update();
        return machine;
    }

    public Machine registerMachine(Block block, MachineType type) {
        BasicLocation location = new BasicLocation(block.getLocation());
        return registerMachine(location, type);
    }

    public void remove(Machine machine) {
        machine.destroy();
        loadedMachines.remove(machine);
    }

    public void removeAll() {
        for (Machine machine : loadedMachines) {
            machine.destroy();
        }
        loadedMachines.clear();
    }

    public boolean isMachineBlock(Block block) {
        return getMachine(block) != null;
    }

    public boolean isMachineFuel(Block block) {
        for (Machine machine : loadedMachines) {
            if (machine.getFuel().getFurnace() != null &&
                    machine.getFuel().getFurnace().getBlock().equals(block)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the machine for this block
     *
     * @param block the block.
     * @return the machine for this block.
     */
    public Machine getMachine(Block block) {
        for (Machine machine : loadedMachines) {
            if (machine.getMachineLocation().toBukkit().getBlock().equals(block)) {
                return machine;
            }
        }
        return null;
    }

    /**
     * Gets the machine for this uuid.
     *
     * @param uuid to find.
     * @return the machine for this block.
     */
    public Machine getMachine(UUID uuid) {
        for (Machine machine : loadedMachines) {
            if (machine.getUniqueId().equals(uuid)) {
                return machine;
            }
        }
        return null;
    }
}
