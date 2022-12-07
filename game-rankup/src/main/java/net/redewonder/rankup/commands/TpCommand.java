package net.redewonder.rankup.commands;

import me.imfighting.bukkit.managers.CommandManager;
import net.redewonder.rankup.sql.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class TpCommand extends CommandManager {


    public TpCommand() {
        super("tp", new String[]{});
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER") || CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE") || CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN") || CustomPlayer.getGroup(player).equalsIgnoreCase("§2MODERADOR")) {
                if (args.length < 1) {
                    player.sendMessage("§cSintaxe incorreta! Utilize /tp <nick>.");
                } else if (args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);

                    if (target == null) {
                        player.sendMessage("§cEste jogador não está online ou não existe.");
                    } else {
                        player.teleport(target.getLocation());
                        player.sendMessage("§aVocê foi teleportado para §e" + target.getName() + "§a.");
                    }
                }
            }
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) {
            List<String> names = new ArrayList<>();
            for (Player player : Bukkit.getOnlinePlayers()) {
                names.add(player.getName());
            }
            return StringUtil.copyPartialMatches(args[0], names, new ArrayList<>());
        }
        return new ArrayList<>();
    }
}
