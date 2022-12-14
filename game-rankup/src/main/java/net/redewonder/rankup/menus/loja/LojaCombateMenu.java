package net.redewonder.rankup.menus.loja;

import net.redewonder.rankup.api.ShopAPI;
import net.redewonder.rankup.listeners.PlayerListeners;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class LojaCombateMenu implements Listener {



    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        if (e.getInventory().getTitle().contains("Combate")) {
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
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Maçã Dourada Encantada" + " " + "❮")) {
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
                player.openInventory(PlayerListeners.getLoja());
            }
        }
    }
}
