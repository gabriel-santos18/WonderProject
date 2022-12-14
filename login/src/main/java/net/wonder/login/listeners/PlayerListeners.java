package net.wonder.login.listeners;

import com.google.gson.stream.JsonReader;
import me.imfighting.bukkit.api.TitleAPI;
import net.wonder.login.Login;
import net.wonder.login.managers.PlayerManager;
import net.wonder.login.server.ServerConnectServer;
import net.wonder.login.sql.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;
import org.enginehub.squirrelid.Profile;
import org.enginehub.squirrelid.resolver.HttpRepositoryService;
import org.enginehub.squirrelid.resolver.ProfileService;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

public class PlayerListeners implements Listener {

    boolean premium = false;



    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        PlayerManager.updatePlayer(e.getPlayer());

        try {

            String name = e.getPlayer().getName();

            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                Bukkit.getScheduler().runTaskLater(Login.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        ServerConnectServer.connect(e.getPlayer(), "lobby");
                    }
                }, 1L);

                new TitleAPI("§a§lAUTENTICADO", "§aVocê é um jogador original.").sendToPlayer(e.getPlayer());
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

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
