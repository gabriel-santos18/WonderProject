package net.wonder.login.commands;

import me.imfighting.bukkit.managers.CommandManager;
import org.bukkit.command.CommandSender;

import java.util.List;

public class LoginCommand extends CommandManager {

    @Override
    public void execute(CommandSender sender, String[] args) {

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
