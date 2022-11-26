package net.redewonder.lobby;

import com.google.common.collect.Lists;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.redewonder.lobby.commands.GroupCommand;
import net.redewonder.lobby.commands.SetSpawnCommand;
import net.redewonder.lobby.listeners.PlayerListeners;
import net.redewonder.lobby.listeners.WorldListeners;
import net.redewonder.lobby.managers.PlayerManager;
import net.redewonder.lobby.managers.ScoreboardManager;
import net.redewonder.lobby.server.ServerOnlineCount;
import net.redewonder.lobby.sql.SQLConnection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public final class Lobby extends JavaPlugin implements PluginMessageListener {

    private static Lobby instance;
    private SQLConnection sqlConnection;
    public File file;
    private Connection connection;
    private PlayerManager playerManager;


    @Override
    public void onEnable() {
        instance = this;

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        playerManager = new PlayerManager();

        Bukkit.getPluginManager().registerEvents(new PlayerListeners(this), this);
        Bukkit.getPluginManager().registerEvents(new WorldListeners(), this);

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        new SetSpawnCommand();
        new GroupCommand(this);

        file = new File(Lobby.getInstance().getDataFolder(), "locations.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Não foi possível carregar!");
                return;
            }
        }


        List<String> servers = Lists.newArrayListWithExpectedSize(2);
        servers.add("ALL");
        servers.add("Rankup");

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (String serverName : servers) {
                ByteArrayDataOutput output = ByteStreams.newDataOutput();
                output.writeUTF("PlayerCount");
                output.writeUTF(serverName);

                getServer().sendPluginMessage(Lobby.getInstance(), "BungeeCord", output.toByteArray());

                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.getScoreboard().getTeam("onlines").setSuffix("§b" + ServerOnlineCount.NETWORK_ONLINE_COUNT);
                }
            }
        }, 40L, 40L);



        sqlConnection = new SQLConnection();
        try {
            sqlConnection.connect();
            Bukkit.getConsoleSender().sendMessage("§aMySQL conectado!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDisable() {
        sqlConnection.disconnect();
    }


    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        if (!channel.equals("BungeeCord")) {
            return;
        }

        ByteArrayDataInput input = ByteStreams.newDataInput(bytes);

        String subChannel = input.readUTF();

        if (subChannel.equals("PlayerCount")) {
            String server = input.readUTF();

            int response = input.readInt();

            switch (server) {
                case "ALL":
                    ServerOnlineCount.NETWORK_ONLINE_COUNT = response;
                    ScoreboardManager.getScore().getTeam("onlines").setSuffix("§b" + ServerOnlineCount.NETWORK_ONLINE_COUNT);
                    break;
                case "Rankup":
                    ServerOnlineCount.RANKUP_ONLINE_COUNT = response;
                    break;
                default:
                    break;
            }
        }
    }


    public static Lobby getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    public SQLConnection getSqlConnection() {
        return sqlConnection;
    }
    public PlayerManager getPlayerManager() {
        return playerManager;
    }
}
