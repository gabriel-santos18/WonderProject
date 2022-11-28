package me.imfighting.bukkit;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import me.imfighting.bukkit.server.ServerConnectServer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scoreboard.ScoreboardManager;

public final class BukkitMain extends JavaPlugin {

    private static BukkitMain instance;

    @Override
    public void onEnable() {
        instance = this;

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new ServerConnectServer());

    }

    @Override
    public void onDisable() {

    }

    public static BukkitMain getInstance() {
        return instance;
    }
}
