package net.redewonder.lobby.commands;

import net.redewonder.lobby.managers.CommandManager;
import org.bukkit.command.CommandSender;

import java.util.List;

public class TagCommand extends CommandManager {
    public TagCommand() {
        super("tag", new String[]{});
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
