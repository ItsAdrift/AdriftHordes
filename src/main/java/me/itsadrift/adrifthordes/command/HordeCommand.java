package me.itsadrift.adrifthordes.command;

import me.itsadrift.adrifthordes.gui.MobCreatorMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class HordeCommand implements CommandExecutor, TabCompleter {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        // /horde createmob basic SKELETON

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("start")) {
                String hordeID = args[1].toLowerCase(Locale.ROOT);
                // confirm valid hordeID
            }
        } else if (args.length == 3) {
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

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        List<String> results = new ArrayList<>();

        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], Arrays.asList("help", "createmob", "editmob", "removemob", "time", "start", "end"), new ArrayList<>());
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("createmob")) {
                results.add("<id>");
            } else if (args[0].equalsIgnoreCase("start")) {
                results.add("<hordeID>");
            }
        } else if (args.length == 3) {
            if (args[0].equalsIgnoreCase("createmob")) {
                results.add("<EntityType>");
            }
        }

        return results;
    }

    private boolean equalsAny(String s, String... s1) {
        for (String s2 : s1) {
            if (s2.equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }

}
