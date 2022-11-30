package net.wonder.login.commands;

import me.imfighting.bukkit.managers.CommandManager;
import net.wonder.login.Login;
import net.wonder.login.server.ServerConnectServer;
import net.wonder.login.sql.CustomPlayer;
import org.bukkit.Bukkit;
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
            if (CustomPlayer.isRegistrado(player.getName())) {
                if (args.length < 1) {
                    player.sendMessage("§cSintaxe incorreta! Utilize /login (senha).");
                } else if (args.length == 1) {
                    if (CustomPlayer.getPass(player, args[0])) {
                        player.sendMessage("§aVocê foi logado com sucesso, redirecionando para o lobby em 3 segundos." +
                                "..");
                        Bukkit.getScheduler().runTaskLater(Login.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                ServerConnectServer.connect(player, "lobby");
                            }
                        }, 20*3);
                    } else {
                        player.sendMessage("§cA senha digitada está incorreta.");
                    }

                }
            } else {
                player.sendMessage("§cVocê não está registrado, utilize /registrar (senha) (senha).");
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
