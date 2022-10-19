package me.itsadrift.adrifthordes.horde;

import me.itsadrift.adrifthordes.AdriftHordes;
import me.itsadrift.adrifthordes.mob.MobArmor;
import me.itsadrift.adrifthordes.mob.MobBuilder;
import me.itsadrift.adrifthordes.utils.Base64Utils;
import me.itsadrift.adrifthordes.utils.WeightedRandomBag;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.inventory.ItemStack;

import java.util.Base64;

public class HordeMob extends MobBuilder {
    public HordeMob(AdriftHordes main, EntityType type, String mobID) {
        super(type);

        setName(main.getConfig().getString("mobs." + mobID + ".name"));
        setCustomNameVisible(true);

        MobArmor armor = new MobArmor();
        armor.setMainHand(Base64Utils.itemFromBase64(main.getConfig().getString("mobs." + mobID + ".mainHand")));
        armor.setHelmet(Base64Utils.itemFromBase64(main.getConfig().getString("mobs." + mobID + ".helmet")));
        armor.setChestplate(Base64Utils.itemFromBase64(main.getConfig().getString("mobs." + mobID + ".chestplate")));
        armor.setLeggings(Base64Utils.itemFromBase64(main.getConfig().getString("mobs." + mobID + ".leggings")));
        armor.setBoots(Base64Utils.itemFromBase64(main.getConfig().getString("mobs." + mobID + ".boots")));

        setMobArmor(armor);

        WeightedRandomBag<ItemStack> bag = new WeightedRandomBag<>();
        if (main.getConfig().contains("mobs." + mobID + ".drops")) {
            for (String e : main.getConfig().getStringList("mobs." + mobID + ".drops")) {
                String[] str =e.split("@");
                double chance = Double.parseDouble(str[0]);
                ItemStack item = Base64Utils.itemFromBase64(str[1]);

                bag.addEntry(item, chance);
            }
        }

        setMobDrops(bag);

    }


}
