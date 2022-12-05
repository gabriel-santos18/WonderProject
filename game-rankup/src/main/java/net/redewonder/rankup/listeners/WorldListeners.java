package net.redewonder.rankup.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WorldListeners implements Listener {

    @EventHandler
    public void onWorldBlockPlace(BlockPlaceEvent e) {
        if (!e.getPlayer().isOp()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onWorldBlockBreak(BlockBreakEvent e) {
        if (!e.getPlayer().isOp()) {
            e.setCancelled(true);
        }    }

    @EventHandler
    public void onWorldWeatherChange (WeatherChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onWorldCreatureSpawn(CreatureSpawnEvent e) {
        e.setCancelled(true);
    }
}
