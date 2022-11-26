package net.redewonder.lobby.managers;

import net.redewonder.lobby.group.Groups;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class NametagManager {

    public static void updateNametag(Player player) {
        for (Player online : Bukkit.getOnlinePlayers()) {
            online.getScoreboard().getTeam(Groups.MASTER.getOrderSymbol() + Groups.MASTER.name()).addEntry(player.getName());
        }
    }
}
