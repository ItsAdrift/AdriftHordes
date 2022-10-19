package me.itsadrift.adrifthordes.gui;

import me.itsadrift.adrifthordes.AdriftHordes;
import me.itsadrift.adrifthordes.input.PlayerChatInput;
import me.itsadrift.adrifthordes.menu.Menu;
import me.itsadrift.adrifthordes.menu.MenuButton;
import me.itsadrift.adrifthordes.utils.Base64Utils;
import me.itsadrift.adrifthordes.utils.ItemBuilder;
import me.itsadrift.adrifthordes.utils.MHFHeads;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public class MobCreatorMenu extends Menu {
    /**
     * Menu constructor.
     *
     * @param title The title of the inventory.
     */
    public MobCreatorMenu(String title, EntityType type, String mobID) {
        super(title, 3);
        AdriftHordes main = AdriftHordes.getInstance();

        setBackground(Material.BLACK_STAINED_GLASS_PANE);
        registerButton(new MenuButton(new ItemBuilder(MHFHeads.getHeadItem(type)).setDisplayName("&e&l" +mobID).setLore("&7Click to set name")).setWhenClicked(c -> {
            save(type, mobID);
            c.getPlayer().closeInventory();

            new PlayerChatInput(main.getInputManager(), c.getPlayer(), "\n&a&lEnter a name for the mob\n&f").send(response -> {
                c.getPlayer().openInventory(inventory);
                main.getConfig().set("mobs." + mobID + ".name", response);
                main.saveConfig();
            });
        }), 0);

        registerButton(new MenuButton(new ItemBuilder(MHFHeads.getHeadItem(type)).setDisplayName("&e&l" +mobID).setLore("&7Click to set name")).setWhenClicked(c -> {
            save(type, mobID);
            c.getPlayer().closeInventory();

            new PlayerChatInput(main.getInputManager(), c.getPlayer(), "\n&a&lEnter a name for the mob\n&f").send(response -> {
                c.getPlayer().openInventory(inventory);
                main.getConfig().set("mobs." + mobID + ".name", response);
                main.saveConfig();
            });
        }), 10);

        registerButton(new MenuButton(new ItemBuilder(MHFHeads.getHeadItem(type)).setDisplayName("&c&lComing Soon").setLore("&7I don't have anything to put here yet")).setWhenClicked(c -> {
            save(type, mobID);
            c.getPlayer().closeInventory();

            new PlayerChatInput(main.getInputManager(), c.getPlayer(), "\n&a&lEnter a name for the mob\n&f").send(response -> {
                c.getPlayer().openInventory(inventory);
                main.getConfig().set("mobs." + mobID + ".name", response);
                main.saveConfig();
            });
        }), 19);

        registerButton(new MenuButton(new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("&eMain Hand")),3);

        registerButton(new MenuButton(new ItemBuilder(Material.DIAMOND_HELMET).setDisplayName("&eHELMET")),4);
        registerButton(new MenuButton(new ItemBuilder(Material.DIAMOND_CHESTPLATE).setDisplayName("&eCHESTPLATE")),5);
        registerButton(new MenuButton(new ItemBuilder(Material.DIAMOND_LEGGINGS).setDisplayName("&eLEGGINGS")),6);
        registerButton(new MenuButton(new ItemBuilder(Material.DIAMOND_BOOTS).setDisplayName("&eBOOTS")),7);

        if (main.getConfig().contains("mobs." + mobID + ".mainHand")) {
            registerButton(new MenuButton(Base64Utils.itemFromBase64(main.getConfig().getString("mobs." + mobID + ".mainHand"))), 12);
        } else {
            registerButton(new MenuButton(new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setDisplayName("&r")).setWhenClicked(c -> {
                c.getPlayer().getOpenInventory().getTopInventory().setItem(c.getE().getSlot(),c.getE().getCursor());
            }), 12);
        }

        if (main.getConfig().contains("mobs." + mobID + ".helmet")) {
            registerButton(new MenuButton(Base64Utils.itemFromBase64(main.getConfig().getString("mobs." + mobID + ".helmet"))), 12);
        } else {
            registerButton(new MenuButton(new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setDisplayName("&r")).setWhenClicked(c -> {
                c.getPlayer().getOpenInventory().getTopInventory().setItem(c.getE().getSlot(),c.getE().getCursor());
            }), 13);
        }

        if (main.getConfig().contains("mobs." + mobID + ".chestplate")) {
            registerButton(new MenuButton(Base64Utils.itemFromBase64(main.getConfig().getString("mobs." + mobID + ".chestplate"))), 12);
        } else {
            registerButton(new MenuButton(new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setDisplayName("&r")).setWhenClicked(c -> {
                c.getPlayer().getOpenInventory().getTopInventory().setItem(c.getE().getSlot(),c.getE().getCursor());
            }), 14);
        }

        if (main.getConfig().contains("mobs." + mobID + ".leggings")) {
            registerButton(new MenuButton(Base64Utils.itemFromBase64(main.getConfig().getString("mobs." + mobID + ".leggings"))), 12);
        } else {
            registerButton(new MenuButton(new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setDisplayName("&r")).setWhenClicked(c -> {
                c.getPlayer().getOpenInventory().getTopInventory().setItem(c.getE().getSlot(),c.getE().getCursor());
            }), 15);
        }

        if (main.getConfig().contains("mobs." + mobID + ".boots")) {
            registerButton(new MenuButton(Base64Utils.itemFromBase64(main.getConfig().getString("mobs." + mobID + ".boots"))), 12);
        } else {
            registerButton(new MenuButton(new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setDisplayName("&r")).setWhenClicked(c -> {
                c.getPlayer().getOpenInventory().getTopInventory().setItem(c.getE().getSlot(),c.getE().getCursor());
            }), 16);
        }

        registerButton(new MenuButton(new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).setDisplayName("&a&lSave")).setWhenClicked(c -> {
            save(type, mobID);
        }), 22);

    }

    private void save(EntityType type, String mobID) {
        AdriftHordes main = AdriftHordes.getInstance();
        main.getConfig().set("mobs." + mobID + ".type", type.name());


        main.getConfig().set("mobs." + mobID + ".mainHand", Base64Utils.itemToBase64(inventory.getItem(12)));

        main.getConfig().set("mobs." + mobID + ".helmet", Base64Utils.itemToBase64(inventory.getItem(13)));
        main.getConfig().set("mobs." + mobID + ".chestplate", Base64Utils.itemToBase64(inventory.getItem(14)));
        main.getConfig().set("mobs." + mobID + ".leggings", Base64Utils.itemToBase64(inventory.getItem(15)));
        main.getConfig().set("mobs." + mobID + ".boots", Base64Utils.itemToBase64(inventory.getItem(16)));

        main.saveConfig();
    }
}
