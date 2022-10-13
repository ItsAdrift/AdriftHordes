package me.itsadrift.adrifthordes.input;

import me.itsadrift.adrifthordes.AdriftHordes;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerInputManager {

    private HashMap<UUID, PlayerChatInput> sessions = new HashMap<>();

    private AdriftHordes main;
    public PlayerInputManager(AdriftHordes main) {
        this.main = main;
    }

    public boolean hasSession(Player player) {
        return sessions.containsKey(player.getUniqueId());
    }

    public void addSession(Player player, PlayerChatInput session) {
        sessions.put(player.getUniqueId(), session);
    }

    public void removeSession(Player player) {
        sessions.remove(player.getUniqueId());
    }

    public PlayerChatInput getSession(Player player) {
        return sessions.get(player.getUniqueId());
    }

}
