package net.redewonder.rankup.commands;

import me.imfighting.bukkit.managers.CommandManager;
import net.redewonder.rankup.Rankup;
import net.redewonder.rankup.managers.LocationsManager;
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
                if (strings.length < 2) {
                    player.sendMessage("§cSintaxe incorreta! Digite /set warp (warp)");
                } else if (strings[0].equalsIgnoreCase("warp")) {
                    if (strings.length == 1) {
                        player.sendMessage("§cSintaxe incorreta! Digite /set warp (warp)");
                    } else if (strings.length == 2) {
                        if (strings[1].equalsIgnoreCase("minapvp")) {
                            if (!modifyFile.contains("MinaPvP")) {
                                LocationsManager.setLocation(player, "MinaPvP");
                                player.sendMessage("§aMinaPvP setada com sucesso.");
                            } else {
                                player.sendMessage("§cJá existe esta localização, caso queira setar outra, remova do " +
                                        "locations.yml!");
                            }
                        } else if (strings[1].equalsIgnoreCase("minavip")) {
                            if (!modifyFile.contains("MinaVip")) {
                                LocationsManager.setLocation(player, "MinaVip");
                                player.sendMessage("§aMinaVip setada com sucesso.");
                            } else {
                                player.sendMessage("§cJá existe esta localização, caso queira setar outra, remova do " +
                                        "locations.yml!");
                            }
                        } else if (strings[1].equalsIgnoreCase("Mina")) {
                            if (!modifyFile.contains("Mina")) {
                                LocationsManager.setLocation(player, "Mina");
                                player.sendMessage("§aMina setada com sucesso.");
                            } else {
                                player.sendMessage("§cJá existe esta localização, caso queira setar outra, remova do " +
                                        "locations.yml!");
                            }
                        } else if (strings[1].equalsIgnoreCase("plotme")) {
                            if (!modifyFile.contains("Plotme")) {
                                LocationsManager.setLocation(player, "Plotme");
                                player.sendMessage("§aPlotme setada com sucesso.");
                            } else {
                                player.sendMessage("§cJá existe esta localização, caso queira setar outra, remova do " +
                                        "locations.yml!");
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
