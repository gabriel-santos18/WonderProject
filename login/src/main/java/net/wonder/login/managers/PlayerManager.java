package net.wonder.login.managers;

import me.imfighting.bukkit.api.TablistAPI;
import net.wonder.login.sql.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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
        player.getInventory().clear();
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setGameMode(GameMode.SURVIVAL);
        TablistAPI.sendTablist(player, "\n§e§lREDE WONDER\n§aloja.redewonder.net\n§fVocê está conectado em: §eLogin " +
                "#1\n", "\n§eDiscord: §fdiscord.gg/8ZCPPguw5S\n§fAdquira &e&lVIP §fou §e§lCASH §facessando: §eloja" +
                ".redewonder.net\n");
        ScoreboardManager.updateScore(player);
        player.teleport(LocationManager.getSpawn(player));

        for (Player online : Bukkit.getOnlinePlayers()) {
            player.hidePlayer(online);
        }

        for (int i=0; i <= 50; i++) {
            player.sendMessage("");
        }
    }
}
