package net.redewonder.proxy.commands;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import net.redewonder.proxy.Proxy;
import net.redewonder.proxy.sql.CustomPlayer;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MuteCommand extends Command implements TabExecutor {

    public MuteCommand() {
        super("mute");
    }

    private static Cache<UUID, Long> cooldown;
    private static ProxiedPlayer target;

    private static GregorianCalendar gc;

    private static SimpleDateFormat formatter;

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER") || CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE") || CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN") || CustomPlayer.getGroup(player).equalsIgnoreCase("§2MODERADOR")) {
                if (args.length < 2) {
                    player.sendMessage("§cSintaxe incorreta! Digite /mute (nick) (motivo)");
                } else if (args.length >= 2) {
                    if (CustomPlayer.playerExists(args[0])) {
                            if (!args[0].equalsIgnoreCase(player.getName())) {
                                if (!CustomPlayer.isMuted(args[0])) {

                                    StringBuilder builder = new StringBuilder();
                                    for (int i = 1; i < args.length; i++) {
                                        builder.append(args[i] + " ");
                                    }

                                    CustomPlayer.setMute(args[0], player.getName(), String.valueOf(builder), 0, "-");

                                    player.sendMessage("§aVocê mutou com sucesso " + args[0] + " permanentemente pelo" +
                                            " motivo: " + builder);

                                } else {
                                    player.sendMessage("§cEste jogador já está mutado.");
                                }
                            } else {
                                player.sendMessage("§cVocê não pode mutar você mesmo.");
                            }
                    } else {
                        player.sendMessage("§cEste jogador não existe.");
                    }
                }
            } else {
                player.sendMessage("§cVocê não tem permissão.");
            }
        }
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        List<String> results = new ArrayList<>();

        if (args.length == 1) {
            for (ProxiedPlayer proxiedPlayer : Proxy.getInstance().getProxy().getPlayers()) {
                results.add(proxiedPlayer.getName());
                return results;
            }
        }
        return new ArrayList<>();
    }

    public static Cache<UUID, Long> getCooldown() {
        return cooldown;
    }

    public static ProxiedPlayer getTarget() {
        return target;
    }

    public static GregorianCalendar getGc() {
        return gc;
    }

    public static SimpleDateFormat getFormatter() {
        return formatter;
    }
}
