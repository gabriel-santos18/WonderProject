package net.redewonder.lobby.commands;

import me.imfighting.bukkit.managers.CommandManager;
import net.redewonder.lobby.sql.CustomPlayer;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class GamemodeCommand extends CommandManager {
    public GamemodeCommand() {
        super("gamemode", new String[]{"gm"});
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER") || CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE") || CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN") || CustomPlayer.getGroup(player).equalsIgnoreCase("§2MODERADOR")) {
                if (args.length < 1) {
                    player.sendMessage("§cSintaxe incorreta! Digite /gamemode <s, c, a>");
                    player.sendMessage("§c  * s = Survival");
                    player.sendMessage("§c  * c = Criativo");
                    player.sendMessage("§c  * a = Aventura");
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("0")) {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage("§aSeu gamemode foi alterado para §eSURVIVAL§a.");
                    } else if (args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("1")) {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage("§aSeu gamemode foi alterado para §eCRIATIVO§a.");
                    } else if (args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("2")) {
                        player.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage("§aSeu gamemode foi alterado para §eAVENTURA§a.");
                    }
                }
            }
        } else {
            sender.sendMessage("§cApenas jogadores.");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
