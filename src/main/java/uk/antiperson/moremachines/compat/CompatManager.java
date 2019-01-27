package uk.antiperson.moremachines.compat;

import org.bukkit.Location;
import uk.antiperson.moremachines.MoreMachines;
import uk.antiperson.moremachines.compat.hooks.WorldGuardHook;

import java.util.HashSet;
import java.util.Set;

public class CompatManager {

    private Set<PluginHook> hooks;
    public CompatManager(MoreMachines mm) {
        this.hooks = new HashSet<>();
    }

    public void registerAll() {
        new WorldGuardHook(this).register();
    }

    public void register(PluginHook hook) {
        hooks.add(hook);
    }

    public boolean notAllowed(Location location) {
        for (PluginHook hook : hooks) {
            if (!hook.check(location)) {
                return true;
            }
        }
        return false;
    }
}
