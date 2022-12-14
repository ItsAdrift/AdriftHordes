package me.itsadrift.adrifthordes.menu.paged;

import me.itsadrift.adrifthordes.menu.IMenu;
import me.itsadrift.adrifthordes.menu.MenuButton;
import me.itsadrift.adrifthordes.menu.click.MenuClick;
import me.itsadrift.adrifthordes.menu.click.MenuClickInventory;
import me.itsadrift.adrifthordes.utils.HexUtils;
import me.itsadrift.adrifthordes.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class PagedMenu implements IMenu {

    private HashMap<Integer, MenuPage> pages;
    private HashMap<UUID, Integer> users;

    private Consumer<Player> inventoryClosed;
    private Consumer<Player> inventoryOpened;

    private Consumer<MenuClick> clickConsumer;

    public PagedMenu(String title, List<MenuButton> buttons) {
        pages = new HashMap<>();
        users = new HashMap<>();

        // Arrow Menu Buttons
        MenuButton[] arrowMenuButtons = getArrowMenuButtons();
        HashMap<Integer, MenuButton> tempItems = new HashMap<>();

        if (buttons.size() == 0) {

            tempItems.put(45, arrowMenuButtons[0]);
            tempItems.put(53, arrowMenuButtons[1]);

            MenuButton black = new MenuButton(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName("&0"));
            for (int a = 46; a < 53; a++) {
                tempItems.put(a, black);
            }

            MenuPage menuPage = new MenuPage(0, title, tempItems);
            pages.put(0, menuPage);

            return;
        }

        int page = 0;
        for (int i = 0; i < buttons.size(); i++) {
            if (((i != 0)&& i % 45 == 0) || i+1 == buttons.size()) {
                if (i+1 == buttons.size()) {
                    tempItems.put(i - (45 * page), buttons.get(i));
                }

                // Create current page, increase to next page
                tempItems.put(45, arrowMenuButtons[0]);
                tempItems.put(53, arrowMenuButtons[1]);

                MenuButton black = new MenuButton(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName("&0"));
                for (int a = 46; a < 53; a++) {
                    tempItems.put(a, black);
                }

                MenuPage menuPage = new MenuPage(page, title, tempItems);
                pages.put(page, menuPage);
                page++;
                tempItems.clear();

                tempItems.put(i - (45 * page), buttons.get(i));

                continue;
            }

            tempItems.put(i - (45 * page), buttons.get(i));
        }
    }

    public void openPage(Player player, int page) {
        MenuPage menuPage = pages.get(0);
        if (pages.containsKey(page)) {
            menuPage = pages.get(page);
        }

        player.openInventory(menuPage.getInventory());

        users.put(player.getUniqueId(), page);
        PagedMenuManager.getInstance().registerMenu(player.getUniqueId(), this);
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

    public void setClickConsumer(Consumer<MenuClick> clickConsumer) {
        this.clickConsumer = clickConsumer;
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
        if (inventoryOpened != null) {
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

        if (clicked == null || users.get(event.getWhoClicked().getUniqueId()) == null) {
            return;
        }

        MenuPage page = getPage(users.get(event.getWhoClicked().getUniqueId()));

        if (page==null)
            return;

        Map<Integer, MenuButton> itemMap = page.getItemMap();

        MenuClickInventory inventory = MenuClickInventory.MENU;
        if (event.getClickedInventory().getType() == InventoryType.PLAYER) {
            inventory = MenuClickInventory.PLAYER;
        }
        MenuClick click = new MenuClick((Player) event.getWhoClicked(), clicked, this, event.getClick(), inventory, page.getPage(), event);

        if (itemMap.containsKey(event.getRawSlot())) {
            // Clicked on a valid button
            if (itemMap.get(event.getRawSlot()).getWhenClicked() != null) {
                Consumer<MenuClick> consumer = itemMap.get(event.getRawSlot()).getWhenClicked();
                consumer.accept(click);
            }
        }
        if (clickConsumer != null) {
            clickConsumer.accept(click);
        }
    }

    public MenuPage getPage(int page) {
        return pages.get(page);
    }

    private MenuButton[] getArrowMenuButtons() {
        ItemStack[] arrows = getArrows();
        MenuButton leftArrow = new MenuButton(arrows[0]).setWhenClicked(click -> {
            int currentPage = users.get(click.getPlayer().getUniqueId());
            if (currentPage != 0) {
                openPage(click.getPlayer(), currentPage - 1);
            } else {
                // Go to last page
                openPage(click.getPlayer(), pages.size() - 1);
            }
        });
        MenuButton rightArrow = new MenuButton(arrows[1]).setWhenClicked(click -> {
            int currentPage = users.get(click.getPlayer().getUniqueId());
            if (currentPage != pages.size() -1) {
                openPage(click.getPlayer(), currentPage + 1);
            } else {
                // Back to first page
                openPage(click.getPlayer(), 0);
            }
        });

        return new MenuButton[]{leftArrow, rightArrow};
    }

    private ItemStack[] getArrows() {
        ItemStack skullLeft = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta leftMeta = (SkullMeta) skullLeft.getItemMeta();
        leftMeta.setDisplayName(HexUtils.colour("&c&lGo Back"));
        leftMeta.setOwner("MHF_ArrowLeft");
        skullLeft.setItemMeta(leftMeta);

        ItemStack skullRight = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skullRight.getItemMeta();
        skullMeta.setOwner("MHF_ArrowRight");
        skullMeta.setDisplayName(HexUtils.colour("&a&lNext"));
        skullRight.setItemMeta(skullMeta);

        return new ItemStack[]{skullLeft, skullRight};
    }

}
