package net.redewonder.proxy.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import net.redewonder.proxy.sql.CustomPlayer;

import java.util.ArrayList;
import java.util.List;

public class ReportCommand extends Command implements TabExecutor {

    public ReportCommand() {
        super("report");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if (args.length < 2) {
                player.sendMessage("§cSintaxe incorreta! Digite /report (nick) (motivo)");
            } else if (args.length == 2) {
                ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
                if (target != null) {
                    player.sendMessage("§aVocê reportou o jogador " + target.getName() + " pelo motivo " + args[1] +
                            " com sucesso!");
                    for (ProxiedPlayer proxiedPlayer : ProxyServer.getInstance().getPlayers()) {
                        if (CustomPlayer.getGroup(proxiedPlayer).equalsIgnoreCase("§6MASTER") ||
                                CustomPlayer.getGroup(proxiedPlayer).equalsIgnoreCase("§3GERENTE") ||
                                CustomPlayer.getGroup(proxiedPlayer).equalsIgnoreCase("§cADMIN") ||
                                CustomPlayer.getGroup(proxiedPlayer).equalsIgnoreCase("§2MODERADOR")) {
                            proxiedPlayer.sendMessage("§c");
                            proxiedPlayer.sendMessage("§cReportado: §f" + target.getName());
                            proxiedPlayer.sendMessage("§6Motivo: §f" + args[1]);
                            proxiedPlayer.sendMessage("§eServidor: §f" + target.getServer().getInfo().getName());
                            proxiedPlayer.sendMessage("§aQuem enviou: §f" + player.getName());
                            TextComponent report = new TextComponent("§b§lCLIQUE AQUI §epara se teleportar.");
                            report.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
                                    "/go " + target.getName()));
                            report.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                    new TextComponent[]{new TextComponent("§eClique aqui para se teleportar até o " +
                                            "jogador.")}));
                            proxiedPlayer.sendMessage("");
                            proxiedPlayer.sendMessage(report);
                        }
                    }
                } else {
                    player.sendMessage("§cEste jogador não está online!");
                }
            }
        }
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        List<String> results = new ArrayList<>();

        if (args.length == 1) {
            for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                results.add(player.getName());
            }
        }

        return results;
    }
}
