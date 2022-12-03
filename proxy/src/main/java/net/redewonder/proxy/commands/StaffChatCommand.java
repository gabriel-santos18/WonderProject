package net.redewonder.proxy.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.redewonder.proxy.sql.CustomPlayer;

public class StaffChatCommand extends Command {

    public StaffChatCommand() {
        super("staffchat", "", "sc");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§2MODERADOR") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§eAJUDANTE")) {
                if (args.length < 1) {
                    player.sendMessage("§cSintaxe incorreta! Digite /sc (mensagem).");
                } else {
                    for (ProxiedPlayer proxiedPlayer : ProxyServer.getInstance().getPlayers()) {
                        if (CustomPlayer.getGroup(proxiedPlayer).equalsIgnoreCase("§6MASTER") ||
                                CustomPlayer.getGroup(proxiedPlayer).equalsIgnoreCase("§3GERENTE") ||
                                CustomPlayer.getGroup(proxiedPlayer).equalsIgnoreCase("§cADMIN") ||
                                CustomPlayer.getGroup(proxiedPlayer).equalsIgnoreCase("§2MODERADOR") ||
                                CustomPlayer.getGroup(proxiedPlayer).equalsIgnoreCase("§eAJUDANTE")) {

                            StringBuilder builder = new StringBuilder();
                            for (int i = 0; i < args.length; i++) {
                                builder.append(args[i] + " ");
                            }

                            proxiedPlayer.sendMessage("§e[STAFF] §b" + player.getName() + "§e: §f" + builder);
                        }
                    }
                }
            }
        }
    }
}
