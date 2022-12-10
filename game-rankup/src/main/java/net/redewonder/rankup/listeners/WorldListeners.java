package net.redewonder.rankup.listeners;

import me.imfighting.bukkit.api.ActionBarAPI;
import net.redewonder.rankup.sql.CustomPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WorldListeners implements Listener {

    @EventHandler
    public void onWorldBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if (!player.isOp()) {
            e.setCancelled(true);

        }
    }

    @EventHandler
    public void onWorldBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (!player.isOp()) {
            e.setCancelled(true);
        }

        if (player.getWorld().getName().equalsIgnoreCase("Minas")) {
            if (e.getBlock().getType().equals(Material.LAPIS_ORE)) {
                e.getBlock().setType(Material.AIR);
                e.setCancelled(false);
                new ActionBarAPI("§eMina ➼ §a150 §fcoins").sendToPlayer(player);
                CustomPlayer.setCoins(CustomPlayer.getCoins(player) + 150, player.getName());
                player.getScoreboard().getTeam("money").setSuffix("§a$" + CustomPlayer.getCoins(player.getName()));
            }
        }
    }

    @EventHandler
    public void onWorldWeatherChange(WeatherChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onWorldCreatureSpawn(CreatureSpawnEvent e) {
        e.setCancelled(true);
    }
}
