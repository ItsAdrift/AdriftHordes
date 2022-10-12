package me.itsadrift.adrifthordes.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public enum MHFHeads {


    BLAZE(EntityType.BLAZE, "MHF_Blaze"),
    CAVE_SPIDER(EntityType.CAVE_SPIDER, "MHF_CaveSpider"),
    CHICKEN(EntityType.CHICKEN, "MHF_Chicken"),
    COW(EntityType.COW, "MHF_Cow"),
    CREEPER(EntityType.CREEPER, "MHF_Creeper"),
    ENDERMAN(EntityType.ENDERMAN, "MHF_Enderman"),
    GHAST(EntityType.GHAST, "MHF_Ghast"),
    IRON_GOLEM(EntityType.IRON_GOLEM, "MHF_Golem"),
    MAGMA_CUBE(EntityType.MAGMA_CUBE, "MHF_LavaSlime"),
    MUSHROOM_COW(EntityType.MUSHROOM_COW, "MHF_MushroomCow"),
    OCELOT(EntityType.OCELOT, "MHF_Ocelot"),
    PIG(EntityType.PIG, "MHF_Pig"),
    ZOMBIFIED_PIGLIN(EntityType.ZOMBIFIED_PIGLIN, "MHF_PigZombie"),
    SHEEP(EntityType.SHEEP, "MHF_Sheep"),
    SKELETON(EntityType.SKELETON, "MHF_Skeleton"),
    SLIME(EntityType.SLIME, "MHF_Slime"),
    SPIDER(EntityType.SPIDER, "MHF_Spider"),
    SQUID(EntityType.SQUID, "MHF_Squid"),
    VILLAGER(EntityType.VILLAGER, "MHF_Villager"),
    WITHER_SKELETON(EntityType.WITHER_SKELETON, "MHF_WSkeleton"),
    ZOMBIE(EntityType.ZOMBIE, "MHF_Zombie");


    public EntityType type;
    public String mhf;
    MHFHeads(EntityType type, String mhf) {
        this.type = type;
        this.mhf = mhf;
    }

    public static MHFHeads fromType(EntityType type) {
        for (MHFHeads h : values()) {
            if (h.type == type) {
                return h;
            }
        }

        return null;
    }

    public static ItemStack getHeadItem(EntityType type) {
        ItemStack i = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta m = (SkullMeta) i;
        m.setOwningPlayer(Bukkit.getOfflinePlayer(fromType(type).mhf));
        return i;
    }
}
