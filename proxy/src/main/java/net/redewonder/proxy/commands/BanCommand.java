package net.redewonder.proxy.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import net.redewonder.proxy.Proxy;
import net.redewonder.proxy.sql.CustomPlayer;

import java.util.ArrayList;
import java.util.List;

public class BanCommand extends Command implements TabExecutor {

    public BanCommand() {
        super("ban");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§2MODERADOR")) {
                if (args.length < 2) {
                    player.sendMessage("§cSintaxe incorreta! Digite /ban (nick) (motivo)");
                } else if (args.length == 2) {
                    if (CustomPlayer.playerExists(args[0])) {
                        CustomPlayer.setBanned(args[0]);
                        CustomPlayer.setReason(args[0], args[1]);
                        CustomPlayer.setAuthor(args[0], player.getName());
                        ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
                        player.sendMessage("§aVocê baniu com sucesso " + args[0] + " pelo motivo: " + args[1]);
                        if (target != null) {
                            target.disconnect("§c§lREDE WONDER\n\n§cVocê foi banido do servidor!\n§cMotivo: " + args[1] + "\n§cCompre unban em: loja.redewonder.net\n\n§cRede Wonder © Todos os direitos reservados" + ".");
                        }
                    } else {
                        player.sendMessage("§cEste jogador não existe.");
                    }
                }
            } else {
                player.sendMessage("§cVocê não tem permissão.");
            }
        }
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        List<String> results = new ArrayList<>();

        if (args.length == 1) {
            for (ProxiedPlayer proxiedPlayer : Proxy.getInstance().getProxy().getPlayers()) {
                results.add(proxiedPlayer.getName());
            }
        }
        return results;
    }
}
