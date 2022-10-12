package me.itsadrift.adrifthordes.utils;

import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HexUtils {

    public static final char COLOR_CHAR = '\u00A7';

    public static String colour(String msg) {
        final Pattern hexPattern = Pattern.compile("&#" + "([A-Fa-f0-9]{6})");
        Matcher matcher = hexPattern.matcher(msg);
        StringBuffer buffer = new StringBuffer(msg.length() + 4 * 8);
        while (matcher.find()) {
            String group = matcher.group(1);
            matcher.appendReplacement(buffer, COLOR_CHAR + "x"
                    + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
                    + COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
                    + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
            );
        }
        return ChatColor.translateAlternateColorCodes('&', matcher.appendTail(buffer).toString());
    }

}
