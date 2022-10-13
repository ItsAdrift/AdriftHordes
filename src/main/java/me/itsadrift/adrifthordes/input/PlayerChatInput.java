package me.itsadrift.adrifthordes.input;

import me.itsadrift.adrifthordes.utils.HexUtils;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class PlayerChatInput {

    private Player player;
    private String prompt;

    private Consumer<String> response;

    private PlayerInputManager manager;
    public PlayerChatInput(PlayerInputManager manager, Player player, String prompt) {
        this.manager = manager;

        this.player = player;
        this.prompt = prompt;
    }

    public void send(Consumer<String> response) {
        this.response = response;

        player.sendMessage(HexUtils.colour(prompt));
        manager.addSession(player, this);
    }

    public Consumer<String> getConsumer() {
        return response;
    }

}
