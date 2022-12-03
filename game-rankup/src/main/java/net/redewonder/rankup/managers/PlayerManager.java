package net.redewonder.rankup.managers;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class PlayerManager {

    public static void updatePlayer(Player player) {
        player.setGameMode(GameMode.SURVIVAL);
        player.setFoodLevel(20);
        player.setHealth(20);
    }
}
