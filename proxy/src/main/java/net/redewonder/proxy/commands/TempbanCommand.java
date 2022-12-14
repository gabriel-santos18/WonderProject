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

public class TempbanCommand extends Command implements TabExecutor {

    public TempbanCommand() {
        super("tempban");
    }

    private static Cache<String, Long> cooldown;
    private static GregorianCalendar gc;

    private static SimpleDateFormat formatter;

    private ProxiedPlayer target;

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER") || CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE") || CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN") || CustomPlayer.getGroup(player).equalsIgnoreCase("§2MODERADOR")) {
                if (args.length < 4) {
                    player.sendMessage("§cSintaxe incorreta! Digite /tempban (nick) (tempo) (unidade) (motivo)");
                    player.sendMessage("§c * Unidade = (segundos, minutos, dias)");
                } else if (args.length >= 4) {
                    if (CustomPlayer.playerExists(args[0])) {
                        if (CustomPlayer.isNumeric(args[1])) {
                            if (!args[0].equalsIgnoreCase(player.getName())) {
                                if (!CustomPlayer.isBanned(args[0])) {

                                    target = ProxyServer.getInstance().getPlayer(args[0]);

                                    StringBuilder builder = new StringBuilder();
                                    for (int i = 3; i < args.length; i++) {
                                        builder.append(args[i] + " ");
                                    }


                                    gc = new GregorianCalendar();
                                    formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

                                    if (args[2].equalsIgnoreCase("segundos")) {
                                        cooldown = CacheBuilder.newBuilder().expireAfterWrite(Long.parseLong(args[1]),
                                                TimeUnit.SECONDS).build();

                                        CustomPlayer.setBan(args[0], player.getName(), String.valueOf(builder), Integer.parseInt(args[1]),
                                                "SEGUNDOS");
                                        player.sendMessage("§aVocê baniu com sucesso " + args[0] + " pelo motivo: " + builder + " por " + args[1] + " " + args[2] + ".");
                                        gc.add(Calendar.SECOND, Integer.parseInt(args[1]));
                                        cooldown.put(args[0],
                                                Long.valueOf(System.currentTimeMillis() + Long.parseLong(args[1]) + "000"));
                                    } else if (args[2].equalsIgnoreCase("minutos")) {
                                        cooldown = CacheBuilder.newBuilder().expireAfterWrite(Long.parseLong(args[1]), TimeUnit.MINUTES).build();

                                        CustomPlayer.setBan(args[0], player.getName(), String.valueOf(builder), Integer.parseInt(args[1]),
                                                "MINUTOS");                                        player.sendMessage(
                                                        "§aVocê baniu com sucesso " + args[0] + " pelo motivo: " + builder + " por " + args[1] + " " + args[2] + ".");
                                        gc.add(Calendar.MINUTE, Integer.parseInt(args[1]));
                                        cooldown.put(args[0],
                                                Long.valueOf(System.currentTimeMillis() + Long.parseLong(args[1]) + "000"));
                                    } else if (args[2].equalsIgnoreCase("dias")) {
                                        cooldown = CacheBuilder.newBuilder().expireAfterWrite(Long.parseLong(args[1]), TimeUnit.DAYS).build();

                                        CustomPlayer.setBan(args[0], player.getName(), String.valueOf(builder), Integer.parseInt(args[1]),
                                                "DIAS");                                        player.sendMessage(
                                                        "§aVocê baniu com sucesso " + args[0] + " pelo motivo: " + builder + " por " + args[1] + " " + args[2] + ".");
                                        gc.add(Calendar.DAY_OF_MONTH, Integer.parseInt(args[1]));
                                        cooldown.put(args[0], Long.valueOf(System.currentTimeMillis() + Long.parseLong(args[1]) + "000"));
                                    } else {
                                        player.sendMessage("§cVocê digitou uma unidade inválida.");
                                    }

                                    if (target != null) {
                                        target.disconnect("§c§lREDE WONDER\n\n§cVocê foi banido temporariamente do " + "servidor!\n§cMotivo: " + builder + "\n§cExpira em: " + formatter.format(gc.getTime()) + "\n§cCompre " + "unban em: loja.redewonder.net\n\n§cRede Wonder © Todos os direitos reservados" + ".");
                                    }
                                } else {
                                    player.sendMessage("§cEste jogador já está banido.");
                                }
                            } else {
                                //player.sendMessage("§cVocê não pode banir você mesmo.");
                            }
                        } else {
                            player.sendMessage("§cO tempo precisa ser um número.");
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
        } else if (args.length == 2) {
            List<String> results2 = new ArrayList<>();
            results2.add("5");
            results2.add("15");
            results2.add("20");
            results2.add("25");
            results2.add("30");
            return results2;
        } else if (args.length == 3) {
            List<String> results3 = new ArrayList<>();
            results3.add("segundos");
            results3.add("minutos");
            results3.add("dias");
            return results3;
        }
        return new ArrayList<>();
    }

    public static Cache<String, Long> getCooldown() {
        return cooldown;
    }

    public static GregorianCalendar getGc() {
        return gc;
    }

    public static SimpleDateFormat getFormatter() {
        return formatter;
    }
}
