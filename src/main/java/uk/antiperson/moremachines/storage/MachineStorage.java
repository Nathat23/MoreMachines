package uk.antiperson.moremachines.storage;

import uk.antiperson.moremachines.machines.Machine;

import java.util.Set;

public interface MachineStorage {

    Set<Machine> loadMachines();

    void storeMachines(Set<Machine> machines);
}
