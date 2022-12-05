package net.redewonder.rankup.listeners;

import net.redewonder.rankup.Rankup;
import net.redewonder.rankup.managers.LocationsManager;
import net.redewonder.rankup.managers.PlayerManager;
import net.redewonder.rankup.sql.CustomPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.sql.SQLException;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        PlayerManager.updatePlayer(e.getPlayer());

        try {
            CustomPlayer playerData = new CustomPlayer(Rankup.getInstance(), e.getPlayer().getUniqueId(), e.getPlayer());
            Rankup.getInstance().getPlayerManager().addCustomPlayer(e.getPlayer().getUniqueId(), playerData);
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
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        e.getPlayer().teleport(LocationsManager.getLocation(e.getPlayer(), "Spawn"));
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        e.setDeathMessage(null);
    }
}
