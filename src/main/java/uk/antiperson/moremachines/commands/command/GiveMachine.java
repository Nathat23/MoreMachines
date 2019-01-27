package uk.antiperson.moremachines.commands.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import uk.antiperson.moremachines.MoreMachines;
import uk.antiperson.moremachines.commands.PluginCommand;
import uk.antiperson.moremachines.machines.MachineType;
import uk.antiperson.moremachines.utils.GlobalValues;

public class GiveMachine extends PluginCommand {

    public GiveMachine(MoreMachines mm) {
        super(mm, "givemachine", "moremachines.give", 2, 1);
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        MachineType type = MachineType.niceMatch(args[0]);
        Player player = args.length == 1 ? (Player) sender : Bukkit.getPlayer(args[1]);
        if (player == null || type == null) {
            return true;
        }
        ItemStack is = getMoreMachines().getItemHandler().createItem(type);
        player.getInventory().addItem(is);
        player.sendMessage(GlobalValues.PLUGIN_TAG + ChatColor.GREEN + "The machine has been added to your inventory.");
        return false;
    }

}
