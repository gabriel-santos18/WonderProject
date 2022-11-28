package me.imfighting.bukkit;

import org.bukkit.plugin.java.JavaPlugin;

public final class BukkitMain extends JavaPlugin {

    private static BukkitMain instance;

    @Override
    public void onEnable() {
        instance = this;

    }

    @Override
    public void onDisable() {

    }

    public static BukkitMain getInstance() {
        return instance;
    }
}
