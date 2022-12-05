package net.redewonder.rankup.managers;

import net.redewonder.rankup.sql.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {

    private HashMap<UUID, CustomPlayer> customPlayers = new HashMap<UUID, CustomPlayer>();

    public CustomPlayer getCustomPlayer(UUID uuid) {
        return customPlayers.get(uuid);
    }

    public void addCustomPlayer(UUID uuid, CustomPlayer player) {
        customPlayers.put(uuid, player);
    }

    public void removeCustomPlayer(UUID uuid) {
        customPlayers.remove(uuid);
    }

    public static void updatePlayer(Player player) {
        player.setGameMode(GameMode.SURVIVAL);
        player.setFoodLevel(20);
        player.setHealth(20);

        player.teleport(LocationsManager.getLocation(player, "Spawn"));
    }
}
