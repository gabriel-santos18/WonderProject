package net.redewonder.lobby.group;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class NametagManager {

    public static void setTag(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        for (Groups groups : Groups.values()) {
            Team team = player.getScoreboard().registerNewTeam(groups.getOrderSymbol() + groups.name());
            team.setPrefix(ChatColor.translateAlternateColorCodes('&', groups.getDisplay()));
        }
    }

    public static void newTag(Player player, Groups groups) {
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.getScoreboard().getTeam(groups.getOrderSymbol() + groups.name()).addEntry(player.getName());
        }
    }

    public static void removeTag(Player player) {
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
        }
    }

}
