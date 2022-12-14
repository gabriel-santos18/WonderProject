package net.redewonder.rankup.menus.terrenos;

import me.imfighting.bukkit.inventory.InventoryBuilder;
import me.imfighting.bukkit.inventory.ItemBuilder;
import net.redewonder.rankup.sql.CustomPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class TerrenosMenu implements Listener {

    Inventory terrenosVip;
    Inventory terrenos;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§l➦ Terrenos")) {

            if (CustomPlayer.getGroup(player).equalsIgnoreCase("§bCLOUD") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§2RAIN") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§5WATER") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§eAJUDANTE") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§2MODERADOR") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER")) {
                terrenosVip = new InventoryBuilder(6, "§8Terrenos").toInventory();

                if (!CustomPlayer.getPlotme1(player)) {
                    terrenosVip.setItem(21, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).setDisplayName("§cSlot #01").setLore("", "§eClique aqui para comprar um terreno").toItemStack());
                } else {
                    terrenosVip.setItem(21, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5).setDisplayName("§aTerreno 01").setLore("", "§eClique aqui para teleportar para este terreno.").toItemStack());
                }


                if (!CustomPlayer.getPlotme2(player)) {
                    terrenosVip.setItem(22, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).setDisplayName("§cSlot #02").setLore("", "§eClique aqui para comprar um terreno").toItemStack());
                } else {
                    terrenosVip.setItem(22, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5).setDisplayName("§aTerreno 02").setLore("", "§eClique aqui para teleportar para este terreno.").toItemStack());
                }


                if (!CustomPlayer.getPlotme3(player)) {
                    terrenosVip.setItem(23, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).setDisplayName("§cSlot #03").setLore("", "§eClique aqui para comprar um terreno").toItemStack());

                } else {
                    terrenosVip.setItem(23, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5).setDisplayName("§aTerreno 03").setLore("", "§eClique aqui para teleportar para este terreno.").toItemStack());
                }

                if (!CustomPlayer.getPlotme4(player)) {
                    terrenosVip.setItem(30, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).setDisplayName("§cSlot #04").setLore("", "§eClique aqui para comprar um terreno").toItemStack());
                } else {
                    terrenosVip.setItem(30, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5).setDisplayName("§aTerreno 04").setLore("", "§eClique aqui para teleportar para este terreno.").toItemStack());
                }

                if (!CustomPlayer.getPlotme5(player)) {
                    terrenosVip.setItem(31, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).setDisplayName("§cSlot #05").setLore("", "§eClique aqui para comprar um terreno").toItemStack());
                } else {
                    terrenosVip.setItem(31, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5).setDisplayName("§aTerreno 05").setLore("", "§eClique aqui para teleportar para este terreno.").toItemStack());
                }

                if (!CustomPlayer.getPlotme6(player)) {
                    terrenosVip.setItem(32, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).setDisplayName("§cSlot #06").setLore("", "§eClique aqui para comprar um terreno").toItemStack());
                } else {
                    terrenosVip.setItem(32, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5).setDisplayName("§aTerreno 06").setLore("", "§eClique aqui para teleportar para este terreno.").toItemStack());
                }
                player.openInventory(terrenosVip);
            } else if (CustomPlayer.getGroup(player).equalsIgnoreCase("§7MEMBRO")) {

                terrenos = new InventoryBuilder(6, "§8Terrenos").toInventory();

                if (!CustomPlayer.getPlotme1(player)) {
                    terrenos.setItem(21, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 0).setDisplayName("§cSlot #01").setLore("", "§eClique aqui para comprar um terreno").toItemStack());
                } else {
                    terrenos.setItem(21, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5).setDisplayName("§aTerreno 01").setLore("", "§eClique aqui para teleportar para este terreno.").toItemStack());
                }

                player.openInventory(terrenos);
            }
        }
    }
}
