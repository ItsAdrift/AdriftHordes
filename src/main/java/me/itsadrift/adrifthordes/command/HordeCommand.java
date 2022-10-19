package me.itsadrift.adrifthordes.command;

import me.itsadrift.adrifthordes.AdriftHordes;
import me.itsadrift.adrifthordes.gui.MobCreatorMenu;
import me.itsadrift.adrifthordes.utils.Base64Utils;
import org.bukkit.Material;
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

    private AdriftHordes main;
    public HordeCommand(AdriftHordes main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        // /horde addDrop basic <chance>

        if (args.length == 1) {
            if (!(sender instanceof Player)) {
                return false;
            }
            Player player = (Player) sender;
            if (args[0].equalsIgnoreCase("addLocation")) {
                main.getHordeManager().addLocation(player.getLocation());
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("start")) {
                String hordeID = args[1].toLowerCase(Locale.ROOT);
                // confirm valid hordeID
            }
        } else if (args.length == 3) {
            if (args[0].equalsIgnoreCase("createmob")) {
                String id = args[1];

                if (isValidAsEntity(args[2])) {
                    new MobCreatorMenu("&e&l"+id, EntityType.valueOf(args[2]), id).open((Player) sender);
                } else {
                    // invalid ent type
                }
            } else if (args[0].equalsIgnoreCase("addDrop")) {
                if (main.getConfig().contains("mobs." + args[1])) {
                    if (isDouble(args[2])) {
                        if (!(sender instanceof Player)) {
                            return false;
                        }
                        Player player = (Player) sender;
                        if (player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().getType() != Material.AIR) {
                            List<String> l = new ArrayList<>();
                            if (main.getConfig().contains("mobs." + args[1] + ".drops")) {
                                l = main.getConfig().getStringList("mobs." + args[1] + ".drops");
                            }

                            l.add(""+args[2] + "@" + Base64Utils.itemToBase64(player.getInventory().getItemInMainHand()));
                            main.getConfig().set("mobs." + args[1] + ".drops", l);
                            main.saveConfig();

                        } else {
                            // Not holding item
                        }




                    } else {
                        // Invalid Integer For Reward
                    }
                } else {
                    // Invalid horde mob type
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
            return StringUtil.copyPartialMatches(args[0], Arrays.asList("help", "createmob", "editmob", "removemob", "time", "start", "end", "addLocation", "listLocations"), new ArrayList<>());
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

    public boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
