package net.redewonder.rankup;

import net.redewonder.rankup.commands.SetSpawnCommand;
import net.redewonder.rankup.commands.TpWorldCommand;
import net.redewonder.rankup.listeners.PlayerListeners;
import net.redewonder.rankup.listeners.WorldListeners;
import net.redewonder.rankup.managers.PlayerManager;
import net.redewonder.rankup.managers.WorldManager;
import net.redewonder.rankup.sql.SQLConnection;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public final class Rankup extends JavaPlugin {

    private static Rankup instance;
    public File file;

    private SQLConnection sqlConnection;

    private PlayerManager playerManager;



    @Override
    public void onEnable() {
        instance = this;

        Bukkit.getPluginManager().registerEvents(new PlayerListeners(), this);
        Bukkit.getPluginManager().registerEvents(new WorldListeners(), this);

        if (Bukkit.getWorld("Minas") == null) {
            WorldCreator wc = new WorldCreator("Minas");
            wc.generator(new WorldManager());
            wc.createWorld();
        }




        new SetSpawnCommand();
        new TpWorldCommand();

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        file = new File(Rankup.getInstance().getDataFolder(), "locations.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Não foi possível carregar!");
                return;
            }
        }

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

    }

    public static Rankup getInstance() {
        return instance;
    }

    public SQLConnection getSqlConnection() {
        return sqlConnection;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }
}
