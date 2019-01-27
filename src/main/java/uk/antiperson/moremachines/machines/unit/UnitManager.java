package uk.antiperson.moremachines.machines.unit;

import uk.antiperson.moremachines.config.machines.UnitConfig;
import uk.antiperson.moremachines.machines.Machine;

import java.util.ArrayList;
import java.util.List;

public class UnitManager {

    private List<Unit> units;
    private Machine owner;

    public UnitManager(Machine owner) {
        this.units = new ArrayList<>();
        this.owner = owner;
    }

    /**
     * Gets a unit with the specified id.
     *
     * @param id the id of the unit.
     * @return the unit with the specified id.
     */
    public Unit getUnit(int id) {
        return getUnits().get(id);
    }

    /**
     * Gets a list of all units for this machine
     *
     * @return a list of all units for this machine.
     */
    public List<Unit> getUnits() {
        if (units.isEmpty()) {
            loadUnits();
        }
        return units;
    }

    /**
     * Load all units from the config for this machine.
     */
    public void loadUnits() {
        for (UnitConfig unitConfig : owner.getConfig().getUnits()) {
            Unit unit = new Unit(owner, unitConfig);
            units.add(unit);
        }
    }

    /**
     * Removes all the entities for all units.
     */
    public void removeAll() {
        units.forEach(Unit::remove);
        units.clear();
    }
}
