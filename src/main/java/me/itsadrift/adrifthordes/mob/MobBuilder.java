package me.itsadrift.adrifthordes.mob;

import me.itsadrift.adrifthordes.AdriftHordes;
import me.itsadrift.adrifthordes.events.HordeMobDeathEvent;
import me.itsadrift.adrifthordes.utils.WeightedRandomBag;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class MobBuilder implements Listener {

    private Entity entity;

    private EntityType type;
    private String name;
    private boolean customNameVisible = true;

    private double health = 20;

    private double speed = 0;

    private MobArmor mobArmor;
    private WeightedRandomBag<ItemStack> mobDrops;

    public MobBuilder(EntityType type) {
        this.type = type;

        Bukkit.getPluginManager().registerEvents(this, AdriftHordes.getInstance());
    }

    public void spawn(Location loc) {
        LivingEntity e = (LivingEntity) loc.getWorld().spawnEntity(loc, type);
        e.setCustomName(name);
        e.setCustomNameVisible(customNameVisible);

        e.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
        e.setHealth(health);

        if (speed != 0)
            e.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);

        e.setRemoveWhenFarAway(false);

        this.entity = e;
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if (e.getEntity().getUniqueId().equals(entity.getUniqueId())) {
            if (e.getEntity().getKiller() != null) {
                e.getDrops().clear();
                e.getDrops().add(mobDrops.getRandom());

                HordeMobDeathEvent event = new HordeMobDeathEvent(e.getEntity().getKiller(), this, e.getDrops());
                Bukkit.getPluginManager().callEvent(event);

                if (!event.getDrops().equals(e.getDrops())) {
                    e.getDrops().clear();
                    e.getDrops().addAll(event.getDrops());
                }

            }
        }
    }

    public MobBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MobBuilder setType(EntityType type) {
        this.type = type;
        return this;
    }

    public MobBuilder setCustomNameVisible(boolean customNameVisible) {
        this.customNameVisible = customNameVisible;
        return this;
    }

    public MobBuilder setHealth(double health) {
        this.health = health;
        return this;
    }

    public MobBuilder setSpeed(double speed) {
        this.speed = speed;
        return this;
    }

    public MobBuilder setMobArmor(MobArmor mobArmor) {
        this.mobArmor = mobArmor;
        return this;
    }

    public MobBuilder setMobDrops(WeightedRandomBag<ItemStack> mobDrops) {
        this.mobDrops = mobDrops;
        return this;
    }

    @Nullable
    public Entity getEntity() {
        return entity;
    }

    public EntityType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public boolean isCustomNameVisible() {
        return customNameVisible;
    }

    public double getHealth() {
        return health;
    }

    public double getSpeed() {
        return speed;
    }

    public MobArmor getMobArmor() {
        return mobArmor;
    }

    public WeightedRandomBag<ItemStack> getMobDrops() {
        return mobDrops;
    }
}
