package me.itsadrift.adrifthordes;

import me.itsadrift.adrifthordes.command.HordeCommand;
import me.itsadrift.adrifthordes.horde.HordeManager;
import me.itsadrift.adrifthordes.input.PlayerInputListener;
import me.itsadrift.adrifthordes.input.PlayerInputManager;
import me.itsadrift.adrifthordes.menu.MenuListener;
import me.itsadrift.adrifthordes.menu.paged.PagedMenuListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdriftHordes extends JavaPlugin {

    private static AdriftHordes instance;

    private HordeManager hordeManager;
    private PlayerInputManager inputManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        hordeManager  = new HordeManager(this);

        inputManager = new PlayerInputManager(this);

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new MenuListener(), this);
        pm.registerEvents(new PagedMenuListener(), this);
        pm.registerEvents(new PlayerInputListener(inputManager), this);

        HordeCommand cmd = new HordeCommand(this);
        getCommand("horde").setExecutor(cmd);
        getCommand("horde").setTabCompleter(cmd);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static AdriftHordes getInstance() {
        return instance;
    }

    public HordeManager getHordeManager() {
        return hordeManager;
    }

    public PlayerInputManager getInputManager() {
        return inputManager;
    }
}
