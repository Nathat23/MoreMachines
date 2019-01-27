package uk.antiperson.moremachines.commands;

import uk.antiperson.moremachines.MoreMachines;
import uk.antiperson.moremachines.commands.command.GiveMachine;
import uk.antiperson.moremachines.commands.command.ListCommands;

public class CommandManager {

    private MoreMachines mm;
    public CommandManager(MoreMachines mm) {
        this.mm = mm;
    }

    public void registerCommands() {
        new GiveMachine(mm).register();
        new ListCommands(mm).register();
    }
}
