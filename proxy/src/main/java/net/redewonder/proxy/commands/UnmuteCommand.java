package net.redewonder.proxy.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.redewonder.proxy.sql.CustomPlayer;

public class UnmuteCommand extends Command {

    public UnmuteCommand() {
        super("unmute");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE")) {
                if (args.length < 1) {
                    player.sendMessage("§cSintaxe incorreta! Digite /unmute (nick)");
                } else if (args.length == 1) {
                    if (CustomPlayer.isMuted(args[0])) {
                        CustomPlayer.deleteMute(args[0]);
                        player.sendMessage("§aO jogador " + args[0] + " foi desmutado com sucesso.");
                    } else {
                        player.sendMessage("§cEste jogador não está mutado.");
                    }
                }
            }
        } else {
            sender.sendMessage("§cApenas jogadores.");
        }
    }
}
