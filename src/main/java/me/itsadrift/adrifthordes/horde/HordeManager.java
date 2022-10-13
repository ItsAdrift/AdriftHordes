package me.itsadrift.adrifthordes.horde;

import me.itsadrift.adrifthordes.AdriftHordes;
import me.itsadrift.adrifthordes.mob.MobBuilder;
import me.itsadrift.adrifthordes.utils.LocUtils;
import org.bukkit.Location;

public class HordeManager {

    private AdriftHordes main;
    public HordeManager(AdriftHordes main) {
        this.main = main;
    }

    public void addLocation(Location loc) {
       int id = main.getConfig().getConfigurationSection("locations").getKeys(false).size()+1;
       main.getConfig().set("locations." +id, LocUtils.encode(loc));
       main.saveConfig();
    }

    public void startHorde(String mobID) {
        // get the actual mob

        // get amount of mobs

        // split those mobs between all the locations

        // spawn the mobs

        // announce mobs

        // start timer until mobs are removed
    }

}
