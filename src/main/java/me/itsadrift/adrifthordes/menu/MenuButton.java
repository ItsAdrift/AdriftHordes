package me.itsadrift.adrifthordes.menu;

import me.itsadrift.adrifthordes.menu.click.MenuClick;
import me.itsadrift.adrifthordes.utils.ItemBuilder;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class MenuButton {

    private ItemStack itemStack;
    private Consumer<MenuClick> whenClicked;

    /**
     * Class constructor
     * @param itemStack The ItemStack to use for this button.
     */
    public MenuButton(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public MenuButton(ItemBuilder builder) {
        this.itemStack = builder.build();
    }

    /*
     * Returns the value of the whenClicked consumer.
     * @return The whenClicked consumer.
     */
    public Consumer<MenuClick> getWhenClicked() {
        return whenClicked;
    }

    /**
     * Sets the value of the whenClicked consumer.
     * @param whenClicked The consumer to set.
     * @return Returns this object.
     */
    public MenuButton setWhenClicked(Consumer<MenuClick> whenClicked) {
        this.whenClicked = whenClicked;
        return this;
    }

    /**
     * Returns the ItemStack for this button.
     * @return The itemStack supplied in the constructor.
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

}
