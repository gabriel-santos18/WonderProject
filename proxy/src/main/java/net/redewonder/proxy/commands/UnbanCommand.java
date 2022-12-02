package net.redewonder.proxy.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.redewonder.proxy.sql.CustomPlayer;

public class UnbanCommand extends Command {

    public UnbanCommand() {
        super("unban");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE")) {
                if (args.length < 1) {
                    player.sendMessage("§cSintaxe incorreta! Digite /unban (nick)");
                } else if (args.length == 1) {
                    if (CustomPlayer.isBanned(args[0])) {
                        CustomPlayer.deleteBan(args[0]);
                        player.sendMessage("§aO jogador " + args[0] + " foi desbanido com sucesso.");
                    } else {
                        player.sendMessage("§cEste jogador não está banido.");
                    }
                }
            }
        } else {
            sender.sendMessage("§cApenas jogadores.");
        }
    }
}
