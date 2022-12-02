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
            if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER") || CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE") || CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN") || CustomPlayer.getGroup(player).equalsIgnoreCase("§2MODERADOR")) {
                if (args.length < 2) {
                    player.sendMessage("§cSintaxe incorreta! Digite /ban (nick) (motivo)");
                } else if (args.length >= 2) {
                    if (CustomPlayer.playerExists(args[0])) {
                        //if (!args[0].equalsIgnoreCase(player.getName())) {
                            if (!CustomPlayer.isBanned(args[0])) {

                                StringBuilder builder = new StringBuilder();
                                for (int i = 1; i < args.length; i++) {
                                    builder.append(args[i] + " ");
                                }

                                CustomPlayer.setBan(args[0], player.getName(), String.valueOf(builder), 0, "-");
                                ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
                                player.sendMessage("§aVocê baniu com sucesso " + args[0] + " permanentemente pelo " +
                                        "motivo: " + builder);
                                if (target != null) {
                                    target.disconnect("§c§lREDE WONDER\n\n§cVocê foi banido do servidor!\n§cMotivo: " + builder + "\n§cCompre unban em: loja.redewonder.net\n\n§cRede Wonder © Todos os direitos reservados" + ".");
                                }
                            } else {
                                player.sendMessage("§cEste jogador já está banido.");
                            }
                        //} else {
                            //player.sendMessage("§cVocê não pode banir você mesmo.");
                        //}
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
