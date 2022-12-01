package net.redewonder.proxy.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.redewonder.proxy.sql.CustomPlayer;

public class BanCommand extends Command {

    public BanCommand() {
        super("ban");
    }

    // /ban nick motivo


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if (args.length < 2) {
                player.sendMessage("§cSintaxe incorreta! Digite /ban (nick) (motivo)");
            } else if (args.length == 2) {
                if (CustomPlayer.playerExists(args[0])) {
                    CustomPlayer.setBanned(args[0]);
                    ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
                    target.disconnect("§c§lREDE WONDER\n\n§cVocê foi banido do servidor!\n§cMotivo: " + args[1] +
                            "\n§cCompre unban em: loja.redewonder.net\n\n§cRede Wonder © Todos os direitos reservados" +
                            ".");
                } else {
                    player.sendMessage("§cEste jogador não existe.");
                }
            }

        }
    }
}
