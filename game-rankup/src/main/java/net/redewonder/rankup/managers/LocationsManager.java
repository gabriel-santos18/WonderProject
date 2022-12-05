package net.redewonder.rankup.managers;

import net.redewonder.rankup.Rankup;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class LocationsManager {

    public static void setLocation(Player player, String nameLocation) {

        YamlConfiguration modifyFile = YamlConfiguration.loadConfiguration(Rankup.getInstance().file);
        modifyFile.set(nameLocation + ".World", player.getLocation().getWorld().getName());
        modifyFile.set(nameLocation + ".X", player.getLocation().getX());
        modifyFile.set(nameLocation + ".Y", player.getLocation().getY());
        modifyFile.set(nameLocation + ".Z", player.getLocation().getZ());
        modifyFile.set(nameLocation + ".YAW", player.getLocation().getYaw());
        modifyFile.set(nameLocation + ".PITCH", player.getLocation().getPitch());

        try {
            modifyFile.save(Rankup.getInstance().file);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public static Location getLocation(Player player, String nameLocation) {


        YamlConfiguration modifyFile = YamlConfiguration.loadConfiguration(Rankup.getInstance().file);

        return new Location(Bukkit.getWorld(modifyFile.getString(nameLocation + ".World")),
                modifyFile.getDouble(nameLocation + ".X"),
                modifyFile.getDouble(nameLocation + ".Y"),
                modifyFile.getDouble(nameLocation + ".Z"),
                (float) modifyFile.getDouble(nameLocation + ".YAW"),
                (float) modifyFile.getDouble(nameLocation + ".PITCH"));

    }

    public static boolean isLocation(Player player, String nameLocation) {
        YamlConfiguration modifyFile = YamlConfiguration.loadConfiguration(Rankup.getInstance().file);

        if (modifyFile.contains(nameLocation + ".World")) {
            return true;
        } else {
            return false;
        }
    }
}
