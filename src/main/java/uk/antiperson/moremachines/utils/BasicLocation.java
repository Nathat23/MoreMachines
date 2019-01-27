package uk.antiperson.moremachines.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class BasicLocation {

    private int x;
    private int y;
    private int z;
    private World world;

    public BasicLocation(Location location) {
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
        this.world = location.getWorld();
    }

    public BasicLocation(World world, int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public World getWorld() {
        return world;
    }

    public Location toBukkit() {
        return new Location(world, x, y, z);
    }

    public String toString() {
        return world.getName() + "," + x + "," + y + "," + z;
    }

    public static BasicLocation fromString(String string) {
        String[] split = string.split(",");
        World world = Bukkit.getWorld(split[0]);
        int x = Integer.valueOf(split[1]);
        int y = Integer.valueOf(split[2]);
        int z = Integer.valueOf(split[3]);
        return new BasicLocation(world, x, y, z);
    }

    public static BasicLocation convert(Location location) {
        return new BasicLocation(location);
    }
}
