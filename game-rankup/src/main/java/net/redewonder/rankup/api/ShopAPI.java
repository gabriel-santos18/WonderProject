package net.redewonder.rankup.api;

import me.imfighting.bukkit.inventory.InventoryBuilder;
import me.imfighting.bukkit.inventory.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ShopAPI {

    Inventory inventory;

    public ShopAPI(Player player, String categoria, int size, int pag) {
        inventory = new InventoryBuilder(size, "§8Loja - "+ categoria + " ("+ pag + ")").toInventory();
        player.openInventory(inventory);

    }

    public ShopAPI addBackProx(String backProx, int slot) {
        if (backProx == "voltar") {
            inventory.setItem(slot, new ItemBuilder(Material.ARROW)
                    .setDisplayName("§cVoltar")
                    .toItemStack());
        } else if (backProx == "prox") {
            inventory.setItem(slot, new ItemBuilder(Material.INK_SACK)
                    .setDurability(Material.INK_SACK, 10)
                    .setDisplayName("§aPróxima página")
                    .toItemStack());
        } else if (backProx == "voltar") {
            inventory.setItem(slot, new ItemBuilder(Material.INK_SACK)
                    .setDurability(Material.INK_SACK, 8)
                    .setDisplayName("§cPágina anterior")
                    .toItemStack());
        }
        return this;
    }

    public ShopAPI addShopItem(String categoria, Material item, String nameItem ,int slot) {
        if (categoria == "blocos") {
            inventory.setItem(slot, new ItemBuilder(item).setDisplayName("§e❯ "+ nameItem + " ❮")
                    .setLore("",
                            "§fCusto §e(1x)§f: §a50",
                            "§fCusto §e(64x)§f: §a3.200",
                            "",
                            "§fClique §7direito " +
                                    "§fpara comprar 1x",
                            "§fClique §7esquerdo §fpara comprar 64x",
                            "")
                    .toItemStack());
        }
        return this;
    }

    public ShopAPI addShopItem(String categoria, Material item, String nameItem ,int slot, int durability) {
        if (categoria == "blocos") {
            inventory.setItem(slot, new ItemBuilder(item)
                    .setDurability(item, durability)
                    .setDisplayName("§e❯ "+ nameItem + " ❮")
                    .setLore("",
                            "§fCusto §e(1x)§f: §a50",
                            "§fCusto §e(64x)§f: §a3.200",
                            "",
                            "§fClique §7direito " +
                                    "§fpara comprar 1x",
                            "§fClique §7esquerdo §fpara comprar 64x",
                            "")
                    .toItemStack());
        }
        return this;
    }
}
