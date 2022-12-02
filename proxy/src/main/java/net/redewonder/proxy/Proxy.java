package net.redewonder.proxy;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.event.EventHandler;
import net.redewonder.proxy.commands.*;
import net.redewonder.proxy.listeners.Listeners;
import net.redewonder.proxy.managers.PlayerManager;
import net.redewonder.proxy.sql.CustomPlayer;
import net.redewonder.proxy.sql.SQLConnection;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public final class Proxy extends Plugin {

    private SQLConnection sqlConnection;
    private PlayerManager playerManager;
    private static Proxy instance;
    private File file;
    public Configuration configuration;

    public int numero;

    @Override
    public void onEnable() {
        instance = this;


        ProxyServer.getInstance().getPluginManager().registerCommand(this, new BanCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new TempbanCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new MuteCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new TempmuteCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new UnmuteCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new UnbanCommand());

        ProxyServer.getInstance().getPluginManager().registerListener(this, new Listeners());

        ProxyServer.getInstance().getScheduler().schedule(this, () -> {

            Random random = new Random();
            numero = random.nextInt(3);

        }, 0, 5, TimeUnit.SECONDS);


        ProxyServer.getInstance().getScheduler().schedule(this, () -> {

            if (numero == 0) {
                ProxyServer.getInstance().broadcast("§6§lREDE WONDER ➨ §6Nos ajude a manter o servidor online! Compre" +
                        " vip em: §bloja.redewonder.net§6.");
            } else if (numero == 1) {
                ProxyServer.getInstance().broadcast("§6§lREDE WONDER ➨ §6Sabia que nós temos um Discord? Entre já e " +
                        "faça novas amizades: §bdiscord.gg/8ZCPPguw5S");
            } else if (numero == 2) {
                ProxyServer.getInstance().broadcast("§6§lREDE WONDER ➨ §6Encontrou jogadores quebrando alguma regra " +
                        "do servidor? Reporte-o utilizando §b/report (nick) (motivo)§6.");
            }

        }, 0, 3, TimeUnit.MINUTES);

        file = new File(ProxyServer.getInstance().getPluginsFolder()+"/Proxy/config.yml");

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

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public static Proxy getInstance() {
        return instance;
    }

    @EventHandler
    public void teste (ChatEvent e) {
    }
}
