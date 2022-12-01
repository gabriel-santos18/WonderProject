package net.redewonder.proxy.listeners;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.protocol.packet.Login;
import net.redewonder.proxy.Proxy;
import net.redewonder.proxy.sql.CustomPlayer;

import java.sql.SQLException;

public class Listeners implements Listener {

    @EventHandler
    public void onJoin(PostLoginEvent e) {
        ProxiedPlayer player = e.getPlayer();

        if (CustomPlayer.isBanned(player)) {
            player.disconnect("§c§lREDE WONDER\n\n§cVocê foi banido do servidor!\n§cMotivo: " + CustomPlayer.getReason(player) +
                    "\n§cCompre unban em: loja.redewonder.net\n\n§cRede Wonder © Todos os direitos reservados" +
                    ".");
        }

        try {
            CustomPlayer playerData = new CustomPlayer(Proxy.getInstance(), e.getPlayer().getUniqueId(), e.getPlayer());
            Proxy.getInstance().getPlayerManager().addCustomPlayer(e.getPlayer().getUniqueId(), playerData);
        } catch (SQLException ex) {
            e.getPlayer().disconnect("Sua data não foi carregado.");
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void onCommand(ChatEvent e) {
        if (e.getMessage().contains("/server")) {
            e.setCancelled(true);
        }
    }
}
