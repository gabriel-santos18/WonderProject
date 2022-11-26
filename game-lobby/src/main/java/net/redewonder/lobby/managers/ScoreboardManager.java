package net.redewonder.lobby.managers;

import net.redewonder.lobby.Lobby;
import net.redewonder.lobby.group.Groups;
import net.redewonder.lobby.group.NametagManager;
import net.redewonder.lobby.server.ServerOnlineCount;
import net.redewonder.lobby.sql.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreboardManager {

    public static Scoreboard score;

    public static void updateScore(Player player) {

        score = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = score.registerNewObjective("Test", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§e§lREDE WONDER");

        Score space = obj.getScore("§a");
        space.setScore(7);

        Team group = score.registerNewTeam("group");
        group.addEntry("§f");
        group.setPrefix(" §fGrupo: ");
        group.setSuffix("§7...");
        obj.getScore("§f").setScore(6);

        Team online = score.registerNewTeam("onlines");
        online.addEntry("§2");
        online.setPrefix(" §fOnline: ");
        online.setSuffix("§b" + ServerOnlineCount.NETWORK_ONLINE_COUNT);
        obj.getScore("§2").setScore(5);

        Score space2 = obj.getScore("§b");
        space2.setScore(4);

        Score lobby = obj.getScore(" §fLobby: §7#1");
        lobby.setScore(3);

        Score space3 = obj.getScore("§c");
        space3.setScore(2);

        Score website = obj.getScore(" §eloja.redewonder.net");
        website.setScore(1);

        for (Groups groups : Groups.values()) {
            Team team = score.registerNewTeam(groups.getOrderSymbol() + groups.name());
            team.setPrefix(ChatColor.translateAlternateColorCodes('&', groups.getDisplay()));
        }

        for (Player online2 : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("grupo.master")) {
                score.getTeam(Groups.MASTER.getOrderSymbol() + Groups.MASTER.name()).addEntry(online2.getName());
            } else if (player.hasPermission("grupo.gerente")) {
                score.getTeam(Groups.GERENTE.getOrderSymbol() + Groups.GERENTE.name()).addEntry(online2.getName());
            }
    }




        /* if (CustomPlayer.getGroup() == "§6MASTER") {
            score.getTeam("amaster").addEntry(player.getName());
        } else if (CustomPlayer.getGroup() == "§3GERENTE") {
            score.getTeam(Groups.GERENTE.getOrderSymbol() + Groups.GERENTE.name()).addEntry(player.getName());
        } else if (CustomPlayer.getGroup() == "§cADMIN") {
            score.getTeam(Groups.ADMIN.getOrderSymbol() + Groups.ADMIN.name()).addEntry(player.getName());
        } else if (CustomPlayer.getGroup() == "§2MODERADOR") {
            score.getTeam(Groups.MODERADOR.getOrderSymbol() + Groups.MODERADOR.name()).addEntry(player.getName());
        } else if (CustomPlayer.getGroup() == "§eAJUDANTE") {
            score.getTeam(Groups.AJUDANTE.getOrderSymbol() + Groups.AJUDANTE.name()).addEntry(player.getName());
        } else if (CustomPlayer.getGroup() == "§5WATER") {
            score.getTeam(Groups.WATER.getOrderSymbol() + Groups.WATER.name()).addEntry(player.getName());
        } else if (CustomPlayer.getGroup() == "§2RAIN") {
            score.getTeam(Groups.RAIN.getOrderSymbol() + Groups.RAIN.name()).addEntry(player.getName());
        } else if (CustomPlayer.getGroup() == "§bCLOUD") {
            score.getTeam(Groups.CLOUD.getOrderSymbol() + Groups.CLOUD.name()).addEntry(player.getName());
        } else if (CustomPlayer.getGroup() == "§7MEMBRO") {
            score.getTeam(Groups.MEMBRO.getOrderSymbol() + Groups.MEMBRO.name()).addEntry(player.getName());
        }*/


        player.setScoreboard(score);

}

    public static Scoreboard getScore() {
        return score;
    }
}
