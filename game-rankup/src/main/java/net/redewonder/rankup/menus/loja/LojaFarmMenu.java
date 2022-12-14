package net.redewonder.rankup.menus.loja;

import net.redewonder.rankup.api.ShopAPI;
import net.redewonder.rankup.listeners.PlayerListeners;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class LojaFarmMenu implements Listener {


    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getInventory().getTitle().contains("Farm")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e❯ Cactus ❮")) {
                if (e.isLeftClick()) {
                    ShopAPI.buyFarmUm(player, "Cactus", 0);
                } else if (e.isRightClick()) {
                    ShopAPI.buyFarmDezesseis(player, "Cactus", 0);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cVoltar")) {
                player.openInventory(PlayerListeners.getLoja());
            }
        }
    }
}
