package net.wonder.login.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardManager {

    public static void updateScore(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§e§lWONDER LOGIN");

        Score loginLobby = objective.getScore("                 §fLogin: §e#1");
        loginLobby.setScore(9);

        Score space3 = objective.getScore("§c");
        space3.setScore(8);

        Score login = objective.getScore(" §fPor favor, entre:");
        login.setScore(7);

        Score comandoLogin = objective.getScore(" §7/logar (senha)");
        comandoLogin.setScore(6);

        Score space2 = objective.getScore("§b");
        space2.setScore(5);

        Score register = objective.getScore(" §fPor favor, registre-se:");
        register.setScore(4);

        Score comandoRegister = objective.getScore(" §7/registrar (senha) (senha)");
        comandoRegister.setScore(3);

        Score space1 = objective.getScore("§a");
        space1.setScore(2);

        Score website = objective.getScore("     §eloja.redewonder.net");
        website.setScore(1);

        player.setScoreboard(scoreboard);

    }
}
