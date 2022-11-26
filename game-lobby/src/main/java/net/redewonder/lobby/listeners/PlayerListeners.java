package net.redewonder.lobby.listeners;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import net.redewonder.lobby.Lobby;
import net.redewonder.lobby.api.InventoryBuilder;
import net.redewonder.lobby.api.ItemBuilder;
import net.redewonder.lobby.managers.LocationsManager;
import net.redewonder.lobby.managers.PlayerManager;
import net.redewonder.lobby.managers.ScoreboardManager;
import net.redewonder.lobby.managers.TablistManager;
import net.redewonder.lobby.server.ServerOnlineCount;
import net.redewonder.lobby.sql.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PlayerListeners implements Listener {

    private Lobby lobby;
    private boolean visible = true;
    private Cache<UUID, Long> cooldown = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS).build();

    public PlayerListeners(Lobby lobby) {
        this.lobby = lobby;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {


        Player player = e.getPlayer();
        e.setJoinMessage(null);
        PlayerManager.updatePlayer(player);
        ScoreboardManager.updateScore(player);
        TablistManager.updateTablist(player);
        player.teleport(LocationsManager.getSpawn(player));
        try {
            CustomPlayer playerData = new CustomPlayer(lobby, player.getUniqueId(), player);
            lobby.getPlayerManager().addCustomPlayer(player.getUniqueId(), playerData);
        } catch (SQLException ex) {
            player.kickPlayer("Sua data não foi carregado.");
            ex.printStackTrace();
        }


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        CustomPlayer.setLastLogin(formatter.format(date));

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        lobby.getPlayerManager().removeCustomPlayer(e.getPlayer().getUniqueId());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        CustomPlayer.setLastLogin(formatter.format(date));
    }

    @EventHandler
    public void onPlayerDamageByEntity(EntityDamageByEntityEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerFoodLevelChange(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInventoryClick(InventoryClickEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Action action = e.getAction();
        Player player = e.getPlayer();
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            if (e.getItem().getType().equals(Material.SKULL_ITEM)) {
                Inventory inventory = new InventoryBuilder(3, "§8Meu perfil").toInventory();
                inventory.setItem(11, new ItemBuilder(Material.SKULL_ITEM).setPlayerSkull(player.getName()).setDisplayName("§aInformações pessoais").setLore("§fRank: " + CustomPlayer.getGroup(), "§fCash: §b" + CustomPlayer.getCash(), "", "§fCadastrado: §7" + CustomPlayer.getFirstLogin(), "§fÚltimo acesso: §7" + CustomPlayer.getLastLogin()).toItemStack());

                inventory.setItem(15, new ItemBuilder(Material.REDSTONE_COMPARATOR).setDisplayName("§aPreferências").setLore("§7Em nosso servidor você pode personalizar", "§7sua experiência de jogo por completo.", "§7Personalize várias opções únicas como", "§7você desejar!", "", "§8As opções incluem ativar ou desativar as", "§8mensagens privadas, os jogadores e outros.", "", "§eClique para personalizar as opções!").toItemStack());

                player.openInventory(inventory);
            } else if (e.getItem().getType().equals(Material.CHEST)) {
                player.sendMessage("§cEm breve");
            } else if (e.getItem().getType().equals(Material.COMPASS)) {
                Inventory inventory = new InventoryBuilder(3, "§8Servidores").toInventory();
                inventory.setItem(10, new ItemBuilder(Material.DARK_OAK_DOOR_ITEM).setDisplayName("§6Lobby principal").setLore("§eClique para " + "entrar!").toItemStack());
                inventory.setItem(12, new ItemBuilder(Material.DIAMOND_PICKAXE).setDisplayName("§bRankUP Atlântida").setLore("§7O " + "melhor servidor de RankUP!", "", "§eInformações:", "", "§a・ §fEconomia OP", "", "§7→ §aClique para conectar!", "§70 jogando.").toItemStack());
                player.openInventory(inventory);
            } else if (e.getItem().getItemMeta().getDisplayName().equals("§eJogadores: §aON") && (!cooldown.asMap().containsKey(player.getUniqueId()))) {
                player.sendMessage("§cAgora os jogadores estão invisíveis.");
                for (Player online : Bukkit.getOnlinePlayers()) {
                    player.hidePlayer(online);
                }
                player.getInventory().setItem(6, new ItemBuilder(Material.INK_SACK).setDurability(Material.INK_SACK, 8).setDisplayName("§eJogadores: §cOFF").toItemStack());
                if (!cooldown.asMap().containsKey(player.getUniqueId())) {
                    cooldown.put(player.getUniqueId(), System.currentTimeMillis() + 5000);
                }
            } else if (e.getItem().getItemMeta().getDisplayName().equals("§eJogadores: §cOFF") && (!cooldown.asMap().containsKey(player.getUniqueId()))) {
                player.sendMessage("§aAgora os jogadores estão visíveis.");
                player.getInventory().setItem(6, new ItemBuilder(Material.INK_SACK).setDurability(Material.INK_SACK, 10).setDisplayName("§eJogadores: §aON").toItemStack());
                for (Player online : Bukkit.getOnlinePlayers()) {
                    player.showPlayer(online);
                }
                if (!cooldown.asMap().containsKey(player.getUniqueId())) {
                    cooldown.put(player.getUniqueId(), System.currentTimeMillis() + 5000);
                }
            }
        }
    }
}
