package uk.antiperson.moremachines.compat;

import org.bukkit.Location;
import uk.antiperson.moremachines.MoreMachines;
import uk.antiperson.moremachines.compat.hooks.WorldGuardHook;

import java.util.HashSet;
import java.util.Set;

public class CompatManager {

    private Set<PluginHook> hooks;
    private MoreMachines mm;
    public CompatManager(MoreMachines mm) {
        this.mm = mm;
        this.hooks = new HashSet<>();
    }

    /**
     * Registers all plugin hooks so they can be used by the plugin.
     */
    public void registerAll() {
        register(new WorldGuardHook(mm));
    }

    private void register(PluginHook hook) {
        if (hook.canEnable()) {
            hooks.add(hook);
        }
    }

    /**
     * Returns whether all the registered hooks allow an action at the location.
     * @param location the location to check
     * @return whether all the registered hooks allow an action at the location.
     */
    public boolean notAllowed(Location location) {
        for (PluginHook hook : hooks) {
            if (!hook.check(location)) {
                return true;
            }
        }
        return false;
    }

}
