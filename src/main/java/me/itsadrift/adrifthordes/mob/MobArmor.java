package me.itsadrift.adrifthordes.mob;

import org.bukkit.inventory.ItemStack;

public class MobArmor {

    private ItemStack mainHand;

    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;

    public MobArmor(){}

    public MobArmor(ItemStack mainHand, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
        this.mainHand = mainHand;
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
    }

    public ItemStack getMainHand() {
        return mainHand;
    }

    public MobArmor setMainHand(ItemStack mainHand) {
        this.mainHand = mainHand;
        return this;
    }

    public ItemStack getHelmet() {
        return helmet;
    }

    public MobArmor setHelmet(ItemStack helmet) {
        this.helmet = helmet;
        return this;
    }

    public ItemStack getChestplate() {
        return chestplate;
    }

    public MobArmor setChestplate(ItemStack chestplate) {
        this.chestplate = chestplate;
        return this;
    }

    public ItemStack getLeggings() {
        return leggings;
    }

    public MobArmor setLeggings(ItemStack leggings) {
        this.leggings = leggings;
        return this;
    }

    public ItemStack getBoots() {
        return boots;
    }

    public MobArmor setBoots(ItemStack boots) {
        this.boots = boots;
        return this;   
    }
}
