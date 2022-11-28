package net.wonder.login.listeners;

import net.wonder.login.Login;
import net.wonder.login.managers.PlayerManager;
import net.wonder.login.sql.CustomPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;

import java.sql.SQLException;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        PlayerManager.updatePlayer(e.getPlayer());

        try {
            CustomPlayer playerData = new CustomPlayer(Login.getInstance(), e.getPlayer().getUniqueId(), e.getPlayer());
            Login.getInstance().getPlayerManager().addCustomPlayer(e.getPlayer().getUniqueId(), playerData);
        } catch (SQLException ex) {
            e.getPlayer().kickPlayer("Sua data não foi carregado.");
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }

    @EventHandler
    public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent e) {
        if (!e.getMessage().contains("/login") && !e.getMessage().contains("/registrar")) {
            e.getPlayer().sendMessage("§c§lLOGIN! §cVocê não pode falar no servidor de autenticação.");
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerChatEvent(AsyncPlayerChatEvent e) {
        e.getPlayer().sendMessage("§c§lLOGIN! §cVocê não pode falar no servidor de autenticação.");
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDamageByEntity(EntityDamageByEntityEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerFoodLevelChange(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }

}
