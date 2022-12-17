package net.redewonder.rankup.commands;

import me.imfighting.bukkit.inventory.InventoryBuilder;
import me.imfighting.bukkit.inventory.ItemBuilder;
import me.imfighting.bukkit.managers.CommandManager;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class MaquinasCommand extends CommandManager {


    public MaquinasCommand() {
        super("maquinas", new String[]{"machines"});
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            Inventory inventory = new InventoryBuilder(4, "§8Máquinas").toInventory();

            inventory.setItem(11, new ItemBuilder(Material.HUGE_MUSHROOM_2)
                    .setDisplayName("§bMáquina de Cogumelo")
                    .setLore("",
                            " §7Raridade: §7§lCOMUM",
                            " §7Preço: §f3B",
                            " §7Rank: §eAnfitrite III",
                            "",
                            "§eClique aqui para comprar.")
                    .toItemStack());
            inventory.setItem(12, new ItemBuilder(Material.PUMPKIN)
                    .setDisplayName("§bMáquina de Abóbora")
                    .setLore("",
                            " §7Raridade: §7§lCOMUM",
                            " §7Preço: §f5B",
                            " §7Rank: §eAnfitrite III",
                            "",
                            "§eClique aqui para comprar.")
                    .toItemStack());
            inventory.setItem(13, new ItemBuilder(Material.PRISMARINE)
                    .setDisplayName("§bMáquina de Prismarinho")
                    .setLore("",
                            " §7Raridade: §6§lRARO",
                            " §7Preço: §f20B",
                            " §7Rank: §eNereus III",
                            "",
                            "§eClique aqui para comprar.")
                    .toItemStack());
            inventory.setItem(14, new ItemBuilder(Material.OBSIDIAN)
                    .setDisplayName("§bMáquina de Obsidian")
                    .setLore("",
                            " §7Raridade: §6§lRARO",
                            " §7Preço: §f25B",
                            " §7Rank: §eNereus III",
                            "",
                            "§eClique aqui para comprar.")
                    .toItemStack());
            inventory.setItem(15, new ItemBuilder(Material.GOLD_BLOCK)
                    .setDisplayName("§bMáquina de Ouro")
                    .setLore("",
                            " §7Raridade: §5§lLENDÁRIO",
                            " §7Preço: §f50B",
                            " §7Rank: §eProteu I",
                            "",
                            "§eClique aqui para comprar.")
                    .toItemStack());

            inventory.setItem(31, new ItemBuilder(Material.COAL, 1, 1)
                    .setDisplayName("§eLoja de combustível")
                    .setLore("",
                            "§eClique aqui para comprar combustíveis.")
                    .toItemStack());

            player.openInventory(inventory);
        } else {
            commandSender.sendMessage("§cApenas jogadores.");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] strings) {
        return null;
    }
}
