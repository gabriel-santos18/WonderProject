package net.redewonder.rankup.commands;

import me.imfighting.bukkit.inventory.ItemBuilder;
import me.imfighting.bukkit.managers.CommandManager;
import net.redewonder.rankup.managers.LocationsManager;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.List;

public class SpawnCommand extends CommandManager {

    public SpawnCommand() {
        super("spawn", new String[]{});
    }


    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            player.teleport(LocationsManager.getLocation(player, "Spawn"));
            player.sendMessage("§aVocê foi teleportado para o spawn com sucesso.");
            if (player.getInventory().contains(Material.FISHING_ROD)) {
                player.getInventory().remove(Material.FISHING_ROD);
            } else if (player.getInventory().contains(new ItemBuilder(Material.DIAMOND_PICKAXE)
                    .addEnchantmant(Enchantment.DURABILITY, 3)
                    .addEnchantmant(Enchantment.LOOT_BONUS_BLOCKS, 2)
                    .addEnchantmant(Enchantment.DIG_SPEED, 5)
                    .setDisplayName("§bPicareta de diamante §c§l(MINA)").toItemStack())) {
                player.getInventory().remove(Material.DIAMOND_PICKAXE);
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
