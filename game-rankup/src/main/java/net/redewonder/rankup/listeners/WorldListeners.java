package net.redewonder.rankup.listeners;

import me.imfighting.bukkit.api.ActionBarAPI;
import net.redewonder.rankup.Rankup;
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

        if (player.getWorld().getName().equalsIgnoreCase(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/1")) {
            e.setCancelled(false);
        } else if (player.getWorld().getName().equalsIgnoreCase(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/2")) {
            e.setCancelled(false);
        } else if (player.getWorld().getName().equalsIgnoreCase(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/3")) {
            e.setCancelled(false);
        } else if (player.getWorld().getName().equalsIgnoreCase(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/4")) {
            e.setCancelled(false);
        } else if (player.getWorld().getName().equalsIgnoreCase(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/5")) {
            e.setCancelled(false);
        } else if (player.getWorld().getName().equalsIgnoreCase(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/6")) {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onWorldBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (!player.isOp()) {
            e.setCancelled(true);
        }

        if (player.getWorld().getName().equalsIgnoreCase(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/1")) {
            e.setCancelled(false);
        } else if (player.getWorld().getName().equalsIgnoreCase(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/2")) {
            e.setCancelled(false);
        } else if (player.getWorld().getName().equalsIgnoreCase(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/3")) {
            e.setCancelled(false);
        } else if (player.getWorld().getName().equalsIgnoreCase(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/4")) {
            e.setCancelled(false);
        } else if (player.getWorld().getName().equalsIgnoreCase(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/5")) {
            e.setCancelled(false);
        } else if (player.getWorld().getName().equalsIgnoreCase(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/6")) {
            e.setCancelled(false);
        }

        if (player.getWorld().getName().equalsIgnoreCase("Minas")) {
            if (e.getBlock().getType().equals(Material.LAPIS_ORE)) {
                e.getBlock().setType(Material.AIR);
                e.setCancelled(false);
                new ActionBarAPI("§eMina ➼ §a150 §fcoins").sendToPlayer(player);
                CustomPlayer.setCoins(CustomPlayer.getCoins(player) + 150, player.getName());
                player.getScoreboard().getTeam("money").setSuffix((CustomPlayer.getCoins(player.getName()) > 999 &&
                        CustomPlayer.getCoins(player.getName()) <= 9999 ?
                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 1) + "K" :
                        CustomPlayer.getCoins(player.getName()) > 9999 &&
                                CustomPlayer.getCoins(player.getName()) <= 99999 ?
                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 2) + "K" :
                                CustomPlayer.getCoins(player.getName()) > 99999 &&
                                        CustomPlayer.getCoins(player.getName()) <= 999999 ?
                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 3) + "K" :
                                        CustomPlayer.getCoins(player.getName()) > 999999 &&
                                                CustomPlayer.getCoins(player.getName()) <= 9999999 ?
                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 1) + "M" :
                                                CustomPlayer.getCoins(player.getName()) > 9999999 &&
                                                        CustomPlayer.getCoins(player.getName()) <= 99999999 ?
                                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                2) + "M" :
                                                        CustomPlayer.getCoins(player.getName()) > 99999999 &&
                                                                CustomPlayer.getCoins(player.getName()) <= 999999999 ?
                                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                        3) + "M" :
                                                                CustomPlayer.getCoins(player.getName()) > 999999999 &&
                                                                        CustomPlayer.getCoins(player.getName()) <= 9999999999L ?
                                                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                1) + "B" :
                                                                        "§a$" + CustomPlayer.getCoins(player.getName())));
            }
        }

    }

    public static int PegaPrimirosDigitos(int valor, int digitos) {
        digitos = Math.max(1, digitos);
        int positivo = Math.abs(valor);
        String texto = String.valueOf(positivo);
        if (digitos > texto.length()) {
            return valor;
        }
        return Integer.parseInt(texto.substring(0, digitos)) * Integer.signum(valor);
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
