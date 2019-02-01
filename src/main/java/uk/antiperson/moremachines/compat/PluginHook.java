package uk.antiperson.moremachines.compat;

import org.bukkit.Location;

public interface PluginHook {

    boolean canEnable();

    boolean check(Location location);
}
