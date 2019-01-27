package uk.antiperson.moremachines.compat;

import org.bukkit.Location;

public interface PluginHook {

    void register();

    boolean check(Location location);
}
