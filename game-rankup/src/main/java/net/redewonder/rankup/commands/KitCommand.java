package net.redewonder.rankup.commands;

import me.imfighting.bukkit.inventory.InventoryBuilder;
import me.imfighting.bukkit.inventory.ItemBuilder;
import me.imfighting.bukkit.managers.CommandManager;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class KitCommand extends CommandManager {

    public KitCommand() {
        super("kit", new String[]{});
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            Inventory inventory = new InventoryBuilder(3, "§8Kits").toInventory();
            inventory.setItem(10,
                    new ItemBuilder(Material.WOOD_SWORD).setDisplayName("§6Kits §7➺ §7[Membro]").setLore("§a",
                            "§7Clique §8direito §7para ver os itens.",
                            "§7Clique §8esquerdo §7para recolher os itens.").toItemStack());
            inventory.setItem(12,
                    new ItemBuilder(Material.STONE_SWORD).setDisplayName("§6Kits §7➺ §b[CLOUD]").setLore("§a",
                            "§7Clique §8direito §7para ver os itens.",
                            "§7Clique §8esquerdo §7para recolher os itens.").toItemStack());
            inventory.setItem(14,
                    new ItemBuilder(Material.IRON_SWORD).setDisplayName("§6Kits §7➺ §2[RAIN]").setLore("§a",
                            "§7Clique §8direito §7para ver os itens.",
                            "§7Clique §8esquerdo §7para recolher os itens.").toItemStack());
            inventory.setItem(16,
                    new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("§6Kits §7➺ §5[WATER]").setLore("§a",
                            "§7Clique §8direito §7para ver os itens.",
                            "§7Clique §8esquerdo §7para recolher os itens.").toItemStack());
            player.openInventory(inventory);
        } else {
            commandSender.sendMessage("§cApenas jogadores!");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] strings) {
        return null;
    }

}
