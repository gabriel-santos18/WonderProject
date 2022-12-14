package net.redewonder.rankup.menus.loja;

import net.redewonder.rankup.api.ShopAPI;
import net.redewonder.rankup.listeners.PlayerListeners;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class LojaCorantesMenu implements Listener {


    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getInventory().getTitle().contains("Corantes")) {
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
                player.openInventory(PlayerListeners.getLoja());
            }
        }
    }
}
