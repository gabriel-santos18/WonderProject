package net.redewonder.rankup.commands;

import me.imfighting.bukkit.managers.CommandManager;
import org.bukkit.command.CommandSender;

import java.util.List;

public class RankupCommand extends CommandManager {


    public RankupCommand() {
        super("cmd", new String[]{});
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] strings) {
        return null;
    }
}
