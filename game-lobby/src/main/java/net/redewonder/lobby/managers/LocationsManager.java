package net.redewonder.lobby.managers;

import net.redewonder.lobby.Lobby;
import net.redewonder.lobby.sql.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class LocationsManager {

    public static void setSpawn(Player player) {
        YamlConfiguration modifyFile = YamlConfiguration.loadConfiguration(Lobby.getInstance().file);
        modifyFile.set("Spawn.X", player.getLocation().getX());
        modifyFile.set("Spawn.Z", player.getLocation().getZ());
        modifyFile.set("Spawn.YAW", player.getLocation().getYaw());
        modifyFile.set("Spawn.PITCH", player.getLocation().getPitch());

        try {
            modifyFile.save(Lobby.getInstance().file);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public static Location getSpawn(Player player) {
        YamlConfiguration modifyFile = YamlConfiguration.loadConfiguration(Lobby.getInstance().file);
            return new Location(player.getWorld(),
                    modifyFile.getDouble("Spawn.X"),
                    modifyFile.getDouble("Spawn.Y"),
                    modifyFile.getDouble("Spawn.Z"),
                    (float) modifyFile.getDouble("Spawn.YAW"),
                    (float) modifyFile.getDouble("Spawn.PITCH"));
    }
}
