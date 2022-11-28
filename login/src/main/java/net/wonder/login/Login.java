package net.wonder.login;

import net.wonder.login.listeners.PlayerListeners;
import net.wonder.login.listeners.WorldListeners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Login extends JavaPlugin {

    private static Login instance;

    @Override
    public void onEnable() {
        instance = this;

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
}
