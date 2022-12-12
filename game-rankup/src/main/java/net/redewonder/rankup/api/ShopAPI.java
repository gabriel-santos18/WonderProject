package net.redewonder.rankup.api;

import me.imfighting.bukkit.inventory.InventoryBuilder;
import me.imfighting.bukkit.inventory.ItemBuilder;
import net.redewonder.rankup.sql.CustomPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopAPI {

    Inventory inventory;

    public ShopAPI(Player player, String categoria, int size, int pag) {
        inventory = new InventoryBuilder(size, "§8Loja - " + categoria + " (" + pag + ")").toInventory();
        player.openInventory(inventory);

    }

    public ShopAPI addBackProx(String backProx, int slot) {
        if (backProx == "voltar") {
            inventory.setItem(slot, new ItemBuilder(Material.ARROW).setDisplayName("§cVoltar").toItemStack());
        } else if (backProx == "prox") {
            inventory.setItem(slot, new ItemBuilder(Material.INK_SACK).setDurability(Material.INK_SACK, 10).setDisplayName("§aPróxima página").toItemStack());
        } else if (backProx == "anterior") {
            inventory.setItem(slot, new ItemBuilder(Material.INK_SACK).setDurability(Material.INK_SACK, 8).setDisplayName("§cPágina anterior").toItemStack());
        }
        return this;
    }

    public ShopAPI addShopItem(String categoria, Material item, String nameItem, int slot) {
        if (categoria == "blocos") {
            inventory.setItem(slot, new ItemBuilder(item).setDisplayName("§e❯ " + nameItem + " ❮").setLore("",
                    "§fCusto §e(1x)§f: §a50", "§fCusto §e(64x)§f: §a3.200", "", "§fClique §7esquerdo " + "§fpara " +
                            "comprar 1x", "§fClique §7direito §fpara comprar 64x", "").toItemStack());
        } else if (categoria == "corantes") {
            inventory.setItem(slot, new ItemBuilder(item).setDisplayName("§e❯ " + nameItem + " ❮").setLore("",
                    "§fCusto §e(1x)§f: §a30", "§fCusto §e(16x)§f: §a480", "", "§fClique §7esquerdo " + "§fpara " +
                            "comprar 1x", "§fClique §7direito §fpara comprar 16x", "").toItemStack());
        } else if (categoria == "farm") {
            inventory.setItem(slot,
                    new ItemBuilder(item).setDisplayName("§e❯ " + nameItem + " ❮").setLore("", "§fCusto §e(1x)§f: §a6" +
                            ".250", "§fCusto §e(16x)§f: §a100.000", "", "§fClique §7esquerdo " + "§fpara comprar 1x", "§fClique §7direito §fpara comprar 16x", "").toItemStack());
        } else if (categoria == "decoracoes") {
            inventory.setItem(slot,
                    new ItemBuilder(item).setDisplayName("§e❯ " + nameItem + " ❮").setLore("", "§fCusto §e(1x)§f: " +
                            "§a156", "§fCusto §e(16x)§f: §a2.500", "", "§fClique §7esquerdo " + "§fpara comprar 1x",
                            "§fClique §7direito §fpara comprar 16x", "").toItemStack());
        }
        return this;
    }

    public ShopAPI addShopItem(String categoria, Material item, String nameItem, int slot, int durability) {
        if (categoria == "blocos") {
            inventory.setItem(slot,
                    new ItemBuilder(item).setDurability(item, durability).setDisplayName("§e❯ " + nameItem + " ❮").setLore("", "§fCusto §e(1x)§f: §a50", "§fCusto §e(64x)§f: §a3.200", "", "§fClique §7esquerdo " + "§fpara comprar 1x", "§fClique §7direito §fpara comprar 64x", "").toItemStack());
        } else if (categoria == "corantes") {
            inventory.setItem(slot,
                    new ItemBuilder(item).setDurability(item, durability).setDisplayName("§e❯ " + nameItem + " ❮").setLore("", "§fCusto §e(1x)§f: §a30", "§fCusto §e(16x)§f: §a480", "", "§fClique §esquerdo " + "§fpara comprar 1x", "§fClique §7direito §fpara comprar 16x", "").toItemStack());
        } else if (categoria == "farm") {
            inventory.setItem(slot,
                    new ItemBuilder(item).setDurability(item, durability).setDisplayName("§e❯ " + nameItem + " ❮").setLore("", "§fCusto §e(1x)§f: §a6.250", "§fCusto §e(16x)§f: §a100.000", "", "§fClique §esquerdo " + "§fpara comprar 1x", "§fClique §7direito §fpara comprar 16x", "").toItemStack());
        } else if (categoria == "decoracoes") {
            inventory.setItem(slot,
                    new ItemBuilder(item).setDurability(item, durability).setDisplayName("§e❯ " + nameItem + " ❮").setLore("",
                            "§fCusto §e(1x)§f:" +
                                    " " +
                            "§a156", "§fCusto §e(16x)§f: §a2.500", "", "§fClique §7esquerdo " + "§fpara comprar 1x",
                            "§fClique §7direito §fpara comprar 16x", "").toItemStack());
        }
        return this;
    }

    public ShopAPI addShopItemCombate(Material item, String nameItem, int slot) {
        if (nameItem == "Capacete") {
            inventory.setItem(slot,
                    new ItemBuilder(item)
                            .setDisplayName("§e❯ " + nameItem + " ❮")
                            .setLore("",
                                    "§fCusto §e(1x)§f: §a2.500",
                                    "",
                                    "§fClique §7esquerdo §fpara comprar 1x",
                                    "")
                            .toItemStack());
        } else if (nameItem == "Peitoral") {
            inventory.setItem(slot,
                    new ItemBuilder(item)
                            .setDisplayName("§e❯ " + nameItem + " ❮")
                            .setLore("",
                                    "§fCusto §e(1x)§f: §a2.500",
                                    "",
                                    "§fClique §7esquerdo §fpara comprar 1x",
                                    "")
                            .toItemStack());
        } else if (nameItem == "Calça") {
            inventory.setItem(slot,
                    new ItemBuilder(item)
                            .setDisplayName("§e❯ " + nameItem + " ❮")
                            .setLore("",
                                    "§fCusto §e(1x)§f: §a2.500",
                                    "",
                                    "§fClique §7esquerdo §fpara comprar 1x",
                                    "")
                            .toItemStack());
        } else if (nameItem == "Bota") {
            inventory.setItem(slot,
                    new ItemBuilder(item)
                            .setDisplayName("§e❯ " + nameItem + " ❮")
                            .setLore("",
                                    "§fCusto §e(1x)§f: §a2.500",
                                    "",
                                    "§fClique §7esquerdo §fpara comprar 1x",
                                    "")
                            .toItemStack());
        } else if (nameItem == "Espada") {
            inventory.setItem(slot,
                    new ItemBuilder(item)
                            .setDisplayName("§e❯ " + nameItem + " ❮")
                            .setLore("",
                                    "§fCusto §e(1x)§f: §a2.500",
                                    "",
                                    "§fClique §7esquerdo §fpara comprar 1x",
                                    "")
                            .toItemStack());
        } else if (nameItem == "Pá") {
            inventory.setItem(slot,
                    new ItemBuilder(item)
                            .setDisplayName("§e❯ " + nameItem + " ❮")
                            .setLore("",
                                    "§fCusto §e(1x)§f: §a2.500",
                                    "",
                                    "§fClique §7esquerdo §fpara comprar 1x",
                                    "")
                            .toItemStack());
        } else if (nameItem == "Maçã Dourada") {
            inventory.setItem(slot,
                    new ItemBuilder(item)
                            .setDisplayName("§e❯ " + nameItem + " ❮")
                            .setLore("",
                                    "§fCusto §e(1x)§f: §a156",
                                    "§fCusto §e(16x)§f: §a2.500",
                                    "",
                                    "§fClique §7direito §fpara comprar 1x",
                                    "§fClique §7esquerdo §fpara comprar 16x",
                                    "")
                            .toItemStack());
        } else if (nameItem == "Maçã Dourada Encantada") {
            inventory.setItem(slot,
                    new ItemBuilder(item, 1, 1)
                            .setDisplayName("§e❯ " + nameItem + " ❮")
                            .setLore("",
                                    "§fCusto §e(1x)§f: §a156",
                                    "§fCusto §e(16x)§f: §a2.500",
                                    "",
                                    "§fClique §7direito §fpara comprar 1x",
                                    "§fClique §7esquerdo §fpara comprar 16x",
                                    "")
                            .toItemStack());
        } else if (nameItem == "Machado") {
            inventory.setItem(slot,
                    new ItemBuilder(item)
                            .setDisplayName("§e❯ " + nameItem + " ❮")
                            .setLore("",
                                    "§fCusto §e(1x)§f: §a2.500",
                                    "",
                                    "§fClique §7esquerdo §fpara comprar 1x",
                                    "")
                            .toItemStack());
        } else if (nameItem == "Enxada") {
            inventory.setItem(slot,
                    new ItemBuilder(item)
                            .setDisplayName("§e❯ " + nameItem + " ❮")
                            .setLore("",
                                    "§fCusto §e(1x)§f: §a2.500",
                                    "",
                                    "§fClique §7esquerdo §fpara comprar 1x",
                                    "")
                            .toItemStack());
        } else if (nameItem == "Picareta") {
            inventory.setItem(slot,
                    new ItemBuilder(item)
                            .setDisplayName("§e❯ " + nameItem + " ❮")
                            .setLore("",
                                    "§fCusto §e(1x)§f: §a2.500",
                                    "",
                                    "§fClique §7esquerdo §fpara comprar 1x",
                                    "")
                            .toItemStack());
        } else if (nameItem == "XP") {
            inventory.setItem(slot,
                    new ItemBuilder(item)
                            .setDisplayName("§e❯ " + nameItem + " ❮")
                            .setLore("",
                                    "§fCusto §e(1x)§f: §a156",
                                    "§fCusto §e(64x)§f: §a2.500",
                                    "",
                                    "§fClique §7direito §fpara comprar 1x",
                                    "§fClique §7esquerdo §fpara comprar 64x",
                                    "")
                            .toItemStack());
        } else if (nameItem == "Poção de Velocidade") {
            inventory.setItem(slot,
                    new ItemBuilder(item, 1, 8226)
                            .setDisplayName("§e❯ " + nameItem + " ❮")
                            .setLore("",
                                    "§fCusto §e(1x)§f: §a156",
                                    "§fCusto §e(16x)§f: §a2.500",
                                    "",
                                    "§fClique §7direito §fpara comprar 1x",
                                    "§fClique §7esquerdo §fpara comprar 16x",
                                    "")
                            .toItemStack());
        } else if (nameItem == "Poção de Força") {
            inventory.setItem(slot,
                    new ItemBuilder(item, 1, 8233)
                            .setDisplayName("§e❯ " + nameItem + " ❮")
                            .setLore("",
                                    "§fCusto §e(1x)§f: §a156",
                                    "§fCusto §e(16x)§f: §a2.500",
                                    "",
                                    "§fClique §7direito §fpara comprar 1x",
                                    "§fClique §7esquerdo §fpara comprar 16x",
                                    "")
                            .toItemStack());
        }

        return this;
    }

    public Inventory toInventory() {
        return inventory;
    }

    public static void sellBlocks(Player player, Material material, String materialName, int quantidade, int money,
                                  int durability) {
        player.closeInventory();
        player.getInventory().addItem(new ItemBuilder(material, quantidade, durability).toItemStack());
        CustomPlayer.setCoins(CustomPlayer.getCoins(player.getName()) - money, player.getName());
        player.sendMessage("§aVocê comprou §e" + quantidade + "x §e" + materialName + " §apor $" + money + " §ecoins§a.");
        player.getScoreboard().getTeam("money").setSuffix((CustomPlayer.getCoins(player.getName()) > 999 &&
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
                                                                "§a$" + CustomPlayer.getCoins(player.getName())));
    }

    public static int PegaPrimirosDigitos(int valor, int digitos) {
        digitos = Math.max(1, digitos);
        int positivo = Math.abs(valor);
        String texto = String.valueOf(positivo);
        if (digitos > texto.length()) {
            return valor;
        }
        return Integer.parseInt(texto.substring(0, digitos)) * Integer.signum(valor);
    }

    public static void buyCorantesUm(Player player, String itemName, int durability) {
        if (CustomPlayer.getCoins(player.getName()) >= 30) {
            ShopAPI.sellBlocks(player, Material.INK_SACK, itemName, 1, 30, durability);
        } else {
            player.closeInventory();
            player.sendMessage("§cVocê não tem coins o suficiente.");
        }
    }

    public static void buyCorantesDezesseis(Player player, String itemName,
                                          int durability) {
        if (CustomPlayer.getCoins(player.getName()) >= 480) {
            ShopAPI.sellBlocks(player, Material.INK_SACK, itemName, 16, 480, durability);
        } else {
            player.closeInventory();
            player.sendMessage("§cVocê não tem coins o suficiente.");
        }
    }

    public static void buyFarmUm(Player player, String itemName, int durability) {
        if (CustomPlayer.getCoins(player.getName()) >= 6250) {
            ShopAPI.sellBlocks(player, Material.CACTUS, itemName, 1, 6250, durability);
        } else {
            player.closeInventory();
            player.sendMessage("§cVocê não tem coins o suficiente.");
        }
    }

    public static void buyFarmDezesseis(Player player, String itemName,
                                            int durability) {
        if (CustomPlayer.getCoins(player.getName()) >= 100000) {
            ShopAPI.sellBlocks(player, Material.CACTUS, itemName, 16, 100000, durability);
        } else {
            player.closeInventory();
            player.sendMessage("§cVocê não tem coins o suficiente.");
        }
    }

    public static void buyDecoUm(Player player, Material material, String itemName, int durability) {
        if (CustomPlayer.getCoins(player.getName()) >= 156) {
            ShopAPI.sellBlocks(player, material, itemName, 1, 156, durability);
        } else {
            player.closeInventory();
            player.sendMessage("§cVocê não tem coins o suficiente.");
        }
    }

    public static void buyDecoDezesseis(Player player, Material material, String itemName,
                                        int durability) {
        if (CustomPlayer.getCoins(player.getName()) >= 2500) {
            ShopAPI.sellBlocks(player, material, itemName, 16, 2500, durability);
        } else {
            player.closeInventory();
            player.sendMessage("§cVocê não tem coins o suficiente.");
        }
    }

    public static void buyCombateUm(Player player, Material material, String itemName, int durability, int money) {
        if (CustomPlayer.getCoins(player.getName()) >= money) {
            ShopAPI.sellBlocks(player, material, itemName, 1, money, durability);
        } else {
            player.closeInventory();
            player.sendMessage("§cVocê não tem coins o suficiente.");
        }
    }

    public static void buyCombateDezesseis(Player player, Material material, String itemName,
                                        int durability) {
        if (CustomPlayer.getCoins(player.getName()) >= 2500) {
            ShopAPI.sellBlocks(player, material, itemName, 16, 2500, durability);
        } else {
            player.closeInventory();
            player.sendMessage("§cVocê não tem coins o suficiente.");
        }
    }
}
