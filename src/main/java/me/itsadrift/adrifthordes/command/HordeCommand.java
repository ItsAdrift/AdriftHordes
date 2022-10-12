package me.itsadrift.adrifthordes.command;

import me.itsadrift.adrifthordes.gui.MobCreatorMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class HordeCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        // /horde createmob basic SKELETON

        if (args.length == 3) {
            if (args[0].equals("cratemob")) {
                String id = args[1];

                if (isValidAsEntity(args[2])) {
                    new MobCreatorMenu("&e&l"+id, EntityType.valueOf(args[2]), id).open((Player) sender);
                } else {
                    // invalid ent type
                }
            }
        }

        return false;
    }

    public boolean isValidAsEntity(String s) {
        return EntityType.valueOf(s).isSpawnable();
    }

}
