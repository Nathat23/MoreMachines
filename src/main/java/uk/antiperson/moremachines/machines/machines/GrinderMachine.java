package uk.antiperson.moremachines.machines.machines;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import uk.antiperson.moremachines.MoreMachines;
import uk.antiperson.moremachines.machines.MachineType;
import uk.antiperson.moremachines.utils.BasicLocation;

public class GrinderMachine extends MobMachine {

    public GrinderMachine(MoreMachines mm, BasicLocation location) {
        super(mm, location, MachineType.GRINDER);
    }

    @Override
    public void tick() {
        for (Entity entity : getNearbyEntities()) {
            ((LivingEntity) entity).damage(1);
        }
    }
}
