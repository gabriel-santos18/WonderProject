package net.redewonder.rankup.commands;

import me.imfighting.bukkit.managers.CommandManager;
import net.redewonder.rankup.Rankup;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import sun.net.www.ApplicationLaunchException;

import java.util.List;

public class TpWorldCommand extends CommandManager {

    public TpWorldCommand() {
        super("tpworld", new String[]{});
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        YamlConfiguration modifyFile = YamlConfiguration.loadConfiguration(Rankup.getInstance().file);

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 1) {
                player.sendMessage("§cSintaxe incorreta! Digite /tpworld (nome)");
            } else if (args.length == 1) {
                if (Bukkit.getWorld(args[0]) != null) {
                    player.teleport(new Location(Bukkit.getWorld(args[0]), modifyFile.getDouble("Mina.X"),
                            modifyFile.getDouble("Mina.Y"), modifyFile.getDouble("Mina.Z")));
                } else {
                    player.sendMessage("§cEste mundo não existe.");
                }
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
