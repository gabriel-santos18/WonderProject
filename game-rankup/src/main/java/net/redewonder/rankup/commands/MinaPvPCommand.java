package net.redewonder.rankup.commands;

import me.imfighting.bukkit.managers.CommandManager;
import net.redewonder.rankup.managers.LocationsManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class MinaPvPCommand extends CommandManager {

    public MinaPvPCommand() {
        super("minapvp", new String[]{});
    }


    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (!player.getWorld().getName().equalsIgnoreCase("Minas")) {
                player.teleport(LocationsManager.getLocation(player, "MinaPvP"));
                player.sendMessage("§aVocê foi teleportado para a mineração PvP com sucesso.");
            } else {
                player.closeInventory();
                player.sendMessage("§cVocê já está na área de mineração PvP.");
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
