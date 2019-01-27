package uk.antiperson.moremachines.compat.hooks;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Bukkit;
import uk.antiperson.moremachines.compat.CompatManager;
import uk.antiperson.moremachines.compat.PluginHook;

public class WorldGuardHook implements PluginHook {

    private CompatManager compatManager;
    public WorldGuardHook(CompatManager compatManager) {
        this.compatManager = compatManager;
    }

    @Override
    public void register() {
        if (Bukkit.getPluginManager().isPluginEnabled("WorldGuard")) {
            compatManager.register(this);
        }
    }

    @Override
    public boolean check(org.bukkit.Location location) {
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        Location location2 = BukkitAdapter.adapt(location);
        return query.testState(location2, null, Flags.BLOCK_BREAK);
    }
}
