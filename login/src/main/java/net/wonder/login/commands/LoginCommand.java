package net.wonder.login.commands;

import me.imfighting.bukkit.managers.CommandManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class LoginCommand extends CommandManager {

    public LoginCommand() {
        super("login", new String[]{});
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 1) {

            }
        } else {
            sender.sendMessage("§cApenas jogadores.");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
