package net.redewonder.rankup.commands;

import me.imfighting.bukkit.managers.CommandManager;
import net.redewonder.rankup.Rankup;
import net.redewonder.rankup.sql.CustomPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SetCommand extends CommandManager {


    public SetCommand() {
        super("set", new String[]{});
    }

    // /set warp <warp> <min,max> <valor>

    @Override
    public void execute(CommandSender commandSender, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER")) {
                if (strings.length < 4) {
                    player.sendMessage("§cSintaxe incorreta! Digite /set warp (warp) (min,max) (valor)");
                } else if (strings[0].equalsIgnoreCase("warp")) {
                    if (strings.length == 2) {
                        player.sendMessage("§cSintaxe incorreta! Digite /set warp (warp) (min,max) (valor)");
                    } else if (strings.length == 3) {
                        player.sendMessage("§cSintaxe incorreta! Digite /set warp (warp) (min,max) (valor)");
                    } else if (strings.length == 4) {
                        Rankup.getInstance().file.
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
