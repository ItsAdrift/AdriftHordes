package me.itsadrift.adrifthordes.menu.paged;

import me.itsadrift.adrifthordes.menu.MenuButton;
import me.itsadrift.adrifthordes.utils.HexUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class MenuPage {

    private Inventory inventory;
    private int page;

    private final HashMap<Integer, MenuButton> itemMap;

    public MenuPage(int page, String title, HashMap<Integer, MenuButton> items) {
        this.page = page;

        this.itemMap = (HashMap<Integer, MenuButton>) items.clone();

        inventory = Bukkit.createInventory(null, 54, HexUtils.colour(title));
        for (Map.Entry<Integer, MenuButton> e : itemMap.entrySet()) {
            inventory.setItem(e.getKey(), e.getValue().getItemStack());
        }

    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getPage() {
        return page;
    }

    public HashMap<Integer, MenuButton> getItemMap() {
        return itemMap;
    }

    private String colour(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
