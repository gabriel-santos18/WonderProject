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
                if (strings.length < 2) {
                    player.sendMessage("§cSintaxe incorreta! Digite /set warp (warp)");
                } else if (strings[0].equalsIgnoreCase("warp")) {
                    if (strings.length == 1) {
                        player.sendMessage("§cSintaxe incorreta! Digite /set warp (warp)");
                    } else if (strings.length == 2) {
                        if (strings[1].equalsIgnoreCase("minapvp")) {
                            if (!modifyFile.contains("MinaPvP")) {
                                modifyFile.set("MinaPvP.X", player.getLocation().getX());
                                modifyFile.set("MinaPvP.Y", player.getLocation().getY());
                                modifyFile.set("MinaPvP.Z", player.getLocation().getZ());
                                modifyFile.set("MinaPvP.YAW", player.getLocation().getYaw());
                                modifyFile.set("MinaPvP.PITCH", player.getLocation().getPitch());
                                player.sendMessage("§aMinaPvP setada com sucesso.");
                                try {
                                    modifyFile.save(Rankup.getInstance().file);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            } else {
                                player.sendMessage("§cJá existe esta localização, caso queira setar outra, remova do " +
                                        "locations.yml!");
                            }
                        } else if (strings[1].equalsIgnoreCase("minavip")) {
                            if (!modifyFile.contains("MinaVip")) {
                                modifyFile.set("MinaVip.X", player.getLocation().getX());
                                modifyFile.set("MinaVip.Y", player.getLocation().getY());
                                modifyFile.set("MinaVip.Z", player.getLocation().getZ());
                                modifyFile.set("MinaVip.YAW", player.getLocation().getYaw());
                                modifyFile.set("MinaVip.PITCH", player.getLocation().getPitch());
                                player.sendMessage("§aMinaVip setada com sucesso.");
                                try {
                                    modifyFile.save(Rankup.getInstance().file);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            } else {
                                player.sendMessage("§cJá existe esta localização, caso queira setar outra, remova do " +
                                        "locations.yml!");
                            }
                        } else if (strings[1].equalsIgnoreCase("Mina")) {
                            if (!modifyFile.contains("Mina")) {
                                modifyFile.set("Mina.X", player.getLocation().getX());
                                modifyFile.set("Mina.Y", player.getLocation().getY());
                                modifyFile.set("Mina.Z", player.getLocation().getZ());
                                modifyFile.set("Mina.YAW", player.getLocation().getYaw());
                                modifyFile.set("Mina.PITCH", player.getLocation().getPitch());
                                player.sendMessage("§aMina setada com sucesso.");
                                try {
                                    modifyFile.save(Rankup.getInstance().file);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            } else {
                                player.sendMessage("§cJá existe esta localização, caso queira setar outra, remova do " +
                                        "locations.yml!");
                            }
                        } else if (strings[1].equalsIgnoreCase("plotme")) {
                            if (!modifyFile.contains("Plotme")) {
                                modifyFile.set("Plotme.X", player.getLocation().getX());
                                modifyFile.set("Plotme.Y", player.getLocation().getY());
                                modifyFile.set("Plotme.Z", player.getLocation().getZ());
                                modifyFile.set("Plotme.YAW", player.getLocation().getYaw());
                                modifyFile.set("Plotme.PITCH", player.getLocation().getPitch());
                                player.sendMessage("§aPlotme setada com sucesso.");
                                try {
                                    modifyFile.save(Rankup.getInstance().file);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
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
