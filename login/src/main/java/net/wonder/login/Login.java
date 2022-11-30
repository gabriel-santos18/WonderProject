package net.wonder.login;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import net.redewonder.lobby.commands.SetSpawnCommand;
import net.wonder.login.commands.LoginCommand;
import net.wonder.login.commands.RegistrarCommand;
import net.wonder.login.listeners.PlayerListeners;
import net.wonder.login.listeners.WorldListeners;
import net.wonder.login.managers.PlayerManager;
import net.wonder.login.sql.SQLConnection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public final class Login extends JavaPlugin implements PluginMessageListener {

    private static Login instance;
    private SQLConnection sqlConnection;
    public File file;

    private PlayerManager playerManager;

    @Override
    public void onEnable() {
        instance = this;

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        new LoginCommand();
        new RegistrarCommand();
        new SetSpawnCommand();

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        playerManager = new PlayerManager();

        sqlConnection = new SQLConnection();
        try {
            sqlConnection.connect();
            Bukkit.getConsoleSender().sendMessage("§aMySQL conectado!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        file = new File(Login.getInstance().getDataFolder(), "locations.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Não foi possível carregar!");
                return;
            }
        }

        Bukkit.getPluginManager().registerEvents(new PlayerListeners(), this);
        Bukkit.getPluginManager().registerEvents(new WorldListeners(), this);

        Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                Bukkit.getWorld("world").setTime(0);
            }
        }, 0, 20L);
    }

    @Override
    public void onDisable() {

    }

    public static Login getInstance() {
        return instance;
    }

    public SQLConnection getSqlConnection() {
        return sqlConnection;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    @Override
    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {
        if (!s.equals("BungeeCord")) {
            return;
        }

        ByteArrayDataInput input = ByteStreams.newDataInput(bytes);

        String subChannel = input.readUTF();
    }
}
