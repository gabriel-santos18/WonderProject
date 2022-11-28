package net.redewonder.lobby.managers;

import me.imfighting.bukkit.inventory.ItemBuilder;
import net.redewonder.lobby.Lobby;
import net.redewonder.lobby.sql.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {

    private HashMap<UUID, CustomPlayer> customPlayers = new HashMap<UUID, CustomPlayer>();

    public CustomPlayer getCustomPlayer(UUID uuid) {
        return customPlayers.get(uuid);
    }

    public void addCustomPlayer(UUID uuid, CustomPlayer player) {
        customPlayers.put(uuid, player);
    }

    public void removeCustomPlayer(UUID uuid) {
        customPlayers.remove(uuid);
    }

    public static void updatePlayer(Player player) {
        player.getInventory().clear();
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setGameMode(GameMode.SURVIVAL);

        Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER") ||
                        CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE") ||
                        CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN") ||
                        CustomPlayer.getGroup(player).equalsIgnoreCase("§2MODERADOR") ||
                        CustomPlayer.getGroup(player).equalsIgnoreCase("§eAJUDANTE") ||
                        CustomPlayer.getGroup(player).equalsIgnoreCase("§5WATER") ||
                        CustomPlayer.getGroup(player).equalsIgnoreCase("§2RAIN") ||
                        CustomPlayer.getGroup(player).equalsIgnoreCase("§bCLOUD")) {
                    player.setAllowFlight(true);
                }
            }
        }, 20L);

        player.getInventory().setItem(1,
                new ItemBuilder(Material.SKULL_ITEM).setPlayerSkull(player.getName()).setDisplayName("§ePerfil §7" +
                        "(Clique direito)").toItemStack());
        player.getInventory().setItem(2,
                new ItemBuilder(Material.CHEST).setDisplayName("§eCosméticos §7" +
                        "(Clique direito)").toItemStack());

        player.getInventory().setItem(4,
                new ItemBuilder(Material.COMPASS).setDisplayName("§eServidores §7" +
                        "(Clique direito)").toItemStack());

        player.getInventory().setItem(6,
                new ItemBuilder(Material.INK_SACK).setDurability(Material.INK_SACK, 10).setDisplayName("§eJogadores: " +
                        "§aON").toItemStack());
        player.getInventory().setItem(7,
                new ItemBuilder(Material.NETHER_STAR).setDisplayName("§eLobbies §7(Clique direito)").toItemStack());
    }
}
