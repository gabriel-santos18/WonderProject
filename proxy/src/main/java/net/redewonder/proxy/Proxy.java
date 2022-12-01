package net.redewonder.proxy;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.event.EventHandler;
import net.redewonder.proxy.commands.BanCommand;
import net.redewonder.proxy.sql.SQLConnection;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public final class Proxy extends Plugin {

    private SQLConnection sqlConnection;
    private static Proxy instance;
    private File file;
    public Configuration configuration;

    @Override
    public void onEnable() {
        instance = this;

        ProxyServer.getInstance().getPluginManager().registerCommand(this, new BanCommand());

        file = new File(ProxyServer.getInstance().getPluginsFolder()+"/config.yml");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sqlConnection = new SQLConnection();
        try {
            sqlConnection.connect();
            getLogger().info("§aMySQL conectado!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        sqlConnection.disconnect();
    }

    public SQLConnection getSqlConnection() {
        return sqlConnection;
    }

    public static Proxy getInstance() {
        return instance;
    }

    @EventHandler
    public void teste (ChatEvent e) {
    }
}
