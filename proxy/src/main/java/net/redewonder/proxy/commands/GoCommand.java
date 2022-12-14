package net.redewonder.proxy.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import net.redewonder.proxy.sql.CustomPlayer;

import java.util.ArrayList;
import java.util.List;

public class GoCommand extends Command implements TabExecutor {

    public GoCommand() {
        super("go");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§2MODERADOR")) {
                if (args.length < 1) {
                    player.sendMessage("§cSintaxe incorreta! Digite /go (nick)");
                } else if (args.length == 1) {
                    ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
                    if (target != null) {
                        player.connect(target.getServer().getInfo());
                        player.sendMessage("§aVocê teleportou até o servidor do jogador " + target.getName() + ".");
                    } else {
                        player.sendMessage("§cEste jogador não está online.");
                    }
                } else {
                    player.sendMessage("§cEste jogador não está online!");
                }
            } else {
                player.sendMessage("§cVocê não possui permissão.");
            }
        }
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        List<String> results = new ArrayList<>();

        if (args.length == 1) {
            for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                results.add(player.getName());
            }
        }

        return results;
    }
}
