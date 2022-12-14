package net.redewonder.rankup.menus.loja;

import net.redewonder.rankup.api.ShopAPI;
import net.redewonder.rankup.listeners.PlayerListeners;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class LojaDecoraçõesMenu implements Listener {


    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        if (e.getInventory().getTitle().contains("Decorações")) {
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
                player.openInventory(PlayerListeners.getLoja());
            }
        }
    }
}
