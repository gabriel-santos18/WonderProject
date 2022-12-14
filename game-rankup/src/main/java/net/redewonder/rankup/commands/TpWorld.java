package net.redewonder.rankup.commands;

import me.imfighting.bukkit.managers.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class TpWorld extends CommandManager {

    public TpWorld() {
        super("tpworld", new String[]{});
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        Player player = (Player) commandSender;
        player.teleport(new Location(Bukkit.getWorld("Pesca"), 30, 30, 30));
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] strings) {
        return null;
    }
}
