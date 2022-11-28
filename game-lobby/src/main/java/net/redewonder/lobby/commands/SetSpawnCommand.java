package net.redewonder.lobby.commands;

import me.imfighting.bukkit.managers.CommandManager;
import net.redewonder.lobby.managers.LocationsManager;
import net.redewonder.lobby.sql.CustomPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SetSpawnCommand extends CommandManager {

    public SetSpawnCommand() {
        super("setspawn", new String[]{});
    }



    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER")) {
                LocationsManager.setSpawn(player);
                player.sendMessage("§aVocê setou o spawn com sucesso.");
            } else {
                player.sendMessage("§cVocê não tem permissão.");
            }
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
