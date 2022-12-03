package net.redewonder.rankup;

import net.redewonder.rankup.listeners.PlayerListeners;
import net.redewonder.rankup.listeners.WorldListeners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Rankup extends JavaPlugin {

    private static Rankup instance;
    public File file;


    @Override
    public void onEnable() {
        instance = this;

        Bukkit.getPluginManager().registerEvents(new PlayerListeners(), this);
        Bukkit.getPluginManager().registerEvents(new WorldListeners(), this);

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



    }

    @Override
    public void onDisable() {

    }

    public static Rankup getInstance() {
        return instance;
    }
}
