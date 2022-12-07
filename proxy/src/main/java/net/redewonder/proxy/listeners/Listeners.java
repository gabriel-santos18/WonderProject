package net.redewonder.proxy.listeners;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.protocol.packet.Login;
import net.redewonder.proxy.Proxy;
import net.redewonder.proxy.commands.TempbanCommand;
import net.redewonder.proxy.commands.TempmuteCommand;
import net.redewonder.proxy.sql.CustomPlayer;
import net.redewonder.proxy.sql.SQLConnection;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class Listeners implements Listener {

    @EventHandler
    public void onJoin(PostLoginEvent e) {
        ProxiedPlayer player = e.getPlayer();

        if (!SQLConnection.isConnected()) {
            player.disconnect("§c§lREDE WONDER \n\n §cErro no banco de dados!");
        }

        ProxyServer.getInstance().getScheduler().schedule(Proxy.getInstance(), () -> {

            if (!TempmuteCommand.getCooldown().asMap().containsKey(player.getName())) {
                CustomPlayer.deleteMute(player.getName());
            }

        }, 0, 5, TimeUnit.SECONDS);

        ProxyServer.getInstance().getScheduler().schedule(Proxy.getInstance(), () -> {

            if (!TempbanCommand.getCooldown().asMap().containsKey(player.getName())) {
                CustomPlayer.deleteBan(player.getName());
            }

        }, 0, 5, TimeUnit.SECONDS);

        if (CustomPlayer.isBanned(player.getName())) {
            if (CustomPlayer.getPermBan(player)) {
                player.disconnect("§c§lREDE WONDER\n\n§cVocê foi banido do servidor!\n§cMotivo: " + CustomPlayer.getReason(player) +
                        "\n§cCompre unban em: loja.redewonder.net\n\n§cRede Wonder © Todos os direitos reservados" +
                        ".");
            } else {
                player.disconnect("§c§lREDE WONDER\n\n§cVocê foi banido temporariamente do servidor!\n§cMotivo: " + CustomPlayer.getReason(player) + "\nExpira em: " + TempbanCommand.getFormatter().format(TempbanCommand.getGc().getTime()) +
                        "\n§cCompre unban em: loja.redewonder.net\n\n§cRede Wonder © Todos os direitos reservados" +
                        ".");
            }
        }
    }

    @EventHandler
    public void onCommand(ChatEvent e) {

        ProxiedPlayer player = (ProxiedPlayer) e.getSender();

        if (!CustomPlayer.getPermMute(player)) {
            if (CustomPlayer.isMuted(player.getName())) {
                if (!e.getMessage().contains("/")) {
                    ((ProxiedPlayer) e.getSender()).sendMessage("§cVocê está mutado temporariamente do servidor. Expira " + "em: " + TempmuteCommand.getFormatter().format(TempmuteCommand.getGc().getTime()) + " Para " + "mais " + "informações: §bdiscord.gg/8ZCPPguw5S§c.");
                    e.setCancelled(true);
                }
            }
        } else {
            if (CustomPlayer.isMuted(player.getName())) {
                if (!e.getMessage().contains("/")) {
                    ((ProxiedPlayer) e.getSender()).sendMessage("§cVocê está mutado permanentemente do servidor. Para mais " + "informações: §bdiscord.gg/8ZCPPguw5S§c.");
                    e.setCancelled(true);
                }
            }
        }

        if (player.getServer().getInfo().getName().equalsIgnoreCase("login")) {
            if (!e.getMessage().contains("/login") && !e.getMessage().contains("/registrar")) {
                e.setCancelled(true);
            }
        }
    }
}
