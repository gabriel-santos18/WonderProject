package net.redewonder.lobby.commands;

import net.redewonder.lobby.managers.CommandManager;
import me.imfighting.bukkit.server.ServerConnectServer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class LobbyCommand extends CommandManager {

    public LobbyCommand() {
        super("lobby", new String[]{"l"});
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage("§aSendo enviado para o lobby...");
            ServerConnectServer.connect(player, "lobby");
        } else {
            sender.sendMessage("§cApenas jogadores.");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
