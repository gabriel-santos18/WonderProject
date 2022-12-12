package net.redewonder.rankup.listeners;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import me.imfighting.bukkit.api.TablistAPI;
import me.imfighting.bukkit.inventory.InventoryBuilder;
import me.imfighting.bukkit.inventory.ItemBuilder;
import net.redewonder.rankup.Rankup;
import net.redewonder.rankup.api.SchematicAPI;
import net.redewonder.rankup.api.ShopAPI;
import net.redewonder.rankup.commands.WarpCommand;
import net.redewonder.rankup.group.Groups;
import net.redewonder.rankup.managers.LocationsManager;
import net.redewonder.rankup.managers.PlayerManager;
import net.redewonder.rankup.managers.ScoreboardManager;
import net.redewonder.rankup.managers.WorldManager;
import net.redewonder.rankup.sql.CustomPlayer;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Team;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PlayerListeners implements Listener {

    private Cache<UUID, Long> cooldownDays = CacheBuilder.newBuilder().expireAfterWrite(7, TimeUnit.DAYS).build();
    private Cache<UUID, Long> cooldownHours = CacheBuilder.newBuilder().expireAfterWrite(24, TimeUnit.HOURS).build();
    private Cache<UUID, Long> cooldownMinutes = CacheBuilder.newBuilder().expireAfterWrite(60, TimeUnit.MINUTES).build();
    private Cache<UUID, Long> cooldownSeconds = CacheBuilder.newBuilder().expireAfterWrite(60, TimeUnit.SECONDS).build();


    private Cache<UUID, Long> cooldownDaysCloud = CacheBuilder.newBuilder().expireAfterWrite(7, TimeUnit.DAYS).build();
    private Cache<UUID, Long> cooldownHoursCloud = CacheBuilder.newBuilder().expireAfterWrite(24, TimeUnit.HOURS).build();
    private Cache<UUID, Long> cooldownMinutesCloud = CacheBuilder.newBuilder().expireAfterWrite(60, TimeUnit.MINUTES).build();
    private Cache<UUID, Long> cooldownSecondsCloud = CacheBuilder.newBuilder().expireAfterWrite(60, TimeUnit.SECONDS).build();


    private Cache<UUID, Long> cooldownDaysRain = CacheBuilder.newBuilder().expireAfterWrite(7, TimeUnit.DAYS).build();
    private Cache<UUID, Long> cooldownHoursRain = CacheBuilder.newBuilder().expireAfterWrite(24, TimeUnit.HOURS).build();
    private Cache<UUID, Long> cooldownMinutesRain = CacheBuilder.newBuilder().expireAfterWrite(60, TimeUnit.MINUTES).build();
    private Cache<UUID, Long> cooldownSecondsRain = CacheBuilder.newBuilder().expireAfterWrite(60, TimeUnit.SECONDS).build();


    private Cache<UUID, Long> cooldownDaysWater = CacheBuilder.newBuilder().expireAfterWrite(7, TimeUnit.DAYS).build();
    private Cache<UUID, Long> cooldownHoursWater = CacheBuilder.newBuilder().expireAfterWrite(24, TimeUnit.HOURS).build();
    private Cache<UUID, Long> cooldownMinutesWater = CacheBuilder.newBuilder().expireAfterWrite(60, TimeUnit.MINUTES).build();
    private Cache<UUID, Long> cooldownSecondsWater = CacheBuilder.newBuilder().expireAfterWrite(60, TimeUnit.SECONDS).build();


    Inventory loja;
    Inventory blocos;
    Inventory blocos2;
    Inventory blocos3;
    Inventory corantes;
    Inventory farm;
    Inventory decoracoes;
    Inventory combate;

    Inventory terrenosVip;
    Inventory terrenos;



    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        PlayerManager.updatePlayer(e.getPlayer());
        Player player = e.getPlayer();
        ScoreboardManager.updateScore(player);
        TablistAPI.sendTablist(e.getPlayer(), "\n§e§lREDE WONDER\n§aloja.redewonder.net\n§fVocê está conectado em: " + "§eRankup\n", "\n§eDiscord: §fdiscord.gg/8ZCPPguw5S\n§fAdquira §e§lVIP §fou §e§lCASH §facessando: " + "§eloja.redewonder.net\n");

        try {
            CustomPlayer playerData = new CustomPlayer(Rankup.getInstance(), e.getPlayer().getUniqueId(), e.getPlayer());
            Rankup.getInstance().getPlayerManager().addCustomPlayer(e.getPlayer().getUniqueId(), playerData);
        } catch (SQLException ex) {
            e.getPlayer().kickPlayer("Sua data não foi carregado.");
            ex.printStackTrace();
        }

        if (!CustomPlayer.plotmePlayer(player.getName(), "TERRENO1") &&
                !CustomPlayer.plotmePlayer(player.getName(), "TERRENO2") &&
                !CustomPlayer.plotmePlayer(player.getName(), "TERRENO3") &&
                !CustomPlayer.plotmePlayer(player.getName(), "TERRENO4") &&
                !CustomPlayer.plotmePlayer(player.getName(), "TERRENO5") &&
                !CustomPlayer.plotmePlayer(player.getName(), "TERRENO6")) {
            CustomPlayer.addPlotme(player);
        }

        for (Groups groups : Groups.values()) {
            Team team = ScoreboardManager.score.registerNewTeam(groups.getOrderSymbol() + groups.name());
            team.setPrefix(ChatColor.translateAlternateColorCodes('&', groups.getDisplay()));
        }

        Bukkit.getScheduler().runTaskTimer(Rankup.getInstance(), new Runnable() {
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
    }

    @EventHandler
    public void onPlayerChatEvent(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (CustomPlayer.getNametag(player).equalsIgnoreCase("§6MASTER")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.MASTER.getDisplay()) + "§e[" + CustomPlayer.getRank(player.getName()) + "] " + player.getName() + "§7: §f" + e.getMessage().replace('&', '§'));
        } else if (CustomPlayer.getNametag(player).equalsIgnoreCase("§3GERENTE")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.GERENTE.getDisplay()) + "§e[" + CustomPlayer.getRank(player.getName()) + "] " + player.getName() + "§7: §f" + e.getMessage().replace('&', '§'));
        } else if (CustomPlayer.getNametag(player).equalsIgnoreCase("§cADMIN")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.ADMIN.getDisplay()) + "§e[" + CustomPlayer.getRank(player.getName()) + "] " + player.getName() + "§7:" + " §f" + e.getMessage().replace('&', '§'));
        } else if (CustomPlayer.getNametag(player).equalsIgnoreCase("§2MODERADOR")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.MODERADOR.getDisplay()) + "§e[" + CustomPlayer.getRank(player.getName()) + "] " + player.getName() + "§7: " + "§f" + e.getMessage().replace('&', '§'));
        } else if (CustomPlayer.getNametag(player).equalsIgnoreCase("§eAJUDANTE")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.AJUDANTE.getDisplay() + "§e[" + CustomPlayer.getRank(player.getName()) + "] " + player.getName() + "§7: §f" + e.getMessage().replace('&', '§')));
        } else if (CustomPlayer.getNametag(player).equalsIgnoreCase("§5WATER")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.WATER.getDisplay() + "§e[" + CustomPlayer.getRank(player.getName()) + "] " + player.getName() + "§7:" + " §f" + e.getMessage().replace('&', '§')));
        } else if (CustomPlayer.getNametag(player).equalsIgnoreCase("§2RAIN")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.RAIN.getDisplay() + "§e[" + CustomPlayer.getRank(player.getName()) + "] " + player.getName() + "§7: " + "§f" + e.getMessage().replace('&', '§')));
        } else if (CustomPlayer.getNametag(player).equalsIgnoreCase("§bCLOUD")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.CLOUD.getDisplay() + "§e[" + CustomPlayer.getRank(player.getName()) + "] " + player.getName() + "§7:" + " §f" + e.getMessage().replace('&', '§')));
        } else {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', Groups.MEMBRO.getDisplay() + "§e[" + CustomPlayer.getRank(player.getName()) + "] " + player.getName() + "§7: " + "§7" + e.getMessage().replace('&', '§')));
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);

        Player player = e.getPlayer();

        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§bPicareta de diamante §c§l(MINA)")) {
                player.getInventory().remove(Material.DIAMOND_PICKAXE);
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        e.getPlayer().teleport(LocationsManager.getLocation(e.getPlayer(), "Spawn"));
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        e.setDeathMessage(null);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if (e.getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
                if (player.getWorld().getName().equalsIgnoreCase("world")) {
                    Bukkit.getScheduler().runTaskLater(Rankup.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            player.setHealth(20);
                        }
                    }, 1L);
                    player.teleport(LocationsManager.getLocation(player, "Spawn"));
                } else if (player.getWorld().getName().equalsIgnoreCase("Minas")) {
                    Bukkit.getScheduler().runTaskLater(Rankup.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            player.setHealth(20);
                        }
                    }, 1L);
                    player.teleport(LocationsManager.getLocation(player, "Mina"));
                }
            } else if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getInventory().getTitle().equals("§8Kits")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType().equals(Material.WOOD_SWORD)) {
                if (e.getClick().isRightClick()) {
                    Inventory inventory = new InventoryBuilder(6, "§8Membro (Semanal)").toInventory();
                    inventory.setItem(10, new ItemBuilder(Material.DIAMOND_HELMET).toItemStack());
                    inventory.setItem(11, new ItemBuilder(Material.DIAMOND_CHESTPLATE).toItemStack());
                    inventory.setItem(12, new ItemBuilder(Material.DIAMOND_LEGGINGS).toItemStack());
                    inventory.setItem(13, new ItemBuilder(Material.DIAMOND_BOOTS).toItemStack());
                    inventory.setItem(14, new ItemBuilder(Material.DIAMOND_SWORD).toItemStack());
                    inventory.setItem(15, new ItemBuilder(Material.GOLDEN_APPLE, 4).toItemStack());

                    player.openInventory(inventory);
                } else if (e.getClick().isLeftClick()) {
                    if (!cooldownDays.asMap().containsKey(player.getUniqueId())) {
                        if (!cooldownHours.asMap().containsKey(player.getUniqueId())) {
                            if (!cooldownMinutes.asMap().containsKey(player.getUniqueId())) {
                                if (!cooldownSeconds.asMap().containsKey(player.getUniqueId())) {
                                    player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_HELMET).toItemStack());
                                    player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_CHESTPLATE).toItemStack());
                                    player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_LEGGINGS).toItemStack());
                                    player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_BOOTS).toItemStack());
                                    player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_SWORD).toItemStack());
                                    player.getInventory().addItem(new ItemBuilder(Material.GOLDEN_APPLE, 4).toItemStack());
                                    player.closeInventory();
                                    player.sendMessage("§aVocê pegou o kit §7Membro §acom sucesso!");
                                    cooldownDays.put(player.getUniqueId(), System.currentTimeMillis() + 604800000);
                                    cooldownHours.put(player.getUniqueId(), System.currentTimeMillis() + 86400000);
                                    cooldownMinutes.put(player.getUniqueId(), System.currentTimeMillis() + 3600000);
                                    cooldownSeconds.put(player.getUniqueId(), System.currentTimeMillis() + 60000);
                                } else {
                                    long distanceDays = cooldownDays.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                    long distanceHours = cooldownHours.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                    long distanceMinutes = cooldownMinutes.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                    long distanceSeconds = cooldownSeconds.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                    player.sendMessage("§cVocê está em delay para pegar este kit. Espere por mais " + TimeUnit.MILLISECONDS.toDays(distanceDays) + " dias, " + TimeUnit.MILLISECONDS.toHours(distanceHours) + " horas, " + TimeUnit.MILLISECONDS.toMinutes(distanceMinutes) + " minutos e " + TimeUnit.MILLISECONDS.toSeconds(distanceSeconds) + " segundos.");
                                }
                            } else {
                                long distanceDays = cooldownDays.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                long distanceHours = cooldownHours.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                long distanceMinutes = cooldownMinutes.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                long distanceSeconds = cooldownSeconds.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                player.sendMessage("§cVocê está em delay para pegar este kit. Espere por mais " + TimeUnit.MILLISECONDS.toDays(distanceDays) + " dias, " + TimeUnit.MILLISECONDS.toHours(distanceHours) + " horas, " + TimeUnit.MILLISECONDS.toMinutes(distanceMinutes) + " minutos e " + TimeUnit.MILLISECONDS.toSeconds(distanceSeconds) + " segundos.");
                            }
                        } else {
                            long distanceDays = cooldownDays.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                            long distanceHours = cooldownHours.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                            long distanceMinutes = cooldownMinutes.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                            long distanceSeconds = cooldownSeconds.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                            player.sendMessage("§cVocê está em delay para pegar este kit. Espere por mais " + TimeUnit.MILLISECONDS.toDays(distanceDays) + " dias, " + TimeUnit.MILLISECONDS.toHours(distanceHours) + " horas, " + TimeUnit.MILLISECONDS.toMinutes(distanceMinutes) + " minutos e " + TimeUnit.MILLISECONDS.toSeconds(distanceSeconds) + " segundos.");
                        }
                    } else {
                        long distanceDays = cooldownDays.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                        long distanceHours = cooldownHours.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                        long distanceMinutes = cooldownMinutes.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                        long distanceSeconds = cooldownSeconds.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                        player.sendMessage("§cVocê está em delay para pegar este kit. Espere por mais " + TimeUnit.MILLISECONDS.toDays(distanceDays) + " dias, " + TimeUnit.MILLISECONDS.toHours(distanceHours) + " horas, " + TimeUnit.MILLISECONDS.toMinutes(distanceMinutes) + " minutos e " + TimeUnit.MILLISECONDS.toSeconds(distanceSeconds) + " segundos.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.STONE_SWORD)) {
                if (e.getClick().isRightClick()) {
                    Inventory inventory = new InventoryBuilder(6, "§8Cloud (Semanal)").toInventory();
                    inventory.setItem(10, new ItemBuilder(Material.DIAMOND_HELMET).addEnchantmant(Enchantment.DAMAGE_ALL, 3).toItemStack());
                    inventory.setItem(11, new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchantmant(Enchantment.DAMAGE_ALL, 3).toItemStack());
                    inventory.setItem(12, new ItemBuilder(Material.DIAMOND_LEGGINGS).addEnchantmant(Enchantment.DAMAGE_ALL, 3).toItemStack());
                    inventory.setItem(13, new ItemBuilder(Material.DIAMOND_BOOTS).addEnchantmant(Enchantment.DAMAGE_ALL, 3).toItemStack());
                    inventory.setItem(14, new ItemBuilder(Material.DIAMOND_SWORD).addEnchantmant(Enchantment.DAMAGE_ALL, 3).toItemStack());
                    inventory.setItem(15, new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantmant(Enchantment.DIG_SPEED, 3).toItemStack());
                    inventory.setItem(16, new ItemBuilder(Material.GOLDEN_APPLE, 10).toItemStack());

                    player.openInventory(inventory);
                } else if (e.getClick().isLeftClick()) {
                    if ((CustomPlayer.getGroup(player).equalsIgnoreCase("§bCLOUD")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§2RAIN")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§5WATER")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§eAJUDANTE")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§2MODERADOR")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER"))) {
                        if (!cooldownDaysCloud.asMap().containsKey(player.getUniqueId())) {
                            if (!cooldownHoursCloud.asMap().containsKey(player.getUniqueId())) {
                                if (!cooldownMinutesCloud.asMap().containsKey(player.getUniqueId())) {
                                    if (!cooldownSecondsCloud.asMap().containsKey(player.getUniqueId())) {
                                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_HELMET).addEnchantmant(Enchantment.DAMAGE_ALL, 3).toItemStack());
                                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchantmant(Enchantment.DAMAGE_ALL, 3).toItemStack());
                                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_LEGGINGS).addEnchantmant(Enchantment.DAMAGE_ALL, 3).toItemStack());
                                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_BOOTS).addEnchantmant(Enchantment.DAMAGE_ALL, 3).toItemStack());
                                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_SWORD).addEnchantmant(Enchantment.DAMAGE_ALL, 3).toItemStack());
                                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantmant(Enchantment.DIG_SPEED, 3).toItemStack());
                                        player.getInventory().addItem(new ItemBuilder(Material.GOLDEN_APPLE, 10).toItemStack());
                                        player.closeInventory();
                                        player.sendMessage("§aVocê pegou o kit §bCloud §acom sucesso!");
                                        cooldownDaysCloud.put(player.getUniqueId(), System.currentTimeMillis() + 604800000);
                                        cooldownHoursCloud.put(player.getUniqueId(), System.currentTimeMillis() + 86400000);
                                        cooldownMinutesCloud.put(player.getUniqueId(), System.currentTimeMillis() + 3600000);
                                        cooldownSecondsCloud.put(player.getUniqueId(), System.currentTimeMillis() + 60000);
                                    } else {
                                        long distanceDays = cooldownDaysCloud.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                        long distanceHours = cooldownHoursCloud.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                        long distanceMinutes = cooldownMinutesCloud.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                        long distanceSeconds = cooldownSecondsCloud.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                        player.sendMessage("§cVocê está em delay para pegar este kit. Espere por mais " + TimeUnit.MILLISECONDS.toDays(distanceDays) + " dias, " + TimeUnit.MILLISECONDS.toHours(distanceHours) + " horas, " + TimeUnit.MILLISECONDS.toMinutes(distanceMinutes) + " minutos e " + TimeUnit.MILLISECONDS.toSeconds(distanceSeconds) + " segundos.");
                                    }
                                } else {
                                    long distanceDays = cooldownDaysCloud.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                    long distanceHours = cooldownHoursCloud.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                    long distanceMinutes = cooldownMinutesCloud.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                    long distanceSeconds = cooldownSecondsCloud.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                    player.sendMessage("§cVocê está em delay para pegar este kit. Espere por mais " + TimeUnit.MILLISECONDS.toDays(distanceDays) + " dias, " + TimeUnit.MILLISECONDS.toHours(distanceHours) + " horas, " + TimeUnit.MILLISECONDS.toMinutes(distanceMinutes) + " minutos e " + TimeUnit.MILLISECONDS.toSeconds(distanceSeconds) + " segundos.");
                                }
                            } else {
                                long distanceDays = cooldownDaysCloud.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                long distanceHours = cooldownHoursCloud.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                long distanceMinutes = cooldownMinutesCloud.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                long distanceSeconds = cooldownSecondsCloud.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                player.sendMessage("§cVocê está em delay para pegar este kit. Espere por mais " + TimeUnit.MILLISECONDS.toDays(distanceDays) + " dias, " + TimeUnit.MILLISECONDS.toHours(distanceHours) + " horas, " + TimeUnit.MILLISECONDS.toMinutes(distanceMinutes) + " minutos e " + TimeUnit.MILLISECONDS.toSeconds(distanceSeconds) + " segundos.");
                            }
                        } else {
                            long distanceDays = cooldownDaysCloud.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                            long distanceHours = cooldownHoursCloud.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                            long distanceMinutes = cooldownMinutesCloud.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                            long distanceSeconds = cooldownSecondsCloud.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                            player.sendMessage("§cVocê está em delay para pegar este kit. Espere por mais " + TimeUnit.MILLISECONDS.toDays(distanceDays) + " dias, " + TimeUnit.MILLISECONDS.toHours(distanceHours) + " horas, " + TimeUnit.MILLISECONDS.toMinutes(distanceMinutes) + " minutos e " + TimeUnit.MILLISECONDS.toSeconds(distanceSeconds) + " segundos.");
                        }
                    } else {
                        player.sendMessage("§cVocê não tem permissão para este kit.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.IRON_SWORD)) {
                if (e.getClick().isRightClick()) {
                    Inventory inventory = new InventoryBuilder(6, "§8Rain (Semanal)").toInventory();
                    inventory.setItem(10, new ItemBuilder(Material.DIAMOND_HELMET).addEnchantmant(Enchantment.DAMAGE_ALL, 5).addEnchantmant(Enchantment.PROTECTION_FIRE, 5).toItemStack());
                    inventory.setItem(11, new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchantmant(Enchantment.DAMAGE_ALL, 5).addEnchantmant(Enchantment.PROTECTION_FIRE, 5).toItemStack());
                    inventory.setItem(12, new ItemBuilder(Material.DIAMOND_LEGGINGS).addEnchantmant(Enchantment.DAMAGE_ALL, 5).addEnchantmant(Enchantment.PROTECTION_FIRE, 5).toItemStack());
                    inventory.setItem(13, new ItemBuilder(Material.DIAMOND_BOOTS).addEnchantmant(Enchantment.DAMAGE_ALL, 5).addEnchantmant(Enchantment.PROTECTION_FIRE, 5).toItemStack());
                    inventory.setItem(14, new ItemBuilder(Material.DIAMOND_SWORD).addEnchantmant(Enchantment.DAMAGE_ALL, 5).addEnchantmant(Enchantment.PROTECTION_FIRE, 5).toItemStack());
                    inventory.setItem(15, new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantmant(Enchantment.DIG_SPEED, 5).addEnchantmant(Enchantment.LOOT_BONUS_BLOCKS, 1).toItemStack());
                    inventory.setItem(16, new ItemBuilder(Material.GOLDEN_APPLE, 50).toItemStack());

                    player.openInventory(inventory);
                } else if (e.getClick().isLeftClick()) {
                    if ((CustomPlayer.getGroup(player).equalsIgnoreCase("§2RAIN")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§5WATER")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§eAJUDANTE")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§2MODERADOR")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER"))) {
                        if (!cooldownDaysRain.asMap().containsKey(player.getUniqueId())) {
                            if (!cooldownHoursRain.asMap().containsKey(player.getUniqueId())) {
                                if (!cooldownMinutesRain.asMap().containsKey(player.getUniqueId())) {
                                    if (!cooldownSecondsRain.asMap().containsKey(player.getUniqueId())) {
                                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_HELMET).addEnchantmant(Enchantment.DAMAGE_ALL, 5).addEnchantmant(Enchantment.PROTECTION_FIRE, 5).toItemStack());
                                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchantmant(Enchantment.DAMAGE_ALL, 5).addEnchantmant(Enchantment.PROTECTION_FIRE, 5).toItemStack());
                                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_LEGGINGS).addEnchantmant(Enchantment.DAMAGE_ALL, 5).addEnchantmant(Enchantment.PROTECTION_FIRE, 5).toItemStack());
                                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_BOOTS).addEnchantmant(Enchantment.DAMAGE_ALL, 5).addEnchantmant(Enchantment.PROTECTION_FIRE, 5).toItemStack());
                                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_SWORD).addEnchantmant(Enchantment.DAMAGE_ALL, 5).addEnchantmant(Enchantment.PROTECTION_FIRE, 5).toItemStack());
                                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantmant(Enchantment.DIG_SPEED, 5).addEnchantmant(Enchantment.LOOT_BONUS_BLOCKS, 1).toItemStack());
                                        player.getInventory().addItem(new ItemBuilder(Material.GOLDEN_APPLE, 50).toItemStack());
                                        player.closeInventory();
                                        player.sendMessage("§aVocê pegou o kit §2Rain §acom sucesso!");
                                        cooldownDaysRain.put(player.getUniqueId(), System.currentTimeMillis() + 604800000);
                                        cooldownHoursRain.put(player.getUniqueId(), System.currentTimeMillis() + 86400000);
                                        cooldownMinutesRain.put(player.getUniqueId(), System.currentTimeMillis() + 3600000);
                                        cooldownSecondsRain.put(player.getUniqueId(), System.currentTimeMillis() + 60000);
                                    } else {
                                        long distanceDays = cooldownDaysRain.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                        long distanceHours = cooldownHoursRain.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                        long distanceMinutes = cooldownMinutesRain.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                        long distanceSeconds = cooldownSecondsRain.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                        player.sendMessage("§cVocê está em delay para pegar este kit. Espere por mais " + TimeUnit.MILLISECONDS.toDays(distanceDays) + " dias, " + TimeUnit.MILLISECONDS.toHours(distanceHours) + " horas, " + TimeUnit.MILLISECONDS.toMinutes(distanceMinutes) + " minutos e " + TimeUnit.MILLISECONDS.toSeconds(distanceSeconds) + " segundos.");
                                    }
                                } else {
                                    long distanceDays = cooldownDaysRain.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                    long distanceHours = cooldownHoursRain.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                    long distanceMinutes = cooldownMinutesRain.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                    long distanceSeconds = cooldownSecondsRain.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                    player.sendMessage("§cVocê está em delay para pegar este kit. Espere por mais " + TimeUnit.MILLISECONDS.toDays(distanceDays) + " dias, " + TimeUnit.MILLISECONDS.toHours(distanceHours) + " horas, " + TimeUnit.MILLISECONDS.toMinutes(distanceMinutes) + " minutos e " + TimeUnit.MILLISECONDS.toSeconds(distanceSeconds) + " segundos.");
                                }
                            } else {
                                long distanceDays = cooldownDaysRain.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                long distanceHours = cooldownHoursRain.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                long distanceMinutes = cooldownMinutesRain.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                long distanceSeconds = cooldownSecondsRain.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                player.sendMessage("§cVocê está em delay para pegar este kit. Espere por mais " + TimeUnit.MILLISECONDS.toDays(distanceDays) + " dias, " + TimeUnit.MILLISECONDS.toHours(distanceHours) + " horas, " + TimeUnit.MILLISECONDS.toMinutes(distanceMinutes) + " minutos e " + TimeUnit.MILLISECONDS.toSeconds(distanceSeconds) + " segundos.");
                            }
                        } else {
                            long distanceDays = cooldownDaysRain.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                            long distanceHours = cooldownHoursRain.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                            long distanceMinutes = cooldownMinutesRain.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                            long distanceSeconds = cooldownSecondsRain.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                            player.sendMessage("§cVocê está em delay para pegar este kit. Espere por mais " + TimeUnit.MILLISECONDS.toDays(distanceDays) + " dias, " + TimeUnit.MILLISECONDS.toHours(distanceHours) + " horas, " + TimeUnit.MILLISECONDS.toMinutes(distanceMinutes) + " minutos e " + TimeUnit.MILLISECONDS.toSeconds(distanceSeconds) + " segundos.");
                        }
                    } else {
                        player.sendMessage("§cVocê não tem permissão para este kit.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.DIAMOND_SWORD)) {
                if (e.getClick().isRightClick()) {
                    Inventory inventory = new InventoryBuilder(6, "§8Water (Semanal)").toInventory();
                    inventory.setItem(10, new ItemBuilder(Material.DIAMOND_HELMET).addEnchantmant(Enchantment.DAMAGE_ALL, 5).addEnchantmant(Enchantment.PROTECTION_FIRE, 5).addEnchantmant(Enchantment.PROTECTION_PROJECTILE, 5).toItemStack());
                    inventory.setItem(11, new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchantmant(Enchantment.DAMAGE_ALL, 5).addEnchantmant(Enchantment.PROTECTION_FIRE, 5).addEnchantmant(Enchantment.PROTECTION_PROJECTILE, 5).toItemStack());
                    inventory.setItem(12, new ItemBuilder(Material.DIAMOND_LEGGINGS).addEnchantmant(Enchantment.DAMAGE_ALL, 5).addEnchantmant(Enchantment.PROTECTION_FIRE, 5).addEnchantmant(Enchantment.PROTECTION_PROJECTILE, 5).toItemStack());
                    inventory.setItem(13, new ItemBuilder(Material.DIAMOND_BOOTS).addEnchantmant(Enchantment.DAMAGE_ALL, 5).addEnchantmant(Enchantment.PROTECTION_FIRE, 5).addEnchantmant(Enchantment.PROTECTION_PROJECTILE, 5).toItemStack());
                    inventory.setItem(14, new ItemBuilder(Material.DIAMOND_SWORD).addEnchantmant(Enchantment.DAMAGE_ALL, 5).addEnchantmant(Enchantment.PROTECTION_FIRE, 5).addEnchantmant(Enchantment.PROTECTION_PROJECTILE, 5).toItemStack());
                    inventory.setItem(15, new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantmant(Enchantment.DIG_SPEED, 5).addEnchantmant(Enchantment.LOOT_BONUS_BLOCKS, 2).toItemStack());
                    inventory.setItem(16, new ItemBuilder(Material.GOLDEN_APPLE, 64).toItemStack());

                    player.openInventory(inventory);
                } else if (e.getClick().isLeftClick()) {
                    if ((CustomPlayer.getGroup(player).equalsIgnoreCase("§5WATER")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§eAJUDANTE")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§2MODERADOR")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER"))) {
                        if (!cooldownDaysWater.asMap().containsKey(player.getUniqueId())) {
                            if (!cooldownHoursWater.asMap().containsKey(player.getUniqueId())) {
                                if (!cooldownMinutesWater.asMap().containsKey(player.getUniqueId())) {
                                    if (!cooldownSecondsWater.asMap().containsKey(player.getUniqueId())) {
                                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_HELMET).addEnchantmant(Enchantment.DAMAGE_ALL, 5).addEnchantmant(Enchantment.PROTECTION_FIRE, 5).addEnchantmant(Enchantment.PROTECTION_PROJECTILE, 5).toItemStack());
                                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchantmant(Enchantment.DAMAGE_ALL, 5).addEnchantmant(Enchantment.PROTECTION_FIRE, 5).addEnchantmant(Enchantment.PROTECTION_PROJECTILE, 5).toItemStack());
                                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_LEGGINGS).addEnchantmant(Enchantment.DAMAGE_ALL, 5).addEnchantmant(Enchantment.PROTECTION_FIRE, 5).addEnchantmant(Enchantment.PROTECTION_PROJECTILE, 5).toItemStack());
                                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_BOOTS).addEnchantmant(Enchantment.DAMAGE_ALL, 5).addEnchantmant(Enchantment.PROTECTION_FIRE, 5).addEnchantmant(Enchantment.PROTECTION_PROJECTILE, 5).toItemStack());
                                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_SWORD).addEnchantmant(Enchantment.DAMAGE_ALL, 5).addEnchantmant(Enchantment.PROTECTION_FIRE, 5).addEnchantmant(Enchantment.PROTECTION_PROJECTILE, 5).toItemStack());
                                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantmant(Enchantment.DIG_SPEED, 5).addEnchantmant(Enchantment.LOOT_BONUS_BLOCKS, 2).toItemStack());
                                        player.getInventory().addItem(new ItemBuilder(Material.GOLDEN_APPLE, 64).toItemStack());
                                        player.closeInventory();
                                        player.sendMessage("§aVocê pegou o kit §5Water §acom sucesso!");
                                        cooldownDaysWater.put(player.getUniqueId(), System.currentTimeMillis() + 604800000);
                                        cooldownHoursWater.put(player.getUniqueId(), System.currentTimeMillis() + 86400000);
                                        cooldownMinutesWater.put(player.getUniqueId(), System.currentTimeMillis() + 3600000);
                                        cooldownSecondsWater.put(player.getUniqueId(), System.currentTimeMillis() + 60000);
                                    } else {
                                        long distanceDays = cooldownDaysWater.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                        long distanceHours = cooldownHoursWater.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                        long distanceMinutes = cooldownMinutesWater.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                        long distanceSeconds = cooldownSecondsWater.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                        player.sendMessage("§cVocê está em delay para pegar este kit. Espere por mais " + TimeUnit.MILLISECONDS.toDays(distanceDays) + " dias, " + TimeUnit.MILLISECONDS.toHours(distanceHours) + " horas, " + TimeUnit.MILLISECONDS.toMinutes(distanceMinutes) + " minutos e " + TimeUnit.MILLISECONDS.toSeconds(distanceSeconds) + " segundos.");
                                    }
                                } else {
                                    long distanceDays = cooldownDaysWater.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                    long distanceHours = cooldownHoursWater.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                    long distanceMinutes = cooldownMinutesWater.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                    long distanceSeconds = cooldownSecondsWater.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                    player.sendMessage("§cVocê está em delay para pegar este kit. Espere por mais " + TimeUnit.MILLISECONDS.toDays(distanceDays) + " dias, " + TimeUnit.MILLISECONDS.toHours(distanceHours) + " horas, " + TimeUnit.MILLISECONDS.toMinutes(distanceMinutes) + " minutos e " + TimeUnit.MILLISECONDS.toSeconds(distanceSeconds) + " segundos.");
                                }
                            } else {
                                long distanceDays = cooldownDaysWater.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                long distanceHours = cooldownHoursWater.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                long distanceMinutes = cooldownMinutesWater.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                long distanceSeconds = cooldownSecondsWater.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                                player.sendMessage("§cVocê está em delay para pegar este kit. Espere por mais " + TimeUnit.MILLISECONDS.toDays(distanceDays) + " dias, " + TimeUnit.MILLISECONDS.toHours(distanceHours) + " horas, " + TimeUnit.MILLISECONDS.toMinutes(distanceMinutes) + " minutos e " + TimeUnit.MILLISECONDS.toSeconds(distanceSeconds) + " segundos.");
                            }
                        } else {
                            long distanceDays = cooldownDaysWater.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                            long distanceHours = cooldownHoursWater.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                            long distanceMinutes = cooldownMinutesWater.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                            long distanceSeconds = cooldownSecondsWater.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                            player.sendMessage("§cVocê está em delay para pegar este kit. Espere por mais " + TimeUnit.MILLISECONDS.toDays(distanceDays) + " dias, " + TimeUnit.MILLISECONDS.toHours(distanceHours) + " horas, " + TimeUnit.MILLISECONDS.toMinutes(distanceMinutes) + " minutos e " + TimeUnit.MILLISECONDS.toSeconds(distanceSeconds) + " segundos.");
                        }
                    } else {
                        player.sendMessage("§cVocê não tem permissão para este kit.");
                    }
                }
            }
        } else if (e.getInventory().getTitle().equalsIgnoreCase("§8Membro (Semanal)") ||
                (e.getInventory().getTitle().equalsIgnoreCase("§8Cloud (Semanal)")) ||
                (e.getInventory().getTitle().equalsIgnoreCase("§8Rain (Semanal)")) ||
                (e.getInventory().getTitle().equalsIgnoreCase("§8Water (Semanal)")) ||
                (e.getInventory().getTitle().equalsIgnoreCase("§8Warps do servidor"))) {
            e.setCancelled(true);
        }

        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§l➦ Spawn")) {
            if (!player.getWorld().getName().equalsIgnoreCase("world")) {
                player.teleport(LocationsManager.getLocation(player, "Spawn"));
                player.closeInventory();
                player.sendMessage("§aVocê foi teleportado para o spawn com sucesso.");

                for (ItemStack itemStack : player.getInventory().getContents()) {
                    if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§bPicareta de diamante §c§l(MINA)")) {
                        player.getInventory().remove(Material.DIAMOND_PICKAXE);
                    }
                }

            } else {
                player.closeInventory();
                player.sendMessage("§cVocê já está no spawn.");
            }
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§l➦ Mineração")) {

            if (!player.getWorld().getName().equalsIgnoreCase("Minas")) {
                if (CustomPlayer.isInventoryEmpty(player)) {
                    player.teleport(LocationsManager.getLocation(player, "Mina"));
                    player.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantmant(Enchantment.DURABILITY, 3).addEnchantmant(Enchantment.LOOT_BONUS_BLOCKS, 2).addEnchantmant(Enchantment.DIG_SPEED, 5).setDisplayName("§bPicareta de diamante §c§l(MINA)").toItemStack());
                    player.sendMessage("§aVocê foi teleportado para a mineração com sucesso.");
                } else {
                    player.closeInventory();
                    player.sendMessage("§cLimpe seu inventário antes de entrar na mineração.");
                }
            } else {
                player.closeInventory();
                player.sendMessage("§cVocê já está na área de mineração.");
            }
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§l➦ Terrenos")) {

            if (CustomPlayer.getGroup(player).equalsIgnoreCase("§bCLOUD") ||
            CustomPlayer.getGroup(player).equalsIgnoreCase("§2RAIN") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§5WATER") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§eAJUDANTE") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§2MODERADOR") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER")) {
                terrenosVip = new InventoryBuilder(6, "§8Terrenos").toInventory();

                if (!CustomPlayer.getPlotme1(player)) {
                    terrenosVip.setItem(21, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0)
                            .setDisplayName("§cSlot #01")
                            .setLore("", "§eClique aqui para comprar um terreno")
                            .toItemStack());
                } else {
                    terrenosVip.setItem(21, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5)
                            .setDisplayName("§aTerreno 01")
                            .setLore("", "§eClique aqui para teleportar para este terreno.")
                            .toItemStack());
                }


                if (!CustomPlayer.getPlotme2(player)) {
                    terrenosVip.setItem(22, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0)
                            .setDisplayName("§cSlot #02")
                            .setLore("", "§eClique aqui para comprar um terreno")
                            .toItemStack());
                } else {
                    terrenosVip.setItem(22, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5)
                            .setDisplayName("§aTerreno 02")
                            .setLore("", "§eClique aqui para teleportar para este terreno.")
                            .toItemStack());
                }


                if (!CustomPlayer.getPlotme3(player)) {
                    terrenosVip.setItem(23, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0)
                            .setDisplayName("§cSlot #03")
                            .setLore("", "§eClique aqui para comprar um terreno")
                            .toItemStack());

                } else {
                    terrenosVip.setItem(23, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5)
                            .setDisplayName("§aTerreno 03")
                            .setLore("", "§eClique aqui para teleportar para este terreno.")
                            .toItemStack());
                }

                if (!CustomPlayer.getPlotme4(player)) {
                    terrenosVip.setItem(30, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0)
                            .setDisplayName("§cSlot #04")
                            .setLore("", "§eClique aqui para comprar um terreno")
                            .toItemStack());
                } else {
                    terrenosVip.setItem(30, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5)
                            .setDisplayName("§aTerreno 04")
                            .setLore("", "§eClique aqui para teleportar para este terreno.")
                            .toItemStack());
                }

                if (!CustomPlayer.getPlotme5(player)) {
                    terrenosVip.setItem(31, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0)
                            .setDisplayName("§cSlot #05")
                            .setLore("", "§eClique aqui para comprar um terreno")
                            .toItemStack());
                } else {
                    terrenosVip.setItem(31, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5)
                            .setDisplayName("§aTerreno 05")
                            .setLore("", "§eClique aqui para teleportar para este terreno.")
                            .toItemStack());
                }

                if (!CustomPlayer.getPlotme6(player)) {
                    terrenosVip.setItem(32, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0)
                            .setDisplayName("§cSlot #06")
                            .setLore("", "§eClique aqui para comprar um terreno")
                            .toItemStack());
                } else {
                    terrenosVip.setItem(32, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5)
                            .setDisplayName("§aTerreno 06")
                            .setLore("", "§eClique aqui para teleportar para este terreno.")
                            .toItemStack());
                }
                player.openInventory(terrenosVip);
            } else if (CustomPlayer.getGroup(player).equalsIgnoreCase("§7MEMBRO")) {

                terrenos = new InventoryBuilder(6, "§8Terrenos").toInventory();

                if (!CustomPlayer.getPlotme1(player)) {
                    terrenos.setItem(21, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0)
                            .setDisplayName("§cSlot #01")
                            .setLore("", "§eClique aqui para comprar um terreno")
                            .toItemStack());
                } else {
                    terrenos.setItem(21, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5)
                            .setDisplayName("§aTerreno 01")
                            .setLore("", "§eClique aqui para teleportar para este terreno.")
                            .toItemStack());
                }

                player.openInventory(terrenos);

            }

        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§l➦ Loja")) {

            loja = new InventoryBuilder(3, "§8Loja (Categorias)").toInventory();

            loja.setItem(11, new ItemBuilder(Material.SKULL_ITEM)
                    .setDurability(Material.SKULL_ITEM, 3)
                    .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzIyODM5ZDVjN2ZjMDY3ODA2MmYxYzZjOGYyN2IzMzIwOTQzODRlM2JiNWM0YjVlYmQxNjc2YjI3OWIwNmJmIn19fQ==")
                    .setDisplayName("§c§l✦ §a§lBLOCOS")
                    .toItemStack());
            loja.setItem(12, new ItemBuilder(Material.SKULL_ITEM)
                    .setDurability(Material.SKULL_ITEM, 3)
                    .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2YwZTRhNjI5MmJjNWVlNzY0MmNhZTczNzZlZWRhNDQ2YzA0NzcxZTM5MDZmOGMwYjU4NjQxY2IzMWNmNjM3MyJ9fX0=")
                    .setDisplayName("§c§l✦ §5§lCORANTES")
                    .toItemStack());
            loja.setItem(13, new ItemBuilder(Material.SKULL_ITEM)
                    .setDurability(Material.SKULL_ITEM, 3)
                    .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWU1NDE0ZDUxMDY2OWUyMjUyNmQ0MGM1NmM4MzQ5YTQ5M2NhNzQxOTMyYWNhMjZkYWVjZmFmYTZiOWY0OTA4NyJ9fX0=")
                    .setDisplayName("§c§l✦ §e§lFARM")
                    .toItemStack());
            loja.setItem(14, new ItemBuilder(Material.SKULL_ITEM)
                    .setDurability(Material.SKULL_ITEM, 3)
                    .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTY0NWE0NGE5YTI4ZGRjNDdkZjZlODQ5ODExMWI2MjdkMGZiZGU5ODUyYjhjN2ZlODQ3MzRmMWQ2ZWVmYzlmYSJ9fX0=")
                    .setDisplayName("§c§l✦ §b§lDECORAÇÕES")
                    .toItemStack());
            loja.setItem(15, new ItemBuilder(Material.SKULL_ITEM)
                    .setDurability(Material.SKULL_ITEM, 3)
                    .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjdhODExYWUyMDhjZTE0ODg4ZWVkNzQ2N2I3ODBhNzJlOTQwOGI2ZGNmMDY2MWM4Nzg2NWNjODY4NTUxZTljIn19fQ==")
                    .setDisplayName("§c§l✦ §4§lCOMBATE")
                    .toItemStack());

            player.openInventory(loja);

        }

        if (e.getInventory().getTitle().equalsIgnoreCase("§8Loja (Categorias)")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§l✦ §a§lBLOCOS")) {
                blocos = new ShopAPI(player, "Blocos", 5, 1)
                        .addShopItem("blocos", Material.STONE,"Pedra", 2)
                        .addShopItem("blocos", Material.STONE,  "Granito", 3, 1)
                        .addShopItem("blocos", Material.STONE,  "Diorito", 4, 3)
                        .addShopItem("blocos", Material.ICE, "Bloco de Gelo", 5)
                        .addShopItem("blocos", Material.DIRT, "Terra", 6)

                        .addShopItem("blocos", Material.GRASS, "Grama", 11)
                        .addShopItem("blocos", Material.DIRT, "Terra Grossa", 12, 1)
                        .addShopItem("blocos", Material.DIRT, "Podzol", 13, 2)
                        .addShopItem("blocos", Material.MYCEL, "Micélio", 14)
                        .addShopItem("blocos", Material.COBBLESTONE, "Pedregulho", 15)

                        .addBackProx("voltar", 18)

                        .addShopItem("blocos", Material.SAND, "Areia", 20)
                        .addShopItem("blocos", Material.SAND, "Areia Vermelha", 21, 1)
                        .addShopItem("blocos", Material.GLASS, "Vidro", 22)
                        .addShopItem("blocos", Material.WOOL, "Lã", 23)
                        .addShopItem("blocos", Material.BRICK, "Tijolos", 24)

                        .addBackProx("prox", 26)

                        .addShopItem("blocos", Material.BOOKSHELF, "Estante", 29)
                        .addShopItem("blocos", Material.MOSSY_COBBLESTONE, "Pedra com Musgo", 30)
                        .addShopItem("blocos", Material.OBSIDIAN, "Obsidiana", 31)
                        .addShopItem("blocos", Material.SNOW_BLOCK, "Neve", 32)
                        .addShopItem("blocos", Material.CLAY, "Argila", 33)

                        .addShopItem("blocos", Material.NETHERRACK, "Rocha do Nether", 38)
                        .addShopItem("blocos", Material.GLOWSTONE, "Pedra Iluminosa", 39)
                        .addShopItem("blocos", Material.NETHER_BRICK, "Tijolos do Nether", 40)
                        .addShopItem("blocos", Material.ENDER_STONE, "Pedra do Fim", 41)
                        .addShopItem("blocos", Material.QUARTZ_BLOCK, "Bloco de Quartzo", 42)
                        .toInventory();

            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§l✦ §5§lCORANTES")) {
                corantes = new ShopAPI(player, "Corantes", 3, 1)
                        .addShopItem("corantes", Material.INK_SACK, "Corante Ciano", 2, 6)
                        .addShopItem("corantes", Material.INK_SACK, "Corante Azul Claro", 3, 12)
                        .addShopItem("corantes", Material.INK_SACK, "Corante Amarelo", 4, 11)
                        .addShopItem("corantes", Material.INK_SACK, "Corante Laranja", 5, 14)
                        .addShopItem("corantes", Material.INK_SACK, "Corante Preto", 6)

                        .addShopItem("corantes", Material.INK_SACK, "Corante Verde Escuro", 11, 2)
                        .addShopItem("corantes", Material.INK_SACK, "Corante Vermelho", 12, 1)
                        .addShopItem("corantes", Material.INK_SACK, "Corante Branco", 13, 7)
                        .addShopItem("corantes", Material.INK_SACK, "Corante Cinza", 14, 8)
                        .addShopItem("corantes", Material.INK_SACK, "Corante Verde Claro", 15, 10)

                        .addBackProx("voltar", 18)

                        .addShopItem("corantes", Material.INK_SACK, "Corante Rosa", 20, 9)
                        .addShopItem("corantes", Material.INK_SACK, "Corante Magenta", 21, 13)
                        .addShopItem("corantes", Material.INK_SACK, "Corante Roxo", 22, 5)

                        .toInventory();
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§l✦ §e§lFARM")) {
                farm = new ShopAPI(player, "Farm", 3, 1)
                        .addShopItem("farm", Material.CACTUS, "Cactus", 13)

                        .addBackProx("voltar", 18)

                        .toInventory();
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§l✦ §b§lDECORAÇÕES")) {
                decoracoes = new ShopAPI(player, "Decorações", 5, 1)
                        .addShopItem("decoracoes", Material.ENDER_CHEST, "Baú do Fim", 2)
                        .addShopItem("decoracoes", Material.NOTE_BLOCK, "Bloco de Nota Musical", 3)
                        .addShopItem("decoracoes", Material.ITEM_FRAME, "Moldura", 4)
                        .addShopItem("decoracoes", Material.CAULDRON_ITEM, "Caldeirão", 5)
                        .addShopItem("decoracoes", Material.BREWING_STAND_ITEM, "Suporte de Poção", 6)

                        .addShopItem("decoracoes", Material.PAINTING, "Quadro", 11)
                        .addShopItem("decoracoes", Material.BANNER, "Estandarte Branco", 12, 15)
                        .addShopItem("decoracoes", Material.WATER_LILY, "Vitória-Régia", 13)
                        .addShopItem("decoracoes", Material.RED_ROSE, "Allium", 14)
                        .addShopItem("decoracoes", Material.TORCH, "Tocha", 15)

                        .addBackProx("voltar", 18)

                        .addShopItem("decoracoes", Material.WEB, "Teia", 20)
                        .addShopItem("decoracoes", Material.FLOWER_POT_ITEM, "Vaso", 21)
                        .addShopItem("decoracoes", Material.DOUBLE_PLANT, "Roseira", 22, 4)
                        .addShopItem("decoracoes", Material.SAPLING, "Muda de Carvalho Escuro", 23, 5)
                        .addShopItem("decoracoes", Material.RED_ROSE, "Margarida", 24, 8)

                        .addShopItem("decoracoes", Material.DEAD_BUSH, "Abusto Morto", 29)
                        .addShopItem("decoracoes", Material.RED_ROSE, "Orquidea", 30, 1)
                        .addShopItem("decoracoes", Material.YELLOW_FLOWER, "Dente de Leão", 31)
                        .addShopItem("decoracoes", Material.SAPLING, "Muda de Árvore de Selva", 32, 3)
                        .addShopItem("decoracoes", Material.SAPLING, "Muda de Acácia", 33, 4)

                        .addShopItem("decoracoes", Material.SKULL_ITEM, "Cabeça de Zombie", 38, 2)
                        .addShopItem("decoracoes", Material.SKULL_ITEM, "Cabeça de Creeper", 39, 4)
                        .addShopItem("decoracoes", Material.LAVA_BUCKET, "Balde de Lava", 40)
                        .addShopItem("decoracoes", Material.WATER_BUCKET, "Balde de Água", 41)

                        .toInventory();
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§l✦ §4§lCOMBATE")) {
                combate = new ShopAPI(player, "Combate", 5, 1)
                        .addShopItemCombate(Material.DIAMOND_HELMET, "Capacete", 2)
                        .addShopItemCombate(Material.DIAMOND_CHESTPLATE, "Peitoral", 11)
                        .addShopItemCombate(Material.DIAMOND_LEGGINGS, "Calça", 20)
                        .addShopItemCombate(Material.DIAMOND_BOOTS, "Bota", 29)

                        .addBackProx("voltar", 18)

                        .addShopItemCombate(Material.DIAMOND_SWORD, "Espada", 4)
                        .addShopItemCombate(Material.DIAMOND_SPADE, "Pá", 13)
                        .addShopItemCombate(Material.GOLDEN_APPLE, "Maçã Dourada", 22)
                        .addShopItemCombate(Material.GOLDEN_APPLE, "Maçã Dourada Encantada", 31)

                        .addShopItemCombate(Material.DIAMOND_AXE, "Machado", 5)
                        .addShopItemCombate(Material.DIAMOND_HOE, "Enxada", 14)

                        .addShopItemCombate(Material.DIAMOND_PICKAXE, "Picareta", 6)
                        .addShopItemCombate(Material.EXP_BOTTLE, "XP", 15)
                        .addShopItemCombate(Material.POTION, "Poção de Velocidade", 24)
                        .addShopItemCombate(Material.POTION, "Poção de Força", 33)

                        .toInventory();
            }
        }

        if (e.getInventory().getTitle().equalsIgnoreCase("§8Loja - Blocos (1)")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Pedra")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.STONE, "Pedra", 1, 50, 0);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.STONE, "Pedra", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Granito")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.STONE, "Granito", 1, 50, 1);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.STONE, "Granito", 64, 3200, 1);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Diorito")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.STONE, "Diorito", 1, 50, 3);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.STONE, "Diorito", 64, 3200, 3);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.ICE)) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.STONE, "Bloco de Gelo", 1, 50, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.STONE, "Bloco de Gelo", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Terra")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.DIRT, "Terra", 1, 50, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.DIRT, "Terra", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.GRASS)) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.GRASS, "Grama", 1, 50, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.GRASS, "Grama", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Terra Grossa")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.DIRT, "Terra Grossa", 1, 50, 1);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.DIRT, "Terra Grossa", 64, 3200, 1);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Podzol")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.DIRT, "Podzol", 1, 50, 2);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.DIRT, "Podzol", 64, 3200, 2);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.MYCEL)) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.MYCEL, "Micélio", 1, 50, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.MYCEL, "Micélio", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.COBBLESTONE)) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.COBBLESTONE, "Pedregulho", 1, 50, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.COBBLESTONE, "Pedregulho", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Areia")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.SAND, "Areia", 1, 50, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.SAND, "Areia", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Areia Vermelha")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.SAND, "Areia Vermelha", 1, 50, 1);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.SAND, "Areia Vermelha", 64, 3200, 1);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.GLASS)) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.GLASS, "Vidro", 1, 50, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.GLASS, "Vidro", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Lã")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.WOOL, "Lã", 1, 50, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.WOOL, "Lã", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.BRICK)) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.BRICK, "Tijolos", 1, 50, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.BRICK, "Tijolos", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.BOOKSHELF)) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.BOOKSHELF, "Estante", 1, 50, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.BOOKSHELF, "Estante", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.MOSSY_COBBLESTONE)) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.MOSSY_COBBLESTONE, "Pedra com Musgo", 1, 50, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.MOSSY_COBBLESTONE, "Pedra com Musgo", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.OBSIDIAN)) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.OBSIDIAN, "Obsidian", 1, 50, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.OBSIDIAN, "Obsidian", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.SNOW_BLOCK)) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.SNOW_BLOCK, "Neve", 1, 50, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.SNOW_BLOCK, "Neve", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.CLAY)) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.CLAY, "Argila", 1, 50, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.CLAY, "Argila", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.NETHERRACK)) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.NETHERRACK, "Rocha do Nether", 1, 50, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.NETHERRACK, "Rocha do Nether", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.GLOWSTONE)) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.GLOWSTONE, "Pedra Iluminosa", 1, 50, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.GLOWSTONE, "Pedra Iluminosa", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.NETHER_BRICK)) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.NETHER_BRICK, "Pedra Iluminosa", 1, 50, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.NETHER_BRICK, "Pedra Iluminosa", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.ENDER_STONE)) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.ENDER_STONE, "Pedra do Fim", 1, 50, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.ENDER_STONE, "Pedra do Fim", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.QUARTZ_BLOCK)) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.QUARTZ_BLOCK, "Pedra do Fim", 1, 50, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.QUARTZ_BLOCK, "Pedra do Fim", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.ARROW)) {
                player.openInventory(loja);
            } else if (e.getCurrentItem().getType().equals(Material.INK_SACK)) {
                blocos2 = new ShopAPI(player, "Blocos", 5, 2)
                        .addShopItem("blocos", Material.QUARTZ_BLOCK, "Bloco de Quartzo Talhado", 2, 1)
                        .addShopItem("blocos", Material.QUARTZ_BLOCK, "Pilar de Quartzo", 3, 2)
                        .addShopItem("blocos", Material.SMOOTH_BRICK, "Tijolos de Pedra", 4)
                        .addShopItem("blocos", Material.SMOOTH_BRICK, "Tijolos de Pedra com Musgo", 5, 1)
                        .addShopItem("blocos", Material.SMOOTH_BRICK, "Tijolos de Pedra Rachados", 6, 2)

                        .addShopItem("blocos", Material.PRISMARINE, "Prismarinho", 11)
                        .addShopItem("blocos", Material.PRISMARINE, "Tijolos de Prismarinho", 12, 1)
                        .addShopItem("blocos", Material.PRISMARINE, "Prismarinho Escuro", 13, 2)
                        .addShopItem("blocos", Material.SEA_LANTERN, "Lanterna do Mar", 14)
                        .addShopItem("blocos", Material.PACKED_ICE, "Gelo Compactado", 15)

                        .addBackProx("anterior", 18)

                        .addShopItem("blocos", Material.HAY_BLOCK, "Fardo de Feno", 20)
                        .addShopItem("blocos", Material.HARD_CLAY, "Argila Endurecida", 21)
                        .addShopItem("blocos", Material.SANDSTONE, "Arenito", 22)
                        .addShopItem("blocos", Material.SANDSTONE, "Arenito Talhado", 23, 1)
                        .addShopItem("blocos", Material.SANDSTONE, "Arenito Liso", 24, 2)

                        .addBackProx("prox", 26)

                        .addShopItem("blocos", Material.RED_SANDSTONE, "Arenito Vermelho", 29)
                        .addShopItem("blocos", Material.RED_SANDSTONE, "Arenito Vermelho Talhado", 30, 1)
                        .addShopItem("blocos", Material.RED_SANDSTONE, "Arenito Vermelho Liso", 31, 2)
                        .addShopItem("blocos", Material.GRAVEL, "Cascalho", 32)
                        .addShopItem("blocos", Material.LOG, "Madeira de Carvalho", 33)

                        .addShopItem("blocos", Material.LOG, "Madeira de Eucalipto", 38, 2)
                        .addShopItem("blocos", Material.LOG, "Madeira de Pinheiro", 39, 1)
                        .addShopItem("blocos", Material.LOG, "Madeira de Selva", 40, 3)
                        .addShopItem("blocos", Material.LOG_2, "Madeira Acácia", 41)
                        .addShopItem("blocos", Material.LOG_2, "Madeira de Carvalho Escuro", 42, 1)
                        .toInventory();
            }
        } else if (e.getInventory().getTitle().equalsIgnoreCase("§8Loja - Blocos (2)")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Bloco de Quartzo Talhado")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.QUARTZ_BLOCK, "Bloco de Quartzo Talhado", 1, 50, 1);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.QUARTZ_BLOCK, "Bloco de Quartzo Talhado", 64, 3200, 1);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Pilar de Quartzo")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.QUARTZ_BLOCK, "Pilar de Quartzo", 1, 50, 2);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.QUARTZ_BLOCK, "Pilar de Quartzo", 64, 3200, 2);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Tijolos de Pedra")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.SMOOTH_BRICK, "Tijolos de Pedra", 1, 50, 0);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.SMOOTH_BRICK, "Tijolos de Pedra", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Tijolos de Pedra com " +
                    "Musgo")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.SMOOTH_BRICK, "Tijolos de Pedra com Musgo", 1, 50, 1);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.SMOOTH_BRICK, "Tijolos de Pedra com Musgo", 64, 3200, 1);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Tijolos de Pedra " +
                    "Rachados")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.SMOOTH_BRICK, "Tijolos de Pedra Rachados", 1, 50, 2);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.SMOOTH_BRICK, "Tijolos de Pedra Rachados", 64, 3200, 2);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Prismarinho")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.PRISMARINE, "Prismarinho", 1, 50, 0);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.PRISMARINE, "Prismarinho", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Tijolos de Prismarinho")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.PRISMARINE, "Tijolos de Prismarinho", 1, 50, 1);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.PRISMARINE, "Tijolos de Prismarinho", 64, 3200, 1);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Prismarinho Escuro")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.PRISMARINE, "Prismarinho Escuro", 1, 50, 2);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.PRISMARINE, "Prismarinho Escuro", 64, 3200, 2);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.SEA_LANTERN)) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.SEA_LANTERN, "Lanterna do Mar", 1, 50, 0);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.SEA_LANTERN, "Lanterna do Mar", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.PACKED_ICE)) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.PACKED_ICE, "Gelo Compactado", 1, 50, 0);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.PACKED_ICE, "Gelo Compactado", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.HAY_BLOCK)) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.HAY_BLOCK, "Fardo de Feno", 1, 50, 0);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.HAY_BLOCK, "Fardo de Feno", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.HARD_CLAY)) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.HARD_CLAY, "Argila Endurecida", 1, 50, 0);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.HARD_CLAY, "Argila Endurecida", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Arenito ❮")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.SANDSTONE, "Arenito", 1, 50, 0);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.SANDSTONE, "Arenito", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Arenito Talhado")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.SANDSTONE, "Arenito Talhado", 1, 50, 1);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.SANDSTONE, "Arenito Talhado", 64, 3200, 1);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Arenito Liso")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.SANDSTONE, "Arenito Liso", 1, 50, 2);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.SANDSTONE, "Arenito Liso", 64, 3200, 2);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Arenito Vermelho ❮")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.RED_SANDSTONE, "Arenito Vermelho", 1, 50, 0);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.RED_SANDSTONE, "Arenito Vermelho", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Arenito Vermelho " +
                    "Talhado" +
                    " " +
                    "❮")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.RED_SANDSTONE, "Arenito Vermelho Talhado", 1, 50, 1);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.RED_SANDSTONE, "Arenito Vermelho Talhado", 64, 3200, 1);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Arenito Vermelho Liso " +
                    "❮")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.RED_SANDSTONE, "Arenito Vermelho Liso", 1, 50, 2);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.RED_SANDSTONE, "Arenito Vermelho Liso", 64, 3200, 2);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.GRAVEL)) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.GRAVEL, "Cascalho", 1, 50, 0);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.GRAVEL, "Cascalho", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Madeira de Carvalho")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.LOG, "Madeira de Carvalho", 1, 50, 0);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.LOG, "Madeira de Carvalho", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Madeira de Eucalipto")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.LOG, "Madeira de Eucalipto", 1, 50, 2);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.LOG, "Madeira de Eucalipto", 64, 3200, 2);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Madeira de Pinheiro")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.LOG, "Madeira de Pinheiro", 1, 50, 1);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.LOG, "Madeira de Pinheiro", 64, 3200, 1);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Madeira de Selva")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.LOG, "Madeira de Selva", 1, 50, 3);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.LOG, "Madeira de Selva", 64, 3200, 3);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Madeira Acácia")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.LOG_2, "Madeira Acácia", 1, 50, 0);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.LOG_2, "Madeira Acácia", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Madeira de Carvalho Escuro")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.LOG_2, "Madeira de Carvalho Escuro", 1, 50, 1);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.LOG_2, "Madeira de Carvalho Escuro", 64, 3200, 1);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cPágina anterior")) {
                player.openInventory(blocos);
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aPróxima página")) {
                blocos3 = new ShopAPI(player, "Blocos", 5, 3)
                        .addShopItem("blocos", Material.LEAVES, "Folhas de Carvalho", 2)
                        .addShopItem("blocos", Material.LEAVES, "Folhas de Pinheiro", 3, 1)
                        .addShopItem("blocos", Material.LEAVES, "Folhas de Eucalipto", 4, 2)
                        .addShopItem("blocos", Material.LEAVES, "Folhas de Selva", 5, 3)
                        .addShopItem("blocos", Material.GOLD_BLOCK, "Bloco de Ouro", 6, 2)

                        .addShopItem("blocos", Material.STAINED_CLAY, "Argila Branca", 11)
                        .addShopItem("blocos", Material.STAINED_CLAY, "Argila Laranja", 12, 1)
                        .addShopItem("blocos", Material.STAINED_CLAY, "Argila Magenta", 13, 2)
                        .addShopItem("blocos", Material.STAINED_CLAY, "Argila Azul Claro", 14, 3)
                        .addShopItem("blocos", Material.STAINED_CLAY, "Argila Amarela", 15, 4)

                        .addBackProx("anterior", 18)

                        .addShopItem("blocos", Material.STAINED_CLAY, "Argila Limão", 20, 5)
                        .addShopItem("blocos", Material.STAINED_CLAY, "Argila Cinza", 21, 7)
                        .addShopItem("blocos", Material.STAINED_CLAY, "Argila Cinza Claro", 22, 8)
                        .addShopItem("blocos", Material.STAINED_CLAY, "Argila Ciano", 23, 9)
                        .addShopItem("blocos", Material.STAINED_CLAY, "Argila Roxa", 24, 10)

                        .addShopItem("blocos", Material.STAINED_CLAY, "Argila Marrom", 29, 12)
                        .addShopItem("blocos", Material.STAINED_CLAY, "Argila Verde Escuro", 30, 13)
                        .addShopItem("blocos", Material.STAINED_CLAY, "Argila Vermelha", 31, 14)
                        .addShopItem("blocos", Material.STAINED_CLAY, "Argila Preta", 32, 15)
                        .addShopItem("blocos", Material.STAINED_CLAY, "Argila Azul", 33, 11)

                        .addShopItem("blocos", Material.SOUL_SAND, "Soul Sand", 38)
                        .toInventory();
            }
        } else if (e.getInventory().getTitle().equalsIgnoreCase("§8Loja - Blocos (3)")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Folhas de Carvalho")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.LEAVES, "Folhas de Carvalho", 1, 50, 0);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.LEAVES, "Folhas de Carvalho", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Folhas de Pinheiro")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.LEAVES, "Folhas de Pinheiro", 1, 50, 1);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.LEAVES, "Folhas de Pinheiro", 64, 3200, 1);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Folhas de Eucalipto")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.LEAVES, "Folhas de Eucalipto", 1, 50, 2);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.LEAVES, "Folhas de Eucalipto", 64, 3200, 2);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Folhas de Selva")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.LEAVES, "Folhas de Selva", 1, 50, 3);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.LEAVES, "Folhas de Selva", 64, 3200, 3);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.GOLD_BLOCK)) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.GOLD_BLOCK, "Bloco de Ouro", 1, 50, 0);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.GOLD_BLOCK, "Bloco de Ouro", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Argila Branca")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Branca", 1, 50, 0);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Branca", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Argila Laranja")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Laranja", 1, 50, 1);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Laranja", 64, 3200, 1);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Argila Magenta")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Magenta", 1, 50, 2);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Magenta", 64, 3200, 2);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Argila Azul Claro")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Azul Claro", 1, 50, 3);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Azul Claro", 64, 3200, 3);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Argila Amarela")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Amarela", 1, 50, 4);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Amarela", 64, 3200, 4);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Argila Limão")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Limão", 1, 50, 5);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Limão", 64, 3200, 5);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Argila Cinza")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Cinza", 1, 50, 7);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Cinza", 64, 3200, 7);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Argila Cinza Claro")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Cinza Claro", 1, 50, 8);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Cinza Claro", 64, 3200, 8);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Argila Ciano")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Ciano", 1, 50, 9);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Ciano", 64, 3200, 9);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Argila Roxa")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Roxa", 1, 50, 10);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Roxa", 64, 3200, 10);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Argila Marrom")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Marrom", 1, 50, 12);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Marrom", 64, 3200, 12);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Argila Verde Escuro")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Verde Escuro", 1, 50, 13);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Verde Escuro", 64, 3200, 13);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Argila Vermelha")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Vermelha", 1, 50, 14);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Vermelha", 64, 3200, 14);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Argila Preta")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Preta", 1, 50, 15);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Preta", 64, 3200, 15);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Argila Azul")) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Azul", 1, 50, 11);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.STAINED_CLAY, "Argila Azul", 64, 3200, 11);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.SOUL_SAND)) {
                if (e.isLeftClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 50) {
                        ShopAPI.sellBlocks(player, Material.SOUL_SAND, "Soul Sand", 1, 50, 0);
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                } else if (e.isRightClick()) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3200) {
                        ShopAPI.sellBlocks(player, Material.SOUL_SAND, "Soul Sand", 64, 3200, 0);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não tem coins o suficiente.");
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.INK_SACK)) {
                player.openInventory(blocos2);
            }
        } else if (e.getInventory().getTitle().contains("Corantes")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Corante Ciano ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCorantesUm(player, "Corante Ciano", 6);
                } else if (e.isRightClick()) {
                    ShopAPI.buyCorantesDezesseis(player, "Corante Ciano", 6);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Corante Azul Claro ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCorantesUm(player, "Corante Azul Claro", 12);
                } else if (e.isRightClick()) {
                    ShopAPI.buyCorantesDezesseis(player, "Corante Azul Claro", 12);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Corante Amarelo ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCorantesUm(player, "Corante Amarelo", 11);
                } else if (e.isRightClick()) {
                    ShopAPI.buyCorantesDezesseis(player, "Corante Amarelo", 11);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Corante Laranja ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCorantesUm(player, "Corante Laranja", 14);
                } else if (e.isRightClick()) {
                    ShopAPI.buyCorantesDezesseis(player, "Corante Laranja", 14);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Corante Preto ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCorantesUm(player, "Corante Preto", 0);
                } else if (e.isRightClick()) {
                    ShopAPI.buyCorantesDezesseis(player, "Corante Preto", 0);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Corante Verde Escuro " +
                    "❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCorantesUm(player, "Corante Verde Escuro", 2);
                } else if (e.isRightClick()) {
                    ShopAPI.buyCorantesDezesseis(player, "Corante Verde Escuro", 2);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Corante Vermelho ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCorantesUm(player, "Corante Vermelho", 2);
                } else if (e.isRightClick()) {
                    ShopAPI.buyCorantesDezesseis(player, "Corante Vermelho", 2);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Corante Branco ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCorantesUm(player, "Corante Branco", 7);
                } else if (e.isRightClick()) {
                    ShopAPI.buyCorantesDezesseis(player, "Corante Branco", 7);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Corante Cinza ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCorantesUm(player, "Corante Cinza", 8);
                } else if (e.isRightClick()) {
                    ShopAPI.buyCorantesDezesseis(player, "Corante Cinza", 8);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Corante Verde Claro ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCorantesUm(player, "Corante Verde Claro", 10);
                } else if (e.isRightClick()) {
                    ShopAPI.buyCorantesDezesseis(player, "Corante Verde Claro", 10);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Corante Rosa ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCorantesUm(player, "Corante Rosa", 9);
                } else if (e.isRightClick()) {
                    ShopAPI.buyCorantesDezesseis(player, "Corante Rosa", 9);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Corante Magenta ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCorantesUm(player, "Corante Magenta", 13);
                } else if (e.isRightClick()) {
                    ShopAPI.buyCorantesDezesseis(player, "Corante Magenta", 13);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Corante Roxo ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCorantesUm(player, "Corante Roxo", 5);
                } else if (e.isRightClick()) {
                    ShopAPI.buyCorantesDezesseis(player, "Corante Roxo", 5);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cVoltar")) {
                player.openInventory(loja);
            }
        } else if (e.getInventory().getTitle().contains("Farm")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Cactus ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyFarmUm(player, "Cactus", 0);
                } else if (e.isRightClick()) {
                    ShopAPI.buyFarmDezesseis(player, "Cactus", 0);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cVoltar")) {
                player.openInventory(loja);
            }
        } else if (e.getInventory().getTitle().contains("Decorações")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Baú do Fim ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyDecoUm(player, Material.ENDER_CHEST, "Baú do Fim", 0);
                } else if (e.isRightClick()) {
                    ShopAPI.buyDecoDezesseis(player, Material.ENDER_CHEST,"Baú do Fim", 0);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Bloco de Nota Musical " +
                    "❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyDecoUm(player, Material.NOTE_BLOCK, "Bloco de Nota Musical", 0);
                } else if (e.isRightClick()) {
                    ShopAPI.buyDecoDezesseis(player, Material.NOTE_BLOCK,"Bloco de Nota Musical", 0);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Moldura " +
                    "❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyDecoUm(player, Material.ITEM_FRAME, "Moldura", 0);
                } else if (e.isRightClick()) {
                    ShopAPI.buyDecoDezesseis(player, Material.ITEM_FRAME,"Moldura", 0);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Caldeirão " +
                    "❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyDecoUm(player, Material.CAULDRON_ITEM, "Caldeirão", 0);
                } else if (e.isRightClick()) {
                    ShopAPI.buyDecoDezesseis(player, Material.CAULDRON_ITEM,"Caldeirão", 0);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Suporte de Poção " +
                    "❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyDecoUm(player, Material.BREWING_STAND_ITEM, "Suporte de Poção", 0);
                } else if (e.isRightClick()) {
                    ShopAPI.buyDecoDezesseis(player, Material.BREWING_STAND_ITEM,"Suporte de Poção", 0);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Quadro " +
                    "❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyDecoUm(player, Material.PAINTING, "Quadro", 0);
                } else if (e.isRightClick()) {
                    ShopAPI.buyDecoDezesseis(player, Material.PAINTING,"Quadro", 0);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Estandarte Branco " +
                    "❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyDecoUm(player, Material.BANNER, "Estandarte Branco", 15);
                } else if (e.isRightClick()) {
                    ShopAPI.buyDecoDezesseis(player, Material.BANNER,"Estandarte Branco", 15);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Vitória-Régia " +
                    "❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyDecoUm(player, Material.BANNER, "Vitória-Régia", 0);
                } else if (e.isRightClick()) {
                    ShopAPI.buyDecoDezesseis(player, Material.BANNER,"Vitória-Régia", 0);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Allium " +
                    "❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyDecoUm(player, Material.BANNER, "Allium", 0);
                } else if (e.isRightClick()) {
                    ShopAPI.buyDecoDezesseis(player, Material.BANNER,"Allium", 0);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Tocha " +
                    "❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyDecoUm(player, Material.TORCH, "Tocha", 0);
                } else if (e.isRightClick()) {
                    ShopAPI.buyDecoDezesseis(player, Material.TORCH,"Tocha", 0);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Teia " +
                    "❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyDecoUm(player, Material.TORCH, "Teia", 0);
                } else if (e.isRightClick()) {
                    ShopAPI.buyDecoDezesseis(player, Material.TORCH,"Teia", 0);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Vaso " +
                    "❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyDecoUm(player, Material.FLOWER_POT_ITEM, "Vaso", 0);
                } else if (e.isRightClick()) {
                    ShopAPI.buyDecoDezesseis(player, Material.FLOWER_POT_ITEM,"Vaso", 0);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Roseira " +
                    "❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyDecoUm(player, Material.DOUBLE_PLANT, "Roseira", 4);
                } else if (e.isRightClick()) {
                    ShopAPI.buyDecoDezesseis(player, Material.DOUBLE_PLANT,"Roseira", 4);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Muda de Carvalho " +
                    "Escuro" +
                    " " +
                    "❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyDecoUm(player, Material.SAPLING, "Muda de Carvalho Escuro", 5);
                } else if (e.isRightClick()) {
                    ShopAPI.buyDecoDezesseis(player, Material.SAPLING,"Muda de Carvalho Escuro", 5);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Margarida ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyDecoUm(player, Material.SAPLING, "Margarida", 8);
                } else if (e.isRightClick()) {
                    ShopAPI.buyDecoDezesseis(player, Material.SAPLING,"Margarida", 8);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Abusto Morto ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyDecoUm(player, Material.DEAD_BUSH, "Abusto Morto", 0);
                } else if (e.isRightClick()) {
                    ShopAPI.buyDecoDezesseis(player, Material.DEAD_BUSH,"Abusto Morto", 0);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Orquidea ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyDecoUm(player, Material.RED_ROSE, "Orquidea", 1);
                } else if (e.isRightClick()) {
                    ShopAPI.buyDecoDezesseis(player, Material.RED_ROSE,"Orquidea", 1);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Dente de Leão ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyDecoUm(player, Material.YELLOW_FLOWER, "Dente de Leão", 0);
                } else if (e.isRightClick()) {
                    ShopAPI.buyDecoDezesseis(player, Material.YELLOW_FLOWER,"Dente de Leão", 0);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Muda de árvore de " +
                    "Selva" +
                    " " +
                    "❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyDecoUm(player, Material.SAPLING, "Muda de árvore de Selva", 3);
                } else if (e.isRightClick()) {
                    ShopAPI.buyDecoDezesseis(player, Material.SAPLING,"Muda de árvore de Selva", 3);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Muda de Acácia ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyDecoUm(player, Material.SAPLING, "Muda de Acácia", 4);
                } else if (e.isRightClick()) {
                    ShopAPI.buyDecoDezesseis(player, Material.SAPLING,"Muda de Acácia", 4);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Cabeça de Zombie ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyDecoUm(player, Material.SKULL_ITEM, "Cabeça de Zombie", 2);
                } else if (e.isRightClick()) {
                    ShopAPI.buyDecoDezesseis(player, Material.SKULL_ITEM,"Cabeça de Zombie", 2);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Cabeça de Creeper ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyDecoUm(player, Material.SKULL_ITEM, "Cabeça de Creeper", 2);
                } else if (e.isRightClick()) {
                    ShopAPI.buyDecoDezesseis(player, Material.SKULL_ITEM,"Cabeça de Creeper", 2);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Balde de Lava ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyDecoUm(player, Material.LAVA_BUCKET, "Balde de Lava", 0);
                } else if (e.isRightClick()) {
                    ShopAPI.buyDecoDezesseis(player, Material.LAVA_BUCKET,"Balde de Lava", 0);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Balde de Água ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyDecoUm(player, Material.WATER_BUCKET, "Balde de Água", 0);
                } else if (e.isRightClick()) {
                    ShopAPI.buyDecoDezesseis(player, Material.WATER_BUCKET,"Balde de Água", 0);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cVoltar")) {
                player.openInventory(loja);
            }
        } else if (e.getInventory().getTitle().contains("Combate")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Capacete ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCombateUm(player, Material.DIAMOND_CHESTPLATE, "Capacete", 0, 2500);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Peitoral ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCombateUm(player, Material.DIAMOND_CHESTPLATE, "Peitoral", 0, 2500);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Calça ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCombateUm(player, Material.DIAMOND_LEGGINGS, "Calça", 0, 2500);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Bota ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCombateUm(player, Material.DIAMOND_BOOTS, "Bota", 0, 2500);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Espada ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCombateUm(player, Material.DIAMOND_SWORD, "Espada", 0, 2500);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Pá ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCombateUm(player, Material.DIAMOND_SPADE, "Pá", 0, 2500);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Maçã Dourada ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCombateUm(player, Material.GOLDEN_APPLE, "Maçã Dourada", 0, 156);
                } else if (e.isRightClick()) {
                    ShopAPI.buyCombateDezesseis(player, Material.GOLDEN_APPLE, "Maçã Dourada", 0);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Maçã Dourada Encantada" +
                    " " +
                    "❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCombateUm(player, Material.GOLDEN_APPLE, "Maçã Dourada Encantada", 1, 156);
                } else if (e.isRightClick()) {
                    ShopAPI.buyCombateDezesseis(player, Material.GOLDEN_APPLE, "Maçã Dourada Encantada", 1);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Machado ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCombateUm(player, Material.DIAMOND_AXE, "Machado", 0, 2500);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Enxada ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCombateUm(player, Material.DIAMOND_HOE, "Enxada", 0, 2500);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Picareta ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCombateUm(player, Material.DIAMOND_PICKAXE, "Picareta", 0, 2500);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ XP ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCombateUm(player, Material.EXP_BOTTLE, "XP", 0, 156);
                } else if (e.isRightClick()) {
                    ShopAPI.buyCombateDezesseis(player, Material.EXP_BOTTLE, "XP", 0);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Poção de Velocidade ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCombateUm(player, Material.POTION, "Poção de Velocidade", 8226, 156);
                } else if (e.isRightClick()) {
                    ShopAPI.buyCombateDezesseis(player, Material.POTION, "Poção de Velocidade", 8226);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Poção de Força ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyCombateUm(player, Material.POTION, "Poção de Força", 8233, 156);
                } else if (e.isRightClick()) {
                    ShopAPI.buyCombateDezesseis(player, Material.POTION, "Poção de Força", 8233);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cVoltar")) {
                player.openInventory(loja);
            }
        } else if (e.getInventory().getTitle().equalsIgnoreCase("§8Terrenos")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSlot #01")) {
                WorldCreator wc =
                        new WorldCreator(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/1");
                wc.generator(new WorldManager());
                wc.createWorld();
                player.closeInventory();
                SchematicAPI.loadSchematic(player, "1");
                player.sendMessage("§aVocê claimou este terreno com sucesso.");
                CustomPlayer.setPlotme(player, "TERRENO1");
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aTerreno 01")) {
                WorldCreator wc =
                        new WorldCreator(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/1");
                wc.generator(new WorldManager());
                wc.createWorld();

                player.teleport(new Location(Bukkit.getWorld(Rankup.getInstance().getDataFolder() + "/Terrenos/" +
                 player.getName() + "/1"), 156.746, 30, 149.879));
                player.sendMessage("§aVocê teleportou para o Terreno #01 com sucesso.");
            }

            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSlot #02")) {
                WorldCreator wc =
                        new WorldCreator(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/2");
                wc.generator(new WorldManager());
                wc.createWorld();
                player.closeInventory();
                SchematicAPI.loadSchematic(player, "2");
                player.sendMessage("§aVocê claimou este terreno com sucesso.");
                CustomPlayer.setPlotme(player, "TERRENO2");
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aTerreno 02")) {
                WorldCreator wc =
                        new WorldCreator(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/2");
                wc.generator(new WorldManager());
                wc.createWorld();

                player.teleport(new Location(Bukkit.getWorld(Rankup.getInstance().getDataFolder() + "/Terrenos/" +
                        player.getName() + "/2"), 156.746, 30, 149.879));
                player.sendMessage("§aVocê teleportou para o Terreno #02 com sucesso.");
            }

            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSlot #03")) {
                WorldCreator wc =
                        new WorldCreator(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/3");
                wc.generator(new WorldManager());
                wc.createWorld();
                player.closeInventory();
                SchematicAPI.loadSchematic(player, "3");
                player.sendMessage("§aVocê claimou este terreno com sucesso.");
                CustomPlayer.setPlotme(player, "TERRENO3");
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aTerreno 03")) {
                WorldCreator wc =
                        new WorldCreator(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/3");
                wc.generator(new WorldManager());
                wc.createWorld();

                player.teleport(new Location(Bukkit.getWorld(Rankup.getInstance().getDataFolder() + "/Terrenos/" +
                        player.getName() + "/3"), 156.746, 30, 149.879));
                player.sendMessage("§aVocê teleportou para o Terreno #03 com sucesso.");
            }

            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSlot #04")) {
                WorldCreator wc =
                        new WorldCreator(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/4");
                wc.generator(new WorldManager());
                wc.createWorld();
                player.closeInventory();
                SchematicAPI.loadSchematic(player, "4");
                player.sendMessage("§aVocê claimou este terreno com sucesso.");
                CustomPlayer.setPlotme(player, "TERRENO4");
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aTerreno 04")) {
                WorldCreator wc =
                        new WorldCreator(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/4");
                wc.generator(new WorldManager());
                wc.createWorld();

                player.teleport(new Location(Bukkit.getWorld(Rankup.getInstance().getDataFolder() + "/Terrenos/" +
                        player.getName() + "/4"), 156.746, 30, 149.879));
                player.sendMessage("§aVocê teleportou para o Terreno #04 com sucesso.");
            }

            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSlot #05")) {
                WorldCreator wc =
                        new WorldCreator(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/5");
                wc.generator(new WorldManager());
                wc.createWorld();
                player.closeInventory();
                SchematicAPI.loadSchematic(player, "5");
                player.sendMessage("§aVocê claimou este terreno com sucesso.");
                CustomPlayer.setPlotme(player, "TERRENO5");
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aTerreno 05")) {
                WorldCreator wc =
                        new WorldCreator(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/5");
                wc.generator(new WorldManager());
                wc.createWorld();

                player.teleport(new Location(Bukkit.getWorld(Rankup.getInstance().getDataFolder() + "/Terrenos/" +
                        player.getName() + "/5"), 156.746, 30, 149.879));
                player.sendMessage("§aVocê teleportou para o Terreno #05 com sucesso.");
            }

            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSlot #06")) {
                WorldCreator wc =
                        new WorldCreator(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/6");
                wc.generator(new WorldManager());
                wc.createWorld();
                player.closeInventory();
                SchematicAPI.loadSchematic(player, "6");
                player.sendMessage("§aVocê claimou este terreno com sucesso.");
                CustomPlayer.setPlotme(player, "TERRENO6");
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aTerreno 06")) {
                WorldCreator wc =
                        new WorldCreator(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/6");
                wc.generator(new WorldManager());
                wc.createWorld();

                player.teleport(new Location(Bukkit.getWorld(Rankup.getInstance().getDataFolder() + "/Terrenos/" +
                        player.getName() + "/6"), 156.746, 30, 149.879));
                player.sendMessage("§aVocê teleportou para o Terreno #06 com sucesso.");
            }

        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        e.setCancelled(true);
        e.getPlayer().sendMessage("§cVocê não pode dropar itens aqui.");
    }

}
