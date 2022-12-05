package net.redewonder.rankup.commands;

import me.imfighting.bukkit.managers.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sun.net.www.ApplicationLaunchException;

import java.util.List;

public class TpWorldCommand extends CommandManager {

    public TpWorldCommand() {
        super("tpworld", new String[]{});
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 1) {
                player.sendMessage("§cSintaxe incorreta! Digite /tpworld (nome)");
            } else if (args.length == 1) {
                player.teleport(new Location(Bukkit.getWorld(args[0]), 100, 100, 100));
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
