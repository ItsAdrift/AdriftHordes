package me.itsadrift.adrifthordes.gui;

import me.itsadrift.adrifthordes.menu.Menu;
import me.itsadrift.adrifthordes.menu.MenuButton;
import me.itsadrift.adrifthordes.utils.ItemBuilder;
import me.itsadrift.adrifthordes.utils.MHFHeads;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class MobCreatorMenu extends Menu {
    /**
     * Menu constructor.
     *
     * @param title The title of the inventory.
     */
    public MobCreatorMenu(String title, EntityType type, String mobID) {
        super(title, 3);

        setBackground(Material.BLACK_STAINED_GLASS_PANE);
        registerButton(new MenuButton(new ItemBuilder(MHFHeads.getHeadItem(type)).setDisplayName("&e&l" +mobID)), 10);

        registerButton(new MenuButton(new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("&eMain Hand")),4);

        registerButton(new MenuButton(new ItemBuilder(Material.DIAMOND_HELMET).setDisplayName("&eHELMET")),4);
        registerButton(new MenuButton(new ItemBuilder(Material.DIAMOND_CHESTPLATE).setDisplayName("&eCHESTPLATE")),5);
        registerButton(new MenuButton(new ItemBuilder(Material.DIAMOND_LEGGINGS).setDisplayName("&eLEGGINGS")),6);
        registerButton(new MenuButton(new ItemBuilder(Material.DIAMOND_BOOTS).setDisplayName("&eBOOTS")),7);

        registerButton(new MenuButton(new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setDisplayName("&r")).setWhenClicked(c -> {
            c.getPlayer().getOpenInventory().getTopInventory().setItem(c.getE().getSlot(),c.getE().getCursor());
        }), new int[]{13,14,15,16});

    }
}
