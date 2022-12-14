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

public class PescaCommand extends CommandManager {

    public PescaCommand() {
        super("pesca", new String[]{});
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (CustomPlayer.isInventoryEmpty(player)) {
                player.teleport(LocationsManager.getLocation(player, "Pesca"));
                player.getInventory().setItem(0, new ItemBuilder(Material.FISHING_ROD)
                        .addEnchantmant(Enchantment.LURE, 3)
                        .addEnchantmant(Enchantment.DURABILITY, 2)
                        .addEnchantmant(Enchantment.LUCK, 3)
                        .setDisplayName("§bVara de Pesca §c§l(PESCA)")
                        .toItemStack());
                player.sendMessage("§aVocê teleportou para a pesca com sucesso.");
            } else {
                player.closeInventory();
                player.sendMessage("§cLimpe seu inventário antes de entrar na pesca.");
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
