package me.itsadrift.adrifthordes.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class LocUtils {
    public static Location decode(String str) {
        String[] encoded = str.split(":");
        Location loc = new Location(Bukkit.getServer().getWorld(encoded[0]), 0.0D, 0.0D, 0.0D);
        loc.setX(Double.parseDouble(encoded[1]));
        loc.setY(Double.parseDouble(encoded[2]));
        loc.setZ(Double.parseDouble(encoded[3]));
        loc.setPitch(Float.parseFloat(encoded[4]));
        loc.setYaw(Float.parseFloat(encoded[5]));
        return loc;
    }

    public static String encode(Location loc) {
        return loc.getWorld().getName() + ":" + loc.getX() + ":" + loc.getY() + ":" + loc.getZ() + ":" + loc.getPitch() + ":" + loc.getYaw();
    }

    public static Location getBlockUnderPlayer(Location playerLocation) {
        Location b11 = playerLocation.clone().add(0.3, -1, -0.3);
        if (b11.getBlock().getType() != Material.AIR) {
            return b11;
        }
        Location b12 = playerLocation.clone().add(-0.3, -1, -0.3);
        if (b12.getBlock().getType() != Material.AIR) {
            return b12;
        }
        Location b21 = playerLocation.clone().add(0.3, -1, 0.3);
        if (b21.getBlock().getType() != Material.AIR) {
            return b21;
        }
        Location b22 = playerLocation.clone().add(-0.3, -1, +0.3);
        if (b22.getBlock().getType() != Material.AIR) {
            return b22;
        }
        return playerLocation;
    }
}
