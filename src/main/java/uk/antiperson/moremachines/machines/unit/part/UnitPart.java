package uk.antiperson.moremachines.machines.unit.part;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import uk.antiperson.moremachines.config.machines.PartConfig;
import uk.antiperson.moremachines.machines.Machine;
import uk.antiperson.moremachines.utils.BasicLocation;

public class UnitPart implements AbstractPart {

    private ArmorStand armorStand;
    private Machine owner;
    private PartConfig part;

    public UnitPart(Machine owner, PartConfig part) {
        this.owner = owner;
        this.part = part;
    }

    @Override
    public void move(BasicLocation to) {
        if (armorStand == null) {
            spawn(to);
        }
        armorStand.teleport(getLocation(to));
    }

    @Override
    public void spawn(BasicLocation where) {
        armorStand = (ArmorStand) owner.getWorld().spawnEntity(getLocation(where), EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setSmall(!part.isBig());
        armorStand.setItemInHand(part.getHandItem());
        armorStand.setHelmet(part.getHeadItem());
        armorStand.setChestplate(part.getChestItem());
        armorStand.setLeggings(part.getLegItem());
        armorStand.setBoots(part.getFootItem());
    }

    private Location getLocation(BasicLocation where) {
        return where.toBukkit().add(part.getXOffset(), part.getYOffset(), part.getZOffset());
    }

    @Override
    public void remove() {
        armorStand.remove();
    }
}
