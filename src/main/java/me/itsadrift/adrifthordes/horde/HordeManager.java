package me.itsadrift.adrifthordes.horde;

import me.itsadrift.adrifthordes.AdriftHordes;
import me.itsadrift.adrifthordes.mob.MobBuilder;
import me.itsadrift.adrifthordes.utils.LocUtils;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.HashMap;

public class HordeManager {

    private HashMap<String, HordeMob> mobMap = new HashMap<>();

    private AdriftHordes main;
    public HordeManager(AdriftHordes main) {
        this.main = main;
    }

    public void loadMobTypes() {
        for (String key : main.getConfig().getConfigurationSection("mobs").getKeys(false)) {
            mobMap.put(key, new HordeMob(main, EntityType.valueOf(main.getConfig().getString("mobs." + key + ".type")), key));
        }
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
