package net.redewonder.lobby.commands;

import net.redewonder.lobby.Lobby;
import net.redewonder.lobby.managers.CommandManager;
import net.redewonder.lobby.sql.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GroupCommand extends CommandManager {

    private Lobby lobby;

    public GroupCommand(Lobby lobby) {
        super("group", new String[]{"grupo"});
        this.lobby = lobby;
    }


    private HashMap<UUID, PermissionAttachment> perms = new HashMap<>();


    // /grupo <nick> <grupo>

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            //if (CustomPlayer.getGroup() == "§6MASTER" || CustomPlayer.getGroup() == "§3GERENTE") {


            if (args.length <= 1) {
                player.sendMessage("§cSintaxe incorreta! Utilize /grupo <nick> <grupo>");
            } else if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[0]);

                PermissionAttachment attachment;
                if (!perms.containsKey(target.getUniqueId())) {
                    attachment = target.addAttachment(lobby);
                    perms.put(target.getUniqueId(), attachment);
                } else {
                    attachment = perms.get(target.getUniqueId());
                }

                if (target != null) {
                    if (args[1].equalsIgnoreCase("master")) {
                        CustomPlayer.setGroup("§6MASTER");
                        player.getScoreboard().getTeam("group").setSuffix("§6MASTER");
                        player.sendMessage("§aVocê setou o grupo de " + target.getName() + " §apara §6§lMASTER§a.");
                    } else if (args[1].equalsIgnoreCase("gerente")) {
                        CustomPlayer.setGroup("§3GERENTE");
                        player.getScoreboard().getTeam("group").setSuffix("§3GERENTE");
                        player.sendMessage("§aVocê setou o grupo de " + target.getName() + " §apara §3§lGERENTE§a.");
                    }
                } else {
                    player.sendMessage("§cEste jogador não existe.");
                }
            }
            //}
        } else {
            sender.sendMessage("§cApenas jogadores.");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
