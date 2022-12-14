package net.redewonder.rankup.commands;

import me.imfighting.bukkit.inventory.ItemBuilder;
import me.imfighting.bukkit.managers.CommandManager;
import net.redewonder.rankup.managers.LocationsManager;
import net.redewonder.rankup.sql.CustomPlayer;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.List;

public class MinaCommand extends CommandManager {

    public MinaCommand() {
        super("mina", new String[]{});
    }


    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (!player.getWorld().getName().equalsIgnoreCase("Minas")) {
                if (CustomPlayer.isInventoryEmpty(player)) {
                    player.teleport(LocationsManager.getLocation(player, "Mina"));
                    player.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_PICKAXE)
                            .addEnchantmant(Enchantment.DURABILITY, 3)
                            .addEnchantmant(Enchantment.LOOT_BONUS_BLOCKS, 2)
                            .addEnchantmant(Enchantment.DIG_SPEED, 5)
                            .setDisplayName("§bPicareta de diamante §c§l(MINA)").toItemStack());
                    player.sendMessage("§aVocê foi teleportado para a mineração com sucesso.");
                } else {
                    player.closeInventory();
                    player.sendMessage("§cLimpe seu inventário antes de entrar na mineração.");
                }
            } else {
                player.closeInventory();
                player.sendMessage("§cVocê já está na área de mineração.");
            }
        } else {
            commandSender.sendMessage("§cApenas jogadores.");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] strings) {
        return null;
    }
}
