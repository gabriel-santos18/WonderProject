package net.redewonder.rankup.listeners;

import me.imfighting.bukkit.api.ActionBarAPI;
import me.imfighting.bukkit.inventory.ItemBuilder;
import net.redewonder.rankup.Rankup;
import net.redewonder.rankup.sql.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

public class WorldListeners implements Listener {

    ArmorStand standCogumelo;
    ArmorStand standAbobora;
    ArmorStand standPrismarinho;
    ArmorStand standObsidian;
    ArmorStand standOuro;

    @EventHandler
    public void onWorldBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if (!player.isOp()) {
            e.setCancelled(true);
        }

        if (player.getWorld().getName().equalsIgnoreCase(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/1") ||
                player.getWorld().getName().equalsIgnoreCase(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/2") ||
                player.getWorld().getName().equalsIgnoreCase(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/3") ||
                player.getWorld().getName().equalsIgnoreCase(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/4") ||
                player.getWorld().getName().equalsIgnoreCase(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/5") ||
                player.getWorld().getName().equalsIgnoreCase(Rankup.getInstance().getDataFolder() + "/Terrenos/" + player.getName() + "/6")) {
            e.setCancelled(false);
            ItemStack is = player.getInventory().getItemInHand();
            if (is.getItemMeta().getDisplayName().equalsIgnoreCase("§bMáquina de Cogumelo")) {
                String[] lines = new String[]{
                        "§aMáquina de Cogumelo",
                };
                Location location = e.getBlock().getLocation();
                for (String line : lines) {
                    standCogumelo = (ArmorStand) player.getWorld().spawnEntity(location.subtract(-0.6, 0.70, -0.2),
                            EntityType.ARMOR_STAND);
                    standCogumelo.setVisible(false);
                    standCogumelo.setGravity(false);
                    standCogumelo.setBodyPose(new EulerAngle(-15f ,0f , 122.15f));

                    standCogumelo.setCustomNameVisible(true);
                    standCogumelo.setCustomName(line);
                }

                if (!Rankup.getInstance().getConfig().contains(player.getName() + ".MaquinaCogumelo")) {
                    Rankup.getInstance().getConfig().set(player.getName() + ".MaquinaCogumelo.Enable", true);
                    Rankup.getInstance().getConfig().set(player.getName() + ".MaquinaCogumelo.Combustiveis", 0);
                    Rankup.getInstance().getConfig().set(player.getName() + ".MaquinaCogumelo.Recolher", 0);
                    Rankup.getInstance().saveConfig();
                }

                player.sendMessage("§aVocê colocou a §bMáquina de Cogumelo §acom sucesso.");
            } else if (is.getItemMeta().getDisplayName().equalsIgnoreCase("§bMáquina de Abóbora")) {
                String[] lines = new String[]{
                        "§aMáquina de Abóbora",
                };
                Location location = e.getBlock().getLocation();
                for (String line : lines) {
                    standAbobora = (ArmorStand) player.getWorld().spawnEntity(location.subtract(-0.3, 0.70, -0.2),
                            EntityType.ARMOR_STAND);
                    standAbobora.setVisible(false);
                    standAbobora.setGravity(false);
                    standAbobora.setBodyPose(new EulerAngle(-15f ,0f , 122.15f));

                    standAbobora.setCustomNameVisible(true);
                    standAbobora.setCustomName(line);
                }

                if (!Rankup.getInstance().getConfig().contains(player.getName() + ".MaquinaAbobora")) {
                    Rankup.getInstance().getConfig().set(player.getName() + ".MaquinaAbobora.Enable", true);
                    Rankup.getInstance().getConfig().set(player.getName() + ".MaquinaAbobora.Combustiveis", 0);
                    Rankup.getInstance().getConfig().set(player.getName() + ".MaquinaAbobora.Recolher", 0);
                    Rankup.getInstance().saveConfig();
                }

                player.sendMessage("§aVocê colocou a §bMáquina de Abóbora §acom sucesso.");
            } else if (is.getItemMeta().getDisplayName().equalsIgnoreCase("§bMáquina de Prismarinho")) {
                String[] lines = new String[]{
                        "§aMáquina de Prismarinho",
                };
                Location location = e.getBlock().getLocation();
                for (String line : lines) {
                    standPrismarinho = (ArmorStand) player.getWorld().spawnEntity(location.subtract(-0.3, 0, -0.2),
                            EntityType.ARMOR_STAND);
                    standPrismarinho.setVisible(false);
                    standPrismarinho.setGravity(false);

                    standPrismarinho.setCustomNameVisible(true);
                    standPrismarinho.setCustomName(line);
                }

                if (!Rankup.getInstance().getConfig().contains(player.getName() + ".MaquinaPrismarinho")) {
                    Rankup.getInstance().getConfig().set(player.getName() + ".MaquinaPrismarinho.Enable", true);
                    Rankup.getInstance().getConfig().set(player.getName() + ".MaquinaPrismarinho.Combustiveis", 0);
                    Rankup.getInstance().getConfig().set(player.getName() + ".MaquinaPrismarinho.Recolher", 0);
                    Rankup.getInstance().saveConfig();
                }

                player.sendMessage("§aVocê colocou a §bMáquina de Prismarinho §acom sucesso.");
            } else if (is.getItemMeta().getDisplayName().equalsIgnoreCase("§bMáquina de Obsidian")) {
                String[] lines = new String[]{
                        "§aMáquina de Obsidian",
                };
                Location location = e.getBlock().getLocation();
                for (String line : lines) {
                    standObsidian = (ArmorStand) player.getWorld().spawnEntity(location.subtract(-0.3, 0.70, -0.2),
                            EntityType.ARMOR_STAND);
                    standObsidian.setVisible(false);
                    standObsidian.setGravity(false);
                    standObsidian.setBodyPose(new EulerAngle(-15f ,0f , 122.15f));

                    standObsidian.setCustomNameVisible(true);
                    standObsidian.setCustomName(line);
                }

                if (!Rankup.getInstance().getConfig().contains(player.getName() + ".MaquinaObsidian")) {
                    Rankup.getInstance().getConfig().set(player.getName() + ".MaquinaObsidian.Enable", true);
                    Rankup.getInstance().getConfig().set(player.getName() + ".MaquinaObsidian.Combustiveis", 0);
                    Rankup.getInstance().getConfig().set(player.getName() + ".MaquinaObsidian.Recolher", 0);
                    Rankup.getInstance().saveConfig();
                }

                player.sendMessage("§aVocê colocou a §bMáquina de Obsidian §acom sucesso.");
            } else if (is.getItemMeta().getDisplayName().equalsIgnoreCase("§bMáquina de Ouro")) {
                String[] lines = new String[]{
                        "§aMáquina de Ouro",
                };
                Location location = e.getBlock().getLocation();
                for (String line : lines) {
                    standOuro = (ArmorStand) player.getWorld().spawnEntity(location.subtract(-0.3, 0.70, -0.2),
                            EntityType.ARMOR_STAND);
                    standOuro.setVisible(false);
                    standOuro.setGravity(false);
                    standOuro.setBodyPose(new EulerAngle(-15f ,0f , 122.15f));

                    standOuro.setCustomNameVisible(true);
                    standOuro.setCustomName(line);
                }

                if (!Rankup.getInstance().getConfig().contains(player.getName() + ".MaquinaOuro")) {
                    Rankup.getInstance().getConfig().set(player.getName() + ".MaquinaOuro.Enable", true);
                    Rankup.getInstance().getConfig().set(player.getName() + ".MaquinaOuro.Combustiveis", 0);
                    Rankup.getInstance().getConfig().set(player.getName() + ".MaquinaOuro.Recolher", 0);
                    Rankup.getInstance().saveConfig();
                }

                player.sendMessage("§aVocê colocou a §bMáquina de Ouro §acom sucesso.");
            }
        }
    }

    @EventHandler
    public void onWorldBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (!player.isOp()) {
            e.setCancelled(true);
        }

        if (e.getBlock().getType().equals(Material.HUGE_MUSHROOM_2)) {
            standCogumelo.remove();
            e.getBlock().setType(Material.AIR);
            player.getWorld().dropItemNaturally(e.getBlock().getLocation(),
                    new ItemBuilder(Material.HUGE_MUSHROOM_2)
                            .setDisplayName("§bMáquina de Cogumelo")
                            .setLore("", "§7Coloque esta máquina no terreno.")
                            .toItemStack());
        } else if (e.getBlock().getType().equals(Material.PUMPKIN)) {
            standAbobora.remove();
            e.getBlock().setType(Material.AIR);
            player.getWorld().dropItemNaturally(e.getBlock().getLocation(),
                    new ItemBuilder(Material.PUMPKIN)
                            .setDisplayName("§bMáquina de Abóbora")
                            .setLore("", "§7Coloque esta máquina no terreno.")
                            .toItemStack());
        } else if (e.getBlock().getType().equals(Material.PRISMARINE)) {
            standPrismarinho.remove();
            e.getBlock().setType(Material.AIR);
            player.getWorld().dropItemNaturally(e.getBlock().getLocation(),
                    new ItemBuilder(Material.PRISMARINE)
                            .setDisplayName("§bMáquina de Prismarinho")
                            .setLore("", "§7Coloque esta máquina no terreno.")
                            .toItemStack());
        } else if (e.getBlock().getType().equals(Material.OBSIDIAN)) {
            standObsidian.remove();
            e.getBlock().setType(Material.AIR);
            player.getWorld().dropItemNaturally(e.getBlock().getLocation(),
                    new ItemBuilder(Material.OBSIDIAN)
                            .setDisplayName("§bMáquina de Obsidian")
                            .setLore("", "§7Coloque esta máquina no terreno.")
                            .toItemStack());
        } else if (e.getBlock().getType().equals(Material.GOLD_BLOCK)) {
            standOuro.remove();
            e.getBlock().setType(Material.AIR);
            player.getWorld().dropItemNaturally(e.getBlock().getLocation(),
                    new ItemBuilder(Material.GOLD_BLOCK)
                            .setDisplayName("§bMáquina de Ouro")
                            .setLore("", "§7Coloque esta máquina no terreno.")
                            .toItemStack());
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
                player.getScoreboard().getTeam("money")
                        .setSuffix((CustomPlayer.getCoins(player.getName()) > 999 &&
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
                                                                                CustomPlayer.getCoins(player.getName()) > 9999999999L &&
                                                                                        CustomPlayer.getCoins(player.getName()) <= 99999999999L ?
                                                                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                                2) + "B" :
                                                                                        CustomPlayer.getCoins(player.getName()) > 99999999999L &&
                                                                                                CustomPlayer.getCoins(player.getName()) <= 999999999999L ?
                                                                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                                        3) + "B" :
                                                                                                CustomPlayer.getCoins(player.getName()) > 99999999999L &&
                                                                                                        CustomPlayer.getCoins(player.getName()) <= 999999999999L ?
                                                                                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                                                3) + "B" :
                                                                                                        CustomPlayer.getCoins(player.getName()) > 999999999999L &&
                                                                                                                CustomPlayer.getCoins(player.getName()) <= 9999999999999L ?
                                                                                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                                                        1) + "T" :
                                                                                                                CustomPlayer.getCoins(player.getName()) > 9999999999999L &&
                                                                                                                        CustomPlayer.getCoins(player.getName()) <= 99999999999999L ?
                                                                                                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                                                                2) +
                                                                                                                                "T" :
                                                                                                                        CustomPlayer.getCoins(player.getName()) > 99999999999999L &&
                                                                                                                                CustomPlayer.getCoins(player.getName()) <= 999999999999999L ?
                                                                                                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                                                                        3) +
                                                                                                                                        "T" :
                                                                                                                                "§a$" + CustomPlayer.getCoins(player.getName())));
            }
        }

    }

    public static long PegaPrimirosDigitos(long valor, long digitos) {
        digitos = Math.max(1, digitos);
        long positivo = Math.abs(valor);
        String texto = String.valueOf(positivo);
        if (digitos > texto.length()) {
            return valor;
        }
        return Long.parseLong(texto.substring(0, Math.toIntExact(digitos))) * Long.signum(valor);
    }

    @EventHandler
    public void onWorldWeatherChange(WeatherChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onWorldCreatureSpawn(CreatureSpawnEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            if (!(e.getEntity() instanceof ItemFrame)) {
                if (!(e.getEntity() instanceof ArmorStand)) {
                    e.setCancelled(true);
                    e.getEntity().remove();
                }
            }
        }
    }
}
