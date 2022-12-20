package net.redewonder.rankup.menus.maquinas;

import me.imfighting.bukkit.inventory.InventoryBuilder;
import me.imfighting.bukkit.inventory.ItemBuilder;
import net.redewonder.rankup.sql.CustomPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class MaquinasMenu implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

        if (e.getInventory().getTitle().equalsIgnoreCase("§8Máquinas")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bMáquina de Cogumelo")) {
                if (CustomPlayer.getCoins(player.getName()) >= 3000000000L) {
                    player.getInventory().addItem(new ItemBuilder(Material.HUGE_MUSHROOM_2)
                            .setDisplayName("§bMáquina de Cogumelo")
                                    .setLore("",
                                            "§7Coloque esta máquina no terreno.")
                            .toItemStack());
                    CustomPlayer.setCoins(CustomPlayer.getCoins(player.getName()) - 3000000000L, player.getName());
                    player.closeInventory();
                    player.sendMessage("§aVocê comprou §eMáquina de Cogumelo§a.");
                } else {
                    player.closeInventory();
                    player.sendMessage("§cVocê não possui coins o suficiente.");
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bMáquina de Abóbora")) {
                if (CustomPlayer.getCoins(player.getName()) >= 5000000000L) {
                    player.getInventory().addItem(new ItemBuilder(Material.PUMPKIN)
                            .setDisplayName("§bMáquina de Abóbora")
                            .setLore("",
                                    "§7Coloque esta máquina no terreno.")
                            .toItemStack());
                    CustomPlayer.setCoins(CustomPlayer.getCoins(player.getName()) - 5000000000L,
                            player.getName());
                    player.closeInventory();
                    player.sendMessage("§aVocê comprou §eMáquina de Abóbora§a.");
                } else {
                    player.closeInventory();
                    player.sendMessage("§cVocê não possui coins o suficiente.");
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bMáquina de Prismarinho")) {
                if (CustomPlayer.getCoins(player.getName()) >= 20000000000L) {
                    if (CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Nereus III") ||
                            CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Nereus III") ||
                            CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Nereus II") ||
                            CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Nereus I") ||
                            CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Proteu III") ||
                            CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Proteu II") ||
                            CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Proteu I") ||
                            CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Poseidon")) {
                        player.getInventory().addItem(new ItemBuilder(Material.PRISMARINE)
                                .setDisplayName("§bMáquina de Prismarinho")
                                .setLore("",
                                        "§7Coloque esta máquina no terreno.")
                                .toItemStack());
                        CustomPlayer.setCoins(CustomPlayer.getCoins(player.getName()) - 20000000000L,
                                player.getName());
                        player.closeInventory();
                        player.sendMessage("§aVocê comprou §eMáquina de Prismarinho§a.");
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não possui o rank.");
                    }
                } else {
                    player.closeInventory();
                    player.sendMessage("§cVocê não possui coins o suficiente.");
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bMáquina de Obsidian")) {
                if (CustomPlayer.getCoins(player.getName()) >= 25000000000L) {
                    if (CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Nereus III") ||
                            CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Nereus III") ||
                            CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Nereus II") ||
                            CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Nereus I") ||
                            CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Proteu III") ||
                            CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Proteu II") ||
                            CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Proteu I") ||
                            CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Poseidon")) {
                        player.getInventory().addItem(new ItemBuilder(Material.OBSIDIAN)
                                .setDisplayName("§bMáquina de Obsidian")
                                .setLore("",
                                        "§7Coloque esta máquina no terreno.")
                                .toItemStack());
                        CustomPlayer.setCoins(CustomPlayer.getCoins(player.getName()) - 25000000000L,
                                player.getName());
                        player.closeInventory();
                        player.sendMessage("§aVocê comprou §eMáquina de Obsidian§a.");
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não possui o rank.");
                    }
                } else {
                    player.closeInventory();
                    player.sendMessage("§cVocê não possui coins o suficiente.");
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bMáquina de Ouro")) {
                if (CustomPlayer.getCoins(player.getName()) >= 50000000000L) {
                    if (CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Proteu I") ||
                            CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Poseidon")) {
                        player.getInventory().addItem(new ItemBuilder(Material.GOLD_BLOCK)
                                .setDisplayName("§bMáquina de Ouro")
                                .setLore("",
                                        "§7Coloque esta máquina no terreno.")
                                .toItemStack());
                        CustomPlayer.setCoins(CustomPlayer.getCoins(player.getName()) - 50000000000L,
                                player.getName());
                        player.closeInventory();
                        player.sendMessage("§aVocê comprou §eMáquina de Ouro§a.");
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cVocê não possui o rank.");
                    }
                } else {
                    player.closeInventory();
                    player.sendMessage("§cVocê não possui coins o suficiente.");
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eLoja de combustível")) {
                Inventory inventory = new InventoryBuilder(3, "§8Loja de Combustíveis").toInventory();

                inventory.setItem(13, new ItemBuilder(Material.COAL)
                        .setDisplayName("§eCombustível")
                        .setLore(
                                "",
                                " §7Preço: §f5B",
                                "",
                                "§eClique aqui para comprar.")
                        .toItemStack());

                player.openInventory(inventory);
            }
        }
    }
}
