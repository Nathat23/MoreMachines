package uk.antiperson.moremachines.tasks;

import org.bukkit.scheduler.BukkitRunnable;
import uk.antiperson.moremachines.MoreMachines;
import uk.antiperson.moremachines.machines.Machine;
import uk.antiperson.moremachines.machines.MachineState;

public class TickUnits extends BukkitRunnable {

    private MoreMachines mm;

    public TickUnits(MoreMachines mm) {
        this.mm = mm;
    }

    @Override
    public void run() {
        for (Machine machine : mm.getMachineManager().getLoadedMachines()) {
            if (!machine.isRunning() || machine.getState() != MachineState.WORKING) {
                continue;
            }
            machine.tickMachine();
        }
    }
}
