package net.redewonder.rankup.commands;

import me.imfighting.bukkit.managers.CommandManager;
import net.redewonder.rankup.Rankup;
import net.redewonder.rankup.sql.CustomPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.List;

public class SetCommand extends CommandManager {


    public SetCommand() {
        super("set", new String[]{});
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {

        YamlConfiguration modifyFile = YamlConfiguration.loadConfiguration(Rankup.getInstance().file);

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER")) {
                if (strings.length < 3) {
                    player.sendMessage("§cSintaxe incorreta! Digite /set warp (warp) (min,max)");
                } else if (strings[0].equalsIgnoreCase("warp")) {
                    if (strings.length == 2) {
                        player.sendMessage("§cSintaxe incorreta! Digite /set warp (warp) (min,max)");
                    } else if (strings.length == 3) {
                        if (strings[2].equalsIgnoreCase("min")) {
                            modifyFile.set("Spawn.Min.X", player.getLocation().getX());
                            modifyFile.set("Spawn.Min.Y", player.getLocation().getY());
                            try {
                                modifyFile.save(Rankup.getInstance().file);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else if (strings[2].equalsIgnoreCase("max")) {
                            modifyFile.set("Spawn.Max.X", player.getLocation().getX());
                            modifyFile.set("Spawn.Max.Y", player.getLocation().getY());                            try {
                                modifyFile.save(Rankup.getInstance().file);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
        } else {
            commandSender.sendMessage("§cApenas jogadores!");
        }

    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] strings) {
        return null;
    }
}
