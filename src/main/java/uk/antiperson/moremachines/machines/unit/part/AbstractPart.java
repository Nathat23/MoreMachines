package uk.antiperson.moremachines.machines.unit.part;

import uk.antiperson.moremachines.utils.BasicLocation;

public interface AbstractPart {

    void move(BasicLocation to);

    void spawn(BasicLocation where);

    void remove();
}
