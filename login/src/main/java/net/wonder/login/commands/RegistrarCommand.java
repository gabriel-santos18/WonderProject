package net.wonder.login.commands;

import me.imfighting.bukkit.managers.CommandManager;
import net.wonder.login.Login;
import net.wonder.login.server.ServerConnectServer;
import net.wonder.login.sql.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.List;

public class RegistrarCommand extends CommandManager {

    public RegistrarCommand() {
        super("registrar", new String[]{});
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!CustomPlayer.isRegistrado(player.getName())) {
                if (args.length < 2) {
                    player.sendMessage("§cSintaxe incorreta! Utilize /registrar (senha) (senha).");
                } else if (args.length == 1) {
                    player.sendMessage("§cSintaxe incorreta! Utilize /registrar (senha) (senha).");
                } else if (args.length == 2) {
                    if (!args[0].equalsIgnoreCase(args[1])) {
                        player.sendMessage("§cA senha não se se coincidem.");
                    } else {
                        CustomPlayer.createFieldPass();
                        CustomPlayer.setPassword(args[0], player);
                        CustomPlayer.setRegister(player);
                        player.sendMessage("§aVocê foi registrado com sucesso, redirecionando para o lobby em 5 segundos...");
                        Bukkit.getScheduler().runTaskLater(Login.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                ServerConnectServer.connect(player, "lobby");
                            }
                        }, 20*5);
                    }
                }
            } else {
                player.sendMessage("§cVocê já está registrado.");
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
