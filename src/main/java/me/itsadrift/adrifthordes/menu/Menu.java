package me.itsadrift.adrifthordes.menu;

import me.itsadrift.adrifthordes.menu.click.MenuClick;
import me.itsadrift.adrifthordes.menu.click.MenuClickInventory;
import me.itsadrift.adrifthordes.utils.HexUtils;
import me.itsadrift.adrifthordes.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Menu implements IMenu {

    public Inventory inventory;

    private Map<Integer, MenuButton> buttonMap;

    private Consumer<Player> inventoryClosed;
    private Consumer<Player> inventoryOpened;

    /**
     * Menu constructor.
     * @param title The title of the inventory.
     * @param rows The amount of rows in the inventory.
     */
    public Menu(String title, int rows) {
        if (rows > 6 || rows < 1 || title.length() > 32) {
            // Invalid rows / title length requested.
            throw new IllegalArgumentException("Invalid arguments passed to menu constructor.");
        }

        // Initialise variables
        this.inventory = Bukkit.createInventory(null, rows * 9, HexUtils.colour(title));
        this.buttonMap = new HashMap<>();
    }

    /**
     * Opens the inventory to a specified player.
     * @param player The player to open the inventory to.
     */
    public void open(Player player) {
        MenuManager manager = MenuManager.getInstance();

        buttonMap.forEach((slot, button) -> {
            inventory.setItem(slot, button.getItemStack());
        });

        // Open the inventory and handle the open event.
        player.openInventory(inventory);
        manager.registerMenu(player.getUniqueId(), this);
        handleOpen(player);
    }

    /**
     * Registers a button in a specified slot.
     * @param button The button object to register.
     * @param slot The slot to associate it with.
     */
    public void registerButton(MenuButton button, int slot) {
        buttonMap.put(slot, button);
    }

    public void registerButton(MenuButton button, int[] slot) {
        for (int i : slot) {
            buttonMap.put(i, button);
        }

    }

    /**
     * Sets the value of the inventoryClosed consumer.
     * @param inventoryClosed The consumer to use.
     */
    public void setInventoryClosed(Consumer<Player> inventoryClosed) {
        this.inventoryClosed = inventoryClosed;
    }

    /**
     * Sets the value of the inventoryOpened consumer.
     * @param inventoryOpened The consumer to use.
     */
    public void setInventoryOpened(Consumer<Player> inventoryOpened) {
        this.inventoryOpened = inventoryOpened;
    }

    /**
     * Handles a player closing the inventory. <br>
     * Executes the inventoryClosed consumer if it is not null.
     * @param player The player who has closed the inventory.
     */
    public void handleClose(Player player) {
        if (inventoryClosed != null) {
            inventoryClosed.accept(player);
        }
    }

    /**
     * Handles a player opening the inventory. <br>
     * Executes the inventoryOpen consumer if it is not null.
     * @param player The player who has opened the inventory.
     */
    public void handleOpen(Player player) {
        // Is there an action associated with opening the inventory?
        if (inventoryOpened != null) {
            // Perform the action
            inventoryOpened.accept(player);
        }
    }

    /**
     * Handles an InventoryClickEvent inside this menu.
     * @param event The InventoryClickEvent.
     */
    public void handleClick(InventoryClickEvent event) {
        event.setCancelled(true);
        ItemStack clicked = event.getCurrentItem();

        if (clicked == null) {
            return;
        }

        if (buttonMap.containsKey(event.getRawSlot())) {
            // Clicked on a valid button

            Consumer<MenuClick> consumer = buttonMap.get(event.getRawSlot()).getWhenClicked();

            // Does the button clicked have an action associated with it?
            if (consumer != null) {
                // Performs the action.
                MenuClickInventory inventory = MenuClickInventory.MENU;
                if (event.getClickedInventory().getType() == InventoryType.PLAYER) {
                    inventory = MenuClickInventory.PLAYER;
                }
                consumer.accept(new MenuClick((Player) event.getWhoClicked(), clicked, this, event.getClick(), inventory, event));
            }
        }
    }

    public void setBackground(Material mat) {
        ItemStack stack = new ItemBuilder(mat).setDisplayName("&r").build();
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, stack);
        }
    }

}
