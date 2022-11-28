package net.wonder.login.managers;

import net.wonder.login.Login;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class LocationManager {

    public static void setSpawn(Player player) {
        YamlConfiguration modifyFile = YamlConfiguration.loadConfiguration(Login.getInstance().file);
        modifyFile.set("Spawn.X", player.getLocation().getX());
        modifyFile.set("Spawn.Y", player.getLocation().getY());
        modifyFile.set("Spawn.Z", player.getLocation().getZ());
        modifyFile.set("Spawn.YAW", player.getLocation().getYaw());
        modifyFile.set("Spawn.PITCH", player.getLocation().getPitch());

        try {
            modifyFile.save(Login.getInstance().file);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public static Location getSpawn(Player player) {
        YamlConfiguration modifyFile = YamlConfiguration.loadConfiguration(Login.getInstance().file);
        return new Location(player.getWorld(),
                modifyFile.getDouble("Spawn.X"),
                modifyFile.getDouble("Spawn.Y"),
                modifyFile.getDouble("Spawn.Z"),
                (float) modifyFile.getDouble("Spawn.YAW"),
                (float) modifyFile.getDouble("Spawn.PITCH"));
    }
}
