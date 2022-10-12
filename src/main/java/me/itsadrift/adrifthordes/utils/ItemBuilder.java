package me.itsadrift.adrifthordes.utils;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class ItemBuilder {
    ItemStack item;
    ItemMeta meta;

    int amount;

    List<String> lore = new ArrayList<>();
    String displayName;

    Map<Enchantment, Integer> enchants = new HashMap<>();

    boolean unbreakable;
    boolean glowing;

    public ItemBuilder(Material mat, String data) {
        if (mat == Material.PLAYER_HEAD) {
            this.item = new ItemStack(mat);
            SkullMeta meta = (SkullMeta) item.getItemMeta();
            meta.setOwningPlayer(Bukkit.getOfflinePlayer(data.replace("OWNER=", "")));
            item.setItemMeta(meta);
            this.meta = item.getItemMeta();

        } else {
            this.item = new ItemStack(mat);
            this.meta = this.item.getItemMeta();
        }
    }

    public ItemBuilder(Material mat) {
        this.item = new ItemStack(mat);
        this.meta = this.item.getItemMeta();
    }

    public ItemBuilder(Material mat, int amount) {
        this.item = new ItemStack(mat, amount);
        this.meta = this.item.getItemMeta();
        this.amount = amount;
    }

    public ItemBuilder(ItemStack item) {
        this.item = item;
        this.amount = item.getAmount();
        this.meta = item.getItemMeta();
        this.meta = item.getItemMeta();
        if (this.meta.getLore() != null)
            this.lore = this.meta.getLore();
        this.displayName = this.meta.getDisplayName();
        this.enchants = this.meta.getEnchants();
        this.unbreakable = this.meta.isUnbreakable();
    }

    public ItemBuilder setLore(String... lore) {
        List<String> a = new ArrayList<>();
        for (String s : Arrays.<String>asList(lore))
            a.add(colour(s));
        this.lore = a;
        this.meta.setLore(a);
        this.item.setItemMeta(this.meta);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        List<String> a = new ArrayList<>();
        for (String s : lore)
            a.add(colour(s));
        this.lore = a;
        this.meta.setLore(a);
        this.item.setItemMeta(this.meta);
        return this;
    }

    public ItemBuilder applyPlaceholders(Pair<String, Object>... placeholders) {
        HashMap<String, Object> placeholdersMap = new HashMap<>();
        for (Pair<String, Object> pair : placeholders)
            placeholdersMap.put(pair.getKey(), pair.getValue());

        List<String> newLore = new ArrayList<>();
        for (String loreLine : lore) {
            boolean placeHoldersApplied = false;
            String newLine = loreLine;
            // continue if loreLine does not contain any placeholder keys

            for (Map.Entry<String, Object> e : placeholdersMap.entrySet()) {
                if (!newLine.contains(e.getKey()))
                    continue;

                if (e.getValue() instanceof String) {
                    newLine = newLine.replace(e.getKey(), (String) e.getValue());
                    placeHoldersApplied = true;
                } else if (e.getValue() instanceof List) {
                    newLine = null;
                    newLore.addAll((List<String>) e.getValue());
                    placeHoldersApplied = true;
                }
            }


            if (!placeHoldersApplied)
                newLore.add(loreLine);
            else {
                if (newLine != null)
                    newLore.add(newLine);
            }

        }

        return setLore(newLore);
    }


    public ItemBuilder setDisplayName(String name) {
        this.displayName = name;
        this.meta.setDisplayName(colour(name));
        this.item.setItemMeta(this.meta);
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ItemBuilder addEnchant(Enchantment ench, int level) {
        this.meta.addEnchant(ench, level, true);
        this.item.setItemMeta(this.meta);
        this.enchants.put(ench, Integer.valueOf(level));
        return this;
    }

    public ItemBuilder setUnbreakable(boolean bool) {
        this.meta.setUnbreakable(bool);
        this.item.setItemMeta(this.meta);
        this.unbreakable = bool;
        return this;
    }

    public ItemBuilder setGlowing(boolean glowing) {
        this.glowing = glowing;
        this.meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        this.meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        return this;
    }

    public ItemBuilder setNBT(String tag) {
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setString("guiNBT", tag);
        this.item = nbtItem.getItem();
        this.meta = this.item.getItemMeta();
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.item.setAmount(amount);
        return this;
    }

    public boolean hasNBT(String tag) {
        return new NBTItem(item).hasKey("guiNBT");
    }

    public String getNBT() {
        NBTItem nbtItem = new NBTItem(item);
        return nbtItem.getString("guiNBT");
    }

    public ItemBuilder addItemFlag(ItemFlag flag) {
        meta.getItemFlags().add(flag);
        return this;
    }

    public ItemStack build() {
        this.item.setItemMeta(this.meta);
        return this.item;
    }

    public ItemStack getItem() {
        this.item.setItemMeta(this.meta);
        return this.item;
    }

    @Override
    public ItemBuilder clone() {
        return new ItemBuilder(build());
    }

    private String colour(String s) {
        return HexUtils.colour(s);
    }
}
