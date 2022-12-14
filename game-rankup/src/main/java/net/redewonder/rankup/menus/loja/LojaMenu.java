package net.redewonder.rankup.menus.loja;

import net.redewonder.rankup.api.ShopAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class LojaMenu implements Listener {

    Inventory corantes;
    Inventory farm;
    Inventory decoracoes;
    Inventory combate;
    private static Inventory blocos;

    @EventHandler
    public void onClickInventory(InventoryClickEvent e) {
        if (e.getInventory().getTitle().equalsIgnoreCase("§8Loja (Categorias)")) {
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
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
    }

    public static Inventory getBlocos() {
        return blocos;
    }
}
