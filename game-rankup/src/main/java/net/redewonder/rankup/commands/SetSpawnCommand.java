package net.redewonder.rankup.commands;

import me.imfighting.bukkit.managers.CommandManager;
import net.redewonder.rankup.managers.LocationsManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SetSpawnCommand extends CommandManager {

    public SetSpawnCommand() {
        super("setspawn", new String[]{});
    }


    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (!LocationsManager.isLocation(player, "Spawn")) {
                LocationsManager.setLocation(player, "Spawn");
                player.sendMessage("§aO spawn foi setado com sucesso.");
            } else {
                player.sendMessage("§cO spawn já está setado, caso queira setar uma nova localização, remova do " +
                        "locations.yml");
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
