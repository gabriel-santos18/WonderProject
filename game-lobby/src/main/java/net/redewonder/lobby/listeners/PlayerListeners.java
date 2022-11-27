package net.redewonder.lobby.listeners;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import net.redewonder.lobby.Lobby;
import net.redewonder.lobby.api.InventoryBuilder;
import net.redewonder.lobby.api.ItemBuilder;
import net.redewonder.lobby.group.Groups;
import net.redewonder.lobby.managers.*;
import net.redewonder.lobby.server.ServerConnectServer;
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
import org.bukkit.event.player.*;
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

        for (Groups groups : Groups.values()) {
            Team team = ScoreboardManager.score.registerNewTeam(groups.getOrderSymbol() + groups.name());
            team.setPrefix(ChatColor.translateAlternateColorCodes('&', groups.getDisplay()));
        }

        Bukkit.getScheduler().runTaskTimer(Lobby.getInstance(), new Runnable() {
            @Override
            public void run() {
                for (Player online : Bukkit.getOnlinePlayers()) {
                    if (CustomPlayer.getNametag(online).equalsIgnoreCase("§6MASTER")) {
                        player.getScoreboard().getTeam(Groups.MASTER.getOrderSymbol() + Groups.MASTER.name()).addEntry(online.getName());
                    } else if (CustomPlayer.getNametag(online).equalsIgnoreCase("§3GERENTE")) {
                        player.getScoreboard().getTeam(Groups.GERENTE.getOrderSymbol() + Groups.GERENTE.name()).addEntry(online.getName());
                    } else if (CustomPlayer.getNametag(online).equalsIgnoreCase("§cADMIN")) {
                        player.getScoreboard().getTeam(Groups.ADMIN.getOrderSymbol() + Groups.ADMIN.name()).addEntry(online.getName());
                    } else if (CustomPlayer.getNametag(online).equalsIgnoreCase("§2MODERADOR")) {
                        player.getScoreboard().getTeam(Groups.MODERADOR.getOrderSymbol() + Groups.MODERADOR.name()).addEntry(online.getName());
                    } else if (CustomPlayer.getNametag(online).equalsIgnoreCase("§eAJUDANTE")) {
                        player.getScoreboard().getTeam(Groups.AJUDANTE.getOrderSymbol() + Groups.AJUDANTE.name()).addEntry(online.getName());
                    } else if (CustomPlayer.getNametag(online).equalsIgnoreCase("§5WATER")) {
                        player.getScoreboard().getTeam(Groups.WATER.getOrderSymbol() + Groups.WATER.name()).addEntry(online.getName());
                    } else if (CustomPlayer.getNametag(online).equalsIgnoreCase("§2RAIN")) {
                        player.getScoreboard().getTeam(Groups.RAIN.getOrderSymbol() + Groups.RAIN.name()).addEntry(online.getName());
                    } else if (CustomPlayer.getNametag(online).equalsIgnoreCase("§bCLOUD")) {
                        player.getScoreboard().getTeam(Groups.CLOUD.getOrderSymbol() + Groups.CLOUD.name()).addEntry(online.getName());
                    } else {
                        player.getScoreboard().getTeam(Groups.MEMBRO.getOrderSymbol() + Groups.MEMBRO.name()).addEntry(online.getName());
                    }
                }
            }
        }, 0, 20L);


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
        CustomPlayer.setLastLogin(formatter.format(date), player);

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        lobby.getPlayerManager().removeCustomPlayer(e.getPlayer().getUniqueId());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        CustomPlayer.setLastLogin(formatter.format(date), e.getPlayer());
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
        Player player = (Player) e.getWhoClicked();

        if (e.getInventory().getTitle().equalsIgnoreCase("§8Servidores")) {
            if (e.getCurrentItem().getType().equals(Material.DARK_OAK_DOOR_ITEM)) {
                player.closeInventory();
                ServerConnectServer.connect(player, "lobby");
            } else if (e.getCurrentItem().getType().equals(Material.DIAMOND_PICKAXE)) {
                player.closeInventory();
                ServerConnectServer.connect(player, "rankup");
            }
        }

    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Action action = e.getAction();
        Player player = e.getPlayer();
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            if (e.getItem().getType().equals(Material.SKULL_ITEM)) {
                Inventory inventory = new InventoryBuilder(3, "§8Meu perfil").toInventory();
                inventory.setItem(11, new ItemBuilder(Material.SKULL_ITEM).setPlayerSkull(player.getName()).setDisplayName("§aInformações pessoais").setLore("§fRank: " + CustomPlayer.getGroup(player), "§fCash: " + "§b" + CustomPlayer.getCash(player.getUniqueId()), "", "§fCadastrado: §7" + CustomPlayer.getFirstLogin(player.getUniqueId()), "§fÚltimo acesso: §7" + CustomPlayer.getLastLogin(player.getUniqueId())).toItemStack());

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
            } else if (e.getItem().getType().equals(Material.NETHER_STAR)) {
                Inventory inventory = new InventoryBuilder(3, "§8Lobbies").toInventory();
                inventory.setItem(10, new ItemBuilder(Material.INK_SACK).setDurability(Material.INK_SACK, 10).setDisplayName("§eLobby #1").setLore("§7Jogadores: §b" + ServerOnlineCount.LOBBY_ONLINE_COUNT).toItemStack());
                player.openInventory(inventory);
            }
        }
    }

    @EventHandler
    public void onPlayerChatEvent(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (CustomPlayer.getNametag(player).equalsIgnoreCase("§6MASTER")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.MASTER.getDisplay()) + player.getName() + ": §f" + e.getMessage());
        } else if (CustomPlayer.getNametag(player).equalsIgnoreCase("§3GERENTE")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.GERENTE.getDisplay()) + player.getName() + ": §f" + e.getMessage());
        } else if (CustomPlayer.getNametag(player).equalsIgnoreCase("§cADMIN")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.ADMIN.getDisplay()) + player.getName() + ":" + " §f" + e.getMessage());
        } else if (CustomPlayer.getNametag(player).equalsIgnoreCase("§2MODERADOR")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.MODERADOR.getDisplay()) + player.getName() + ": " + "§f" + e.getMessage());
        } else if (CustomPlayer.getNametag(player).equalsIgnoreCase("§eAJUDANTE")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.AJUDANTE.getDisplay() + player.getName() + ": §f" + e.getMessage()));
        } else if (CustomPlayer.getNametag(player).equalsIgnoreCase("§5WATER")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.WATER.getDisplay() + player.getName() + ":" + " §f" + e.getMessage()));
        } else if (CustomPlayer.getNametag(player).equalsIgnoreCase("§2RAIN")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.RAIN.getDisplay() + player.getName() + ": " + "§f" + e.getMessage()));
        } else if (CustomPlayer.getNametag(player).equalsIgnoreCase("§bCLOUD")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.CLOUD.getDisplay() + player.getName() + ":" + " §f" + e.getMessage()));
        } else {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.MEMBRO.getDisplay() + player.getName() + ": " + "§7" + e.getMessage()));
        }
    }

}
