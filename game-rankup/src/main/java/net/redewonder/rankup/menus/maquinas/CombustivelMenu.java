package net.redewonder.rankup.menus.maquinas;

import me.imfighting.bukkit.inventory.ItemBuilder;
import net.redewonder.rankup.sql.CustomPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CombustivelMenu implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

        if (e.getInventory().getTitle().equalsIgnoreCase("§8Loja de Combustíveis")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eCombustível")) {
                if (CustomPlayer.getCoins(player.getName()) >= 5000000000L) {
                    player.getInventory().addItem(new ItemBuilder(Material.COAL)
                            .setDisplayName("§eCombustível")
                            .setLore(
                                    "",
                                    "§eUtilize este combustível para ativar a máquina.")
                            .toItemStack());
                    player.sendMessage("§aVocê comprou §eCombustível§a.");
                }
            }
        }
    }
}
