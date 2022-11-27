package net.redewonder.lobby.commands;

import net.redewonder.lobby.Lobby;
import net.redewonder.lobby.group.Groups;
import net.redewonder.lobby.managers.CommandManager;
import net.redewonder.lobby.sql.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.util.StringUtil;

import java.util.*;

public class GroupCommand extends CommandManager {

    private Lobby lobby;

    public GroupCommand(Lobby lobby) {
        super("group", new String[]{"grupo"});
        this.lobby = lobby;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER") || CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE")) {
                if (args.length <= 1) {
                    player.sendMessage("§cSintaxe incorreta! Utilize /grupo <nick> <grupo>");
                } else if (args.length == 2) {
                    if (CustomPlayer.playerExists(args[0])) {
                        if (!args[0].equalsIgnoreCase(player.getName())) {
                            if (args[1].equalsIgnoreCase("master")) {
                                if (!CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE") && !CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN")) {
                                    CustomPlayer.setGroup("§6MASTER", Bukkit.getPlayer(args[0]).getUniqueId());
                                    CustomPlayer.setNametag("§6MASTER", Bukkit.getPlayer(args[0]).getUniqueId());
                                    Bukkit.getPlayer(args[0]).getScoreboard().getTeam("group").setSuffix("§6MASTER");
                                    player.sendMessage("§aVocê setou o grupo de " + args[0] + " §apara §6§lMASTER§a.");
                                    Bukkit.getPlayer(args[0]).sendMessage("§aSeu grupo foi alterado para §6§lMASTER§a.");
                                } else {
                                    player.sendMessage("§cVocê não pode setar um grupo igual ou maior que o seu.");
                                }
                            } else if (args[1].equalsIgnoreCase("gerente")) {
                                if (!CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE") && !CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN")) {
                                    CustomPlayer.setGroup("§3GERENTE", Bukkit.getPlayer(args[0]).getUniqueId());
                                    CustomPlayer.setNametag("§3GERENTE", Bukkit.getPlayer(args[0]).getUniqueId());
                                    Bukkit.getPlayer(args[0]).getScoreboard().getTeam("group").setSuffix("§3GERENTE");
                                    player.sendMessage("§aVocê setou o grupo de " + args[0] + " §apara §3§lGERENTE§a.");
                                    Bukkit.getPlayer(args[0]).sendMessage("§aSeu grupo foi alterado para §3§lGERENTE§a.");
                                } else {
                                    player.sendMessage("§cVocê não pode setar um grupo igual ou maior que o seu.");
                                }
                            } else if (args[1].equalsIgnoreCase("admin")) {
                                if (!CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN")) {
                                    CustomPlayer.setGroup("§cADMIN", Bukkit.getPlayer(args[0]).getUniqueId());
                                    CustomPlayer.setNametag("§cADMIN", Bukkit.getPlayer(args[0]).getUniqueId());
                                    Bukkit.getPlayer(args[0]).getScoreboard().getTeam("group").setSuffix("§cADMIN");
                                    player.sendMessage("§aVocê setou o grupo de " + args[0] + " §apara §c§lADMIN§a.");
                                    Bukkit.getPlayer(args[0]).sendMessage("§aSeu grupo foi alterado para §c§lADMIN§a.");
                                } else {
                                    player.sendMessage("§cVocê não pode setar um grupo igual ou maior que o seu.");
                                }
                            } else if (args[1].equalsIgnoreCase("moderador")) {
                                CustomPlayer.setGroup("§2MODERADOR", Bukkit.getPlayer(args[0]).getUniqueId());
                                CustomPlayer.setNametag("§2MODERADOR", Bukkit.getPlayer(args[0]).getUniqueId());
                                Bukkit.getPlayer(args[0]).getScoreboard().getTeam("group").setSuffix("§2MODERADOR");
                                player.sendMessage("§aVocê setou o grupo de " + args[0] + " §apara §2§lMODERADOR§a.");
                                Bukkit.getPlayer(args[0]).sendMessage("§aSeu grupo foi alterado para §2§lMODERADOR§a.");
                            } else if (args[1].equalsIgnoreCase("ajudante")) {
                                CustomPlayer.setGroup("§eAJUDANTE", Bukkit.getPlayer(args[0]).getUniqueId());
                                CustomPlayer.setNametag("§eAJUDANTE", Bukkit.getPlayer(args[0]).getUniqueId());
                                Bukkit.getPlayer(args[0]).getScoreboard().getTeam("group").setSuffix("§eAJUDANTE");
                                player.sendMessage("§aVocê setou o grupo de " + args[0] + " §apara §e§lAJUDANTE§a.");
                                Bukkit.getPlayer(args[0]).sendMessage("§aSeu grupo foi alterado para §e§lAJUDANTE§a.");
                            } else if (args[1].equalsIgnoreCase("water")) {
                                CustomPlayer.setGroup("§5WATER", Bukkit.getPlayer(args[0]).getUniqueId());
                                CustomPlayer.setNametag("§5WATER", Bukkit.getPlayer(args[0]).getUniqueId());
                                Bukkit.getPlayer(args[0]).getScoreboard().getTeam("group").setSuffix("§5WATER");
                                player.sendMessage("§aVocê setou o grupo de " + args[0] + " §apara §5§lWATER§a.");
                                Bukkit.getPlayer(args[0]).sendMessage("§aSeu grupo foi alterado para §5§lWATER§a.");
                            } else if (args[1].equalsIgnoreCase("rain")) {
                                CustomPlayer.setGroup("§2RAIN", Bukkit.getPlayer(args[0]).getUniqueId());
                                CustomPlayer.setNametag("§2RAIN", Bukkit.getPlayer(args[0]).getUniqueId());
                                Bukkit.getPlayer(args[0]).getScoreboard().getTeam("group").setSuffix("§2RAIN");
                                player.sendMessage("§aVocê setou o grupo de " + args[0] + " §apara §2§lRAIN§a.");
                                Bukkit.getPlayer(args[0]).sendMessage("§aSeu grupo foi alterado para §2§lRAIN§a.");
                            } else if (args[1].equalsIgnoreCase("cloud")) {
                                CustomPlayer.setGroup("§bCLOUD", Bukkit.getPlayer(args[0]).getUniqueId());
                                CustomPlayer.setNametag("§bCLOUD", Bukkit.getPlayer(args[0]).getUniqueId());
                                Bukkit.getPlayer(args[0]).getScoreboard().getTeam("group").setSuffix("§bCLOUD");
                                player.sendMessage("§aVocê setou o grupo de " + args[0] + " §apara §b§lCLOUD§a.");
                                Bukkit.getPlayer(args[0]).sendMessage("§aSeu grupo foi alterado para §b§lCLOUD§a.");
                            } else if (args[1].equalsIgnoreCase("membro")) {
                                CustomPlayer.setGroup("§7MEMBRO", Bukkit.getPlayer(args[0]).getUniqueId());
                                CustomPlayer.setNametag("§7MEMBRO", Bukkit.getPlayer(args[0]).getUniqueId());
                                Bukkit.getPlayer(args[0]).getScoreboard().getTeam("group").setSuffix("§7MEMBRO");
                                player.sendMessage("§aVocê setou o grupo de " + args[0] + " §apara §7§lMEMBRO§a.");
                                Bukkit.getPlayer(args[0]).sendMessage("§aSeu grupo foi alterado para §7§lMEMBRO§a.");
                            } else {
                                player.sendMessage("§cEste grupo não existe.");
                            }
                        } else {
                            player.sendMessage("§cVocê não pode setar um grupo para você mesmo.");
                        }
                    } else {
                        player.sendMessage("§cEste jogador não existe.");
                    }
                }
            } else {
                player.sendMessage("§cVocê não tem permissão.");
            }
        } else {
            sender.sendMessage("§cApenas jogadores.");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) {
            List<String> names = new ArrayList<>();
            for (Player player : Bukkit.getOnlinePlayers()) {
                names.add(player.getName());
            }
            return StringUtil.copyPartialMatches(args[0], names, new ArrayList<>());
        } else if (args.length == 2) {
            return StringUtil.copyPartialMatches(args[1], Arrays.asList("master", "gerente", "admin", "moderador", "ajudante", "water", "rain", "cloud", "membro"), new ArrayList<>());
        }
        return new ArrayList<>();
    }
}
