package uk.antiperson.moremachines.commands;

import org.bukkit.command.CommandSender;

public interface AbstractCommand {

    boolean onCommand(CommandSender sender, String[] args);
}
