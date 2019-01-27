package uk.antiperson.moremachines.machines.unit;

import uk.antiperson.moremachines.config.machines.PartConfig;
import uk.antiperson.moremachines.config.machines.UnitConfig;
import uk.antiperson.moremachines.machines.Machine;
import uk.antiperson.moremachines.machines.unit.part.UnitPart;
import uk.antiperson.moremachines.utils.BasicLocation;

import java.util.ArrayList;
import java.util.List;

public class Unit {

    private List<UnitPart> parts;
    private Machine owner;
    private UnitConfig unit;

    public Unit(Machine owner, UnitConfig unit) {
        this.owner = owner;
        this.unit = unit;
        this.parts = new ArrayList<>();
    }

    /**
     * Gets all the parts of this unit.
     *
     * @return a list of all parts of this unit.
     */
    public List<UnitPart> getParts() {
        if (parts.isEmpty()) {
            loadParts();
        }
        return parts;
    }

    /**
     * Loads parts of this unit from config.
     */
    public void loadParts() {
        for (PartConfig part : unit.getParts()) {
            UnitPart unitPart = new UnitPart(owner, part);
            parts.add(unitPart);
        }
    }

    /**
     * Spawn this unit at this location.
     *
     * @param where the location where to spawn the unit.
     */
    public void spawn(BasicLocation where) {
        parts.forEach(part -> part.spawn(where));
    }

    /**
     * Move the unit to this location.
     *
     * @param where the location where to move the unit to.
     */
    public void move(BasicLocation where) {
        if (getParts().isEmpty()) {
            spawn(where);
        }
        parts.forEach(part -> part.move(where));
    }

    /**
     * Remove this unit and its parts.
     */
    public void remove() {
        parts.forEach(UnitPart::remove);
        parts.clear();
    }
}
