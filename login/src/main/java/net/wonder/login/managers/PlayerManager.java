package net.wonder.login.managers;

import me.imfighting.bukkit.api.TablistAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class PlayerManager {

    public static void updatePlayer(Player player) {
        player.getInventory().clear();
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setGameMode(GameMode.SURVIVAL);
        TablistAPI.sendTablist(player, "\n§e§lREDE WONDER\n§aloja.redewonder.net\n§fVocê está conectado em: §eLogin " +
                "#1\n", "\n§eDiscord: §fdiscord.gg/8ZCPPguw5S\n§fAdquira &e&lVIP §fou §e§lCASH §facessando: §eloja" +
                ".redewonder.net\n");
        ScoreboardManager.updateScore(player);

        for (Player online : Bukkit.getOnlinePlayers()) {
            player.hidePlayer(online);
        }

        for (int i=0; i <= 50; i++) {
            player.sendMessage("");
        }
    }
}
