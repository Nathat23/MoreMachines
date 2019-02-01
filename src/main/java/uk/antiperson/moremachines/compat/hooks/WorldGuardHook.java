package uk.antiperson.moremachines.compat.hooks;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Bukkit;
import uk.antiperson.moremachines.MoreMachines;
import uk.antiperson.moremachines.compat.PluginHook;

public class WorldGuardHook implements PluginHook {

    private MoreMachines mm;
    public WorldGuardHook(MoreMachines mm) {
        this.mm = mm;
    }

    @Override
    public boolean canEnable() {
        return mm.getConfigManager().getGeneralConfig().isWorldGuardAllowed() &&
                Bukkit.getPluginManager().isPluginEnabled("WorldGuard");
    }

    @Override
    public boolean check(org.bukkit.Location location) {
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        Location location2 = BukkitAdapter.adapt(location);
        return query.testState(location2, null, Flags.BLOCK_BREAK);
    }
}
