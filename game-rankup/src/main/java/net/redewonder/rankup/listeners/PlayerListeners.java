package net.redewonder.rankup.listeners;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import me.imfighting.bukkit.api.TablistAPI;
import me.imfighting.bukkit.inventory.InventoryBuilder;
import me.imfighting.bukkit.inventory.ItemBuilder;
import net.redewonder.rankup.Rankup;
import net.redewonder.rankup.group.Groups;
import net.redewonder.rankup.managers.LocationsManager;
import net.redewonder.rankup.managers.PlayerManager;
import net.redewonder.rankup.managers.ScoreboardManager;
import net.redewonder.rankup.sql.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
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
            if (itemStack.getType().equals(Material.DIAMOND_PICKAXE)) {
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
        } else if (e.getInventory().getTitle().equalsIgnoreCase("§8Membro (Semanal)") || (e.getInventory().getTitle().equalsIgnoreCase("§8Cloud (Semanal)")) || (e.getInventory().getTitle().equalsIgnoreCase("§8Rain (Semanal)")) || (e.getInventory().getTitle().equalsIgnoreCase("§8Water (Semanal)")) || (e.getInventory().getTitle().equalsIgnoreCase("§8Warps do servidor"))) {
            e.setCancelled(true);
        }

        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§l➦ Spawn")) {
            if (!player.getWorld().getName().equalsIgnoreCase("world")) {
                player.teleport(LocationsManager.getLocation(player, "Spawn"));
                player.closeInventory();
                player.sendMessage("§aVocê foi teleportado para o spawn com sucesso.");

                for (ItemStack itemStack : player.getInventory().getContents()) {
                    if (itemStack.getType().equals(Material.DIAMOND_PICKAXE)) {
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
                    player.getInventory().setItem(0,
                            new ItemBuilder(Material.DIAMOND_PICKAXE)
                                    .addEnchantmant(Enchantment.DURABILITY, 3)
                                    .addEnchantmant(Enchantment.LOOT_BONUS_BLOCKS, 2)
                                    .addEnchantmant(Enchantment.DIG_SPEED, 5)
                                    .toItemStack());
                    player.sendMessage("§aVocê foi teleportado para a mineração com sucesso.");
                } else {
                    player.closeInventory();
                    player.sendMessage("§cLimpe seu inventário antes de entrar na mineração.");
                }
            } else {
                player.closeInventory();
                player.sendMessage("§cVocê já está na área de mineração.");
            }
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        e.setCancelled(true);
        e.getPlayer().sendMessage("§cVocê não pode dropar itens aqui.");
    }

}
