package uk.antiperson.moremachines.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import uk.antiperson.moremachines.machines.Machine;
import uk.antiperson.moremachines.machines.MachineType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Utilities {

    /**
     * Generate the skull meta for a head.
     *
     * @param item     the item to generate the meta for.
     * @param mojangId the mojang head url
     * @return the skull meta for a head.
     */
    public static SkullMeta getSkullMeta(ItemStack item, String mojangId) {
        SkullMeta sm = (SkullMeta) item.getItemMeta();
        String url = "http://textures.minecraft.net/texture/" + mojangId;

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = sm.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        profileField.setAccessible(true);
        try {
            profileField.set(sm, profile);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return sm;
    }

    /**
     * Replace the config color codes with bukkit color codes,
     *
     * @param str the string to replace color codes of.
     * @return translated string.
     */
    public static String translateColorCodes(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    /**
     * Parse machine config placeholders for multiple strings.
     *
     * @param replace list of strings to parse.
     * @param machine the machine.
     * @return parsed list of strings.
     */
    public static List<String> parseStrings(List<String> replace, Machine machine) {
        List<String> list = new ArrayList<>();
        for (String str : replace) {
            list.add(parseString(str, machine));
        }
        return list;
    }

    /**
     * Parse machine config placeholders for multiple strings.
     *
     * @param replace list of strings to parse.
     * @param level   level of the machine.
     * @param range   range of the machine.
     * @param type    type of the machine.

     * @return parsed list of strings.
     */
    public static List<String> parseStrings(List<String> replace, MachineType type, int range, int level) {
        List<String> list = new ArrayList<>();
        for (String str : replace) {
            list.add(parseString(str, type, range, level));
        }
        return list;
    }

    /**
     * Parse the config placeholders for a string.
     *
     * @param format  string to parse.
     * @param machine machine to parse info for.
     * @return parsed string.
     */
    public static String parseString(String format, Machine machine) {
        String string = parseString(format, machine.getType(), machine.getRange(), machine.getLevel());
        String replace = StringUtils.replace(string, "{running_status}", machine.isRunning() ? "Running" : "Stopped");
        String replace1 = StringUtils.replace(replace, "{status}", formatEnum(machine.getState()));
        return replace1;
    }

    /**
     * Parse the config placeholders for a string.
     *
     * @param format  string to parse.
     * @param level   level of the machine.
     * @param range   range of the machine.
     * @param type    type of the machine.
     * @return parsed string.
     */
    public static String parseString(String format, MachineType type, int range, int level) {
        String replace = StringUtils.replace(format, "{level}", Integer.toString(level));
        String replace1 = StringUtils.replace(replace, "{range}", Integer.toString(range));
        String replace2 = StringUtils.replace(replace1, "{type}", type.toString());
        return translateColorCodes(replace2);
    }

    public static String formatEnum(Enum format) {
        String chars = format.toString().replaceAll("_" , " ");
        return WordUtils.capitalizeFully(chars);
    }

}
