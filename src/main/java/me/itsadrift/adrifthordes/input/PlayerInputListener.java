package me.itsadrift.adrifthordes.input;

import me.itsadrift.adrifthordes.AdriftHordes;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerInputListener implements Listener {

    private PlayerInputManager manager;
    public PlayerInputListener(PlayerInputManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (manager.hasSession(e.getPlayer())) {
            manager.getSession(e.getPlayer()).getConsumer().accept(e.getMessage());
            manager.removeSession(e.getPlayer());
        }
    }

}
