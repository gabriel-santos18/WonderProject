package net.redewonder.rankup.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreboardManager {

    public static Scoreboard score;

    public static void updateScore(Player player) {

        score = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = score.registerNewObjective("Test", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§e§lATLANTIC RANKUP");

        Score space = obj.getScore("§a");
        space.setScore(7);

        Team rank = score.registerNewTeam("rank");
        rank.addEntry("§f");
        rank.setPrefix(" §fRank: ");
        rank.setSuffix("§e[...]");
        obj.getScore("§f").setScore(6);

        Team cla = score.registerNewTeam("cla");
        cla.addEntry("§2");
        cla.setPrefix(" §fClã: ");
        cla.setSuffix("§b");
        obj.getScore("§2").setScore(5);

        Score space2 = obj.getScore("§b");
        space2.setScore(4);

        Team money = score.registerNewTeam("money");
        money.addEntry("§3");
        money.setPrefix(" §fDinheiro: ");
        money.setSuffix("§a$...");
        obj.getScore("§3").setScore(3);

        Score space3 = obj.getScore("§c");
        space3.setScore(2);

        Score website = obj.getScore(" §eloja.redewonder.net");
        website.setScore(1);

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
