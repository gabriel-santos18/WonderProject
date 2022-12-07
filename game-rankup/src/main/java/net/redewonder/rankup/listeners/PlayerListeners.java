package net.redewonder.rankup.listeners;

import me.imfighting.bukkit.api.TablistAPI;
import net.redewonder.rankup.Rankup;
import net.redewonder.rankup.group.Groups;
import net.redewonder.rankup.managers.LocationsManager;
import net.redewonder.rankup.managers.PlayerManager;
import net.redewonder.rankup.managers.ScoreboardManager;
import net.redewonder.rankup.sql.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scoreboard.Team;

import java.sql.SQLException;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        PlayerManager.updatePlayer(e.getPlayer());
        Player player = e.getPlayer();
        ScoreboardManager.updateScore(player);
        TablistAPI.sendTablist(e.getPlayer(), "\n§e§lREDE WONDER\n§aloja.redewonder.net\n§fVocê está conectado em: " +
                "§eRankup\n",
                "\n§eDiscord: §fdiscord.gg/8ZCPPguw5S\n§fAdquira §e§lVIP §fou §e§lCASH §facessando: " +
                "§eloja.redewonder.net\n");

        try {
            CustomPlayer playerData = new CustomPlayer(Rankup.getInstance(), e.getPlayer().getUniqueId(), e.getPlayer());
            Rankup.getInstance().getPlayerManager().addCustomPlayer(e.getPlayer().getUniqueId(), playerData);
        } catch (SQLException ex) {
            e.getPlayer().kickPlayer("Sua data não foi carregado.");
            ex.printStackTrace();
        }

        for (Groups groups : Groups.values()) {
            Team team = ScoreboardManager.score.registerNewTeam(groups.getOrderSymbol() + groups.name());
            team.setPrefix(ChatColor.translateAlternateColorCodes('&', groups.getDisplay()));
        }

        Bukkit.getScheduler().runTaskTimer(Rankup.getInstance(), new Runnable() {
            @Override
            public void run() {
                for (Player online : Bukkit.getOnlinePlayers()) {
                    if (CustomPlayer.getNametag(online).equalsIgnoreCase("§6MASTER")) {
                        player.getScoreboard().getTeam(Groups.MASTER.getOrderSymbol() + Groups.MASTER.name()).addEntry(online.getName());
                    } else if (CustomPlayer.getNametag(online).equalsIgnoreCase("§3GERENTE")) {
                        player.getScoreboard().getTeam(Groups.GERENTE.getOrderSymbol() + Groups.GERENTE.name()).addEntry(online.getName());
                    } else if (CustomPlayer.getNametag(online).equalsIgnoreCase("§cADMIN")) {
                        player.getScoreboard().getTeam(Groups.ADMIN.getOrderSymbol() + Groups.ADMIN.name()).addEntry(online.getName());
                    } else if (CustomPlayer.getNametag(online).equalsIgnoreCase("§2MODERADOR")) {
                        player.getScoreboard().getTeam(Groups.MODERADOR.getOrderSymbol() + Groups.MODERADOR.name()).addEntry(online.getName());
                    } else if (CustomPlayer.getNametag(online).equalsIgnoreCase("§eAJUDANTE")) {
                        player.getScoreboard().getTeam(Groups.AJUDANTE.getOrderSymbol() + Groups.AJUDANTE.name()).addEntry(online.getName());
                    } else if (CustomPlayer.getNametag(online).equalsIgnoreCase("§5WATER")) {
                        player.getScoreboard().getTeam(Groups.WATER.getOrderSymbol() + Groups.WATER.name()).addEntry(online.getName());
                    } else if (CustomPlayer.getNametag(online).equalsIgnoreCase("§2RAIN")) {
                        player.getScoreboard().getTeam(Groups.RAIN.getOrderSymbol() + Groups.RAIN.name()).addEntry(online.getName());
                    } else if (CustomPlayer.getNametag(online).equalsIgnoreCase("§bCLOUD")) {
                        player.getScoreboard().getTeam(Groups.CLOUD.getOrderSymbol() + Groups.CLOUD.name()).addEntry(online.getName());
                    } else {
                        player.getScoreboard().getTeam(Groups.MEMBRO.getOrderSymbol() + Groups.MEMBRO.name()).addEntry(online.getName());
                    }
                }
            }
        }, 0, 20L);

    }

    @EventHandler
    public void onPlayerChatEvent(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (CustomPlayer.getNametag(player).equalsIgnoreCase("§6MASTER")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.MASTER.getDisplay()) + player.getName() + ": §f" + e.getMessage());
        } else if (CustomPlayer.getNametag(player).equalsIgnoreCase("§3GERENTE")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.GERENTE.getDisplay()) + player.getName() + ": §f" + e.getMessage());
        } else if (CustomPlayer.getNametag(player).equalsIgnoreCase("§cADMIN")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.ADMIN.getDisplay()) + player.getName() + ":" + " §f" + e.getMessage());
        } else if (CustomPlayer.getNametag(player).equalsIgnoreCase("§2MODERADOR")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.MODERADOR.getDisplay()) + player.getName() + ": " + "§f" + e.getMessage());
        } else if (CustomPlayer.getNametag(player).equalsIgnoreCase("§eAJUDANTE")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.AJUDANTE.getDisplay() + player.getName() + ": §f" + e.getMessage()));
        } else if (CustomPlayer.getNametag(player).equalsIgnoreCase("§5WATER")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.WATER.getDisplay() + player.getName() + ":" + " §f" + e.getMessage()));
        } else if (CustomPlayer.getNametag(player).equalsIgnoreCase("§2RAIN")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.RAIN.getDisplay() + player.getName() + ": " + "§f" + e.getMessage()));
        } else if (CustomPlayer.getNametag(player).equalsIgnoreCase("§bCLOUD")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.CLOUD.getDisplay() + player.getName() + ":" + " §f" + e.getMessage()));
        } else {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.MEMBRO.getDisplay() + player.getName() + ": " + "§7" + e.getMessage()));
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
