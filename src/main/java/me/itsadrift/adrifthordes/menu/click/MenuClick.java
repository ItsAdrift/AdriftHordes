package me.itsadrift.adrifthordes.menu.click;

import me.itsadrift.adrifthordes.menu.IMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MenuClick {

    private Player player;
    private ItemStack item;
    private IMenu menu;

    private ClickType type;

    private InventoryClickEvent e;

    private MenuClickInventory inventory;

    private int page = 0;

    public MenuClick(Player player, ItemStack item, IMenu menu, ClickType type, MenuClickInventory inventory, InventoryClickEvent e) {
        this.player = player;
        this.item = item;
        this.menu = menu;

        this.type = type;
        this.inventory = inventory;

        this.e = e;
    }

    public MenuClick(Player player, ItemStack item, IMenu menu, ClickType type, MenuClickInventory inventory, int page, InventoryClickEvent e) {
        this.player = player;
        this.item = item;
        this.menu = menu;

        this.type = type;
        this.inventory = inventory;

        this.page = page;
        this.e = e;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getItem() {
        return item;
    }

    public IMenu getMenu() {
        return menu;
    }

    public ClickType getType() {
        return type;
    }

    public MenuClickInventory getInventory() {
        return inventory;
    }

    public int getPage() {
        return page;
    }

    public InventoryClickEvent getE() {
        return e;
    }

}
