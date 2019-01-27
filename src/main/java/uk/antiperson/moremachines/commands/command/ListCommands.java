package uk.antiperson.moremachines.commands.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import uk.antiperson.moremachines.MoreMachines;
import uk.antiperson.moremachines.commands.PluginCommand;
import uk.antiperson.moremachines.utils.GlobalValues;

import java.util.Map;

public class ListCommands extends PluginCommand {

    public ListCommands(MoreMachines mm) {
        super(mm, "moremachines", "moremachines.list", 0, 0);
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        sender.sendMessage(GlobalValues.PLUGIN_TAG + ChatColor.YELLOW  + "Commands list:");
        for (Map.Entry<String, Map<String, Object>> commands : getMoreMachines().getDescription().getCommands().entrySet()) {
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "/" + commands.getKey() + " " + ChatColor.GREEN + commands.getValue().values().toArray()[1]);
        }
        return false;
    }
}
