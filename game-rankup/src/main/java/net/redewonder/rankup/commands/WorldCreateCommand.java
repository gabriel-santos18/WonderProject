package net.redewonder.rankup.commands;

import me.imfighting.bukkit.managers.CommandManager;
import net.redewonder.rankup.managers.WorldManager;
import net.redewonder.rankup.sql.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sun.net.www.ApplicationLaunchException;

import java.util.List;

public class WorldCreateCommand extends CommandManager {

    public WorldCreateCommand() {
        super("worldcreate", new String[]{});
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER")) {
                WorldCreator wc = new WorldCreator("Minas");
                wc.generator(new WorldManager());
                wc.createWorld();
            }
        } else {
            sender.sendMessage("§cApenas jogadores.");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] strings) {
        return null;
    }
}
