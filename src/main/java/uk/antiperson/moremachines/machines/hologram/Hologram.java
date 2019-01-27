package uk.antiperson.moremachines.machines.hologram;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import uk.antiperson.moremachines.machines.Machine;
import uk.antiperson.moremachines.utils.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Hologram {

    private List<UUID> entities;
    private Machine machine;

    public Hologram(Machine machine) {
        this.machine = machine;
        this.entities = new ArrayList<>();
    }

    /**
     * Update all lines of the hologram.
     */
    public void update() {
        if (!machine.getConfig().isHologramEnabled()) {
            return;
        }
        if (entities.isEmpty()) {
            spawnEntities();
        }
        for (int i = 1; i <= entities.size(); i++) {
            ArmorStand armorStand = (ArmorStand) Bukkit.getEntity(entities.get(entities.size() - i));
            String format = machine.getConfig().getHologramLines().get(i - 1);
            armorStand.setCustomName(Utilities.parseString(format, machine));
        }
    }

    /**
     * Remove all the hologram entities.
     */
    public void remove() {
        for (UUID uuid : entities) {
            ArmorStand as = (ArmorStand) Bukkit.getEntity(uuid);
            as.remove();
        }
        entities.clear();
    }

    /**
     * Spawn all the hologram entities.
     */
    private void spawnEntities() {
        Location lastAdd = new Location(machine.getWorld(), 0.5, 1, 0.5);
        int lines = machine.getConfig().getHologramLines().size();
        for (int i = 0; i < lines; i++) {
            Location loc = machine.getMachineLocation().toBukkit().add(lastAdd);
            lastAdd = lastAdd.add(0, 0.25, 0);
            ArmorStand as = (ArmorStand) machine.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
            as.setSmall(true);
            as.setGravity(false);
            as.setVisible(false);
            as.setInvulnerable(true);
            as.setMarker(true);
            as.setCustomNameVisible(true);
            entities.add(as.getUniqueId());
        }
    }

}
