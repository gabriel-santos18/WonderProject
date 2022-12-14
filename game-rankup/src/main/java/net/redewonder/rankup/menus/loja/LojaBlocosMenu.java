package net.redewonder.rankup.menus.loja;

import net.redewonder.rankup.api.ShopAPI;
import net.redewonder.rankup.listeners.PlayerListeners;
import net.redewonder.rankup.sql.CustomPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class LojaBlocosMenu implements Listener {

    Inventory blocos2;
    Inventory blocos3;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

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
                player.openInventory(PlayerListeners.getLoja());
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
                player.openInventory(LojaMenu.getBlocos());
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
        }
    }
}
