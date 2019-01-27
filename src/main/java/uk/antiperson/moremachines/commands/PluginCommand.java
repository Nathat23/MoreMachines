package uk.antiperson.moremachines.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import uk.antiperson.moremachines.MoreMachines;
import uk.antiperson.moremachines.utils.GlobalValues;

public abstract class PluginCommand implements CommandExecutor, AbstractCommand {

    private MoreMachines mm;
    private String cmdName;
    private String permission;
    private int argsLength;
    private int minLength;
    public PluginCommand(MoreMachines mm, String cmdName, String permission, int argsLength, int minLength) {
        this.mm = mm;
        this.cmdName = cmdName;
        this.permission = permission;
        this.argsLength = argsLength;
        this.minLength = minLength;
    }

    public void register() {
        mm.getCommand(cmdName).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission(permission)) {
            if (strings.length > argsLength || strings.length < minLength || onCommand(commandSender, strings)) {
                commandSender.sendMessage(GlobalValues.PLUGIN_TAG + "Invalid arguments!");
            }
        } else {
            commandSender.sendMessage(GlobalValues.PLUGIN_TAG + "No permission!");
        }
        return false;
    }

    public MoreMachines getMoreMachines() {
        return mm;
    }
}
