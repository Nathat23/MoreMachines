package uk.antiperson.moremachines.machines.machines;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import uk.antiperson.moremachines.MoreMachines;
import uk.antiperson.moremachines.machines.Machine;
import uk.antiperson.moremachines.machines.MachineType;
import uk.antiperson.moremachines.utils.BasicLocation;

import java.util.Collection;

public abstract class MobMachine extends Machine {

    public MobMachine(MoreMachines mm, BasicLocation location, MachineType type) {
        super(mm, location, type);
    }

    /**
     * Gets all nearby living entities.
     *
     * @return all nearby living entities.
     */
    public Collection<Entity> getNearbyEntities() {
        return getWorld().getNearbyEntities(getMachineLocation().toBukkit(), getRange(), getRange(), getRange(), entity -> entity instanceof LivingEntity);
    }
}
