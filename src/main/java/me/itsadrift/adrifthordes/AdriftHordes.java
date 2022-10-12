package me.itsadrift.adrifthordes;

import me.itsadrift.adrifthordes.command.HordeCommand;
import me.itsadrift.adrifthordes.menu.MenuListener;
import me.itsadrift.adrifthordes.menu.paged.PagedMenuListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdriftHordes extends JavaPlugin {

    private static AdriftHordes instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new MenuListener(), this);
        pm.registerEvents(new PagedMenuListener(), this);

        getCommand("horde").setExecutor(new HordeCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static AdriftHordes getInstance() {
        return instance;
    }
}
