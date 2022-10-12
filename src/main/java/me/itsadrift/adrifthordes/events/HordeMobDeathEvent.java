package me.itsadrift.adrifthordes.events;

import me.itsadrift.adrifthordes.mob.MobBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class HordeMobDeathEvent extends Event {

    private MobBuilder mob;
    private Player player;

    private List<ItemStack> drops;

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public HordeMobDeathEvent(Player player, MobBuilder mob, List<ItemStack> drops){
        this.player = player;
        this.mob = mob;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    public MobBuilder getMob() {
        return mob;
    }

    public Player getPlayer() {
        return player;
    }

    public List<ItemStack> getDrops() {
        return drops;
    }

    public void setDrops(List<ItemStack> drops) {
        this.drops = drops;
    }
}
