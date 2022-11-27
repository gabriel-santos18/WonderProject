package net.redewonder.lobby.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.redewonder.lobby.Lobby;
import net.redewonder.lobby.group.Groups;
import net.redewonder.lobby.managers.CommandManager;
import net.redewonder.lobby.sql.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.xml.soap.Text;
import java.awt.*;
import java.util.List;

public class TagCommand extends CommandManager {
    public TagCommand() {
        super("tag", new String[]{});
    }

    // /tag <tag>

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 1) {
                TextComponent start = new TextComponent("§aSuas tags: ");

                TextComponent master = new TextComponent("§6§lMASTER");
                master.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tag master"));
                master.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{new TextComponent("§eClique aqui para alterar sua tag.")}));

                TextComponent gerente = new TextComponent("§3§lGERENTE");
                gerente.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tag gerente"));
                gerente.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{new TextComponent("§eClique aqui para alterar sua tag.")}));

                TextComponent admin = new TextComponent("§c§lADMIN");
                admin.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tag admin"));
                admin.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{new TextComponent("§eClique aqui para alterar sua tag.")}));

                TextComponent moderador = new TextComponent("§2§lMODERADOR");
                moderador.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tag moderador"));
                moderador.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{new TextComponent("§eClique aqui para alterar sua tag.")}));

                TextComponent ajudante = new TextComponent("§e§lAJUDANTE");
                ajudante.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tag ajudante"));
                ajudante.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{new TextComponent("§eClique aqui para alterar sua tag.")}));

                TextComponent water = new TextComponent("§5§lWATER");
                water.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tag water"));
                water.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{new TextComponent("§eClique aqui para alterar sua tag.")}));

                TextComponent rain = new TextComponent("§2§lRAIN");
                rain.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tag rain"));
                rain.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{new TextComponent("§eClique aqui para alterar sua tag.")}));

                TextComponent cloud = new TextComponent("§b§lCLOUD");
                cloud.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tag cloud"));
                cloud.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{new TextComponent("§eClique aqui para alterar sua tag.")}));

                TextComponent membro = new TextComponent("§7§lMEMBRO");
                membro.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tag membro"));
                membro.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{new TextComponent("§eClique aqui para alterar sua tag.")}));

                TextComponent period = new TextComponent("§a, ");

                TextComponent end = new TextComponent("§a.");

                if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER")) {
                    start.addExtra(master);
                    start.addExtra(period);
                    start.addExtra(gerente);
                    start.addExtra(period);
                    start.addExtra(admin);
                    start.addExtra(period);
                    start.addExtra(moderador);
                    start.addExtra(period);
                    start.addExtra(ajudante);
                    start.addExtra(period);
                    start.addExtra(water);
                    start.addExtra(period);
                    start.addExtra(rain);
                    start.addExtra(period);
                    start.addExtra(cloud);
                    start.addExtra(period);
                    start.addExtra(membro);
                    start.addExtra(end);
                    player.spigot().sendMessage(start);
                } else if (CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE")) {
                    start.addExtra(gerente);
                    start.addExtra(period);
                    start.addExtra(admin);
                    start.addExtra(period);
                    start.addExtra(moderador);
                    start.addExtra(period);
                    start.addExtra(ajudante);
                    start.addExtra(period);
                    start.addExtra(water);
                    start.addExtra(period);
                    start.addExtra(rain);
                    start.addExtra(period);
                    start.addExtra(cloud);
                    start.addExtra(period);
                    start.addExtra(membro);
                    start.addExtra(end);
                    player.spigot().sendMessage(start);
                } else if (CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN")) {
                    start.addExtra(admin);
                    start.addExtra(period);
                    start.addExtra(moderador);
                    start.addExtra(period);
                    start.addExtra(ajudante);
                    start.addExtra(period);
                    start.addExtra(water);
                    start.addExtra(period);
                    start.addExtra(rain);
                    start.addExtra(period);
                    start.addExtra(cloud);
                    start.addExtra(period);
                    start.addExtra(membro);
                    start.addExtra(end);
                    player.spigot().sendMessage(start);
                } else if (CustomPlayer.getGroup(player).equalsIgnoreCase("§2MODERADOR")) {
                    start.addExtra(moderador);
                    start.addExtra(period);
                    start.addExtra(ajudante);
                    start.addExtra(period);
                    start.addExtra(water);
                    start.addExtra(period);
                    start.addExtra(rain);
                    start.addExtra(period);
                    start.addExtra(cloud);
                    start.addExtra(period);
                    start.addExtra(membro);
                    start.addExtra(end);
                    player.spigot().sendMessage(start);
                } else if (CustomPlayer.getGroup(player).equalsIgnoreCase("§eAJUDANTE")) {
                    start.addExtra(ajudante);
                    start.addExtra(period);
                    start.addExtra(water);
                    start.addExtra(period);
                    start.addExtra(rain);
                    start.addExtra(period);
                    start.addExtra(cloud);
                    start.addExtra(period);
                    start.addExtra(membro);
                    start.addExtra(end);
                    player.spigot().sendMessage(start);
                } else if (CustomPlayer.getGroup(player).equalsIgnoreCase("§5WATER")) {
                    start.addExtra(water);
                    start.addExtra(period);
                    start.addExtra(rain);
                    start.addExtra(period);
                    start.addExtra(cloud);
                    start.addExtra(period);
                    start.addExtra(membro);
                    start.addExtra(end);
                    player.spigot().sendMessage(start);
                } else if (CustomPlayer.getGroup(player).equalsIgnoreCase("§2RAIN")) {
                    start.addExtra(rain);
                    start.addExtra(period);
                    start.addExtra(cloud);
                    start.addExtra(period);
                    start.addExtra(membro);
                    start.addExtra(end);
                    player.spigot().sendMessage(start);
                } else if (CustomPlayer.getGroup(player).equalsIgnoreCase("§bCLOUD")) {
                    start.addExtra(cloud);
                    start.addExtra(period);
                    start.addExtra(membro);
                    start.addExtra(end);
                    player.spigot().sendMessage(start);
                } else if (CustomPlayer.getGroup(player).equalsIgnoreCase("§7MEMBRO")) {
                    start.addExtra(membro);
                    start.addExtra(end);
                    player.spigot().sendMessage(start);
                }
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("master")) {
                    if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER")) {
                        if (!CustomPlayer.getNametag(player).equalsIgnoreCase("§6MASTER")) {
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.getScoreboard().getTeam(Groups.MASTER.getOrderSymbol() + Groups.MASTER.name()).addEntry(player.getName());
                            }
                            CustomPlayer.setNametag("§6MASTER", player.getUniqueId());
                            player.sendMessage("§aVocê alterou sua tag para §6§lMASTER§a.");
                        } else {
                            player.sendMessage("§cVocê já está usando esta tag.");
                        }
                    } else {
                        player.sendMessage("§cVocê não possui esta tag.");
                    }
                } else if (args[0].equalsIgnoreCase("gerente")) {
                    if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER") || (CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE"))) {
                        if (!CustomPlayer.getNametag(player).equalsIgnoreCase("§6MASTER")) {
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.getScoreboard().getTeam(Groups.GERENTE.getOrderSymbol() + Groups.GERENTE.name()).addEntry(player.getName());
                            }
                            CustomPlayer.setNametag("§3GERENTE", player.getUniqueId());
                            player.sendMessage("§aVocê alterou sua tag para §3§lGERENTE§a.");
                        } else {
                            player.sendMessage("§cVocê já está usando esta tag.");
                        }
                    } else {

                    }
                } else if (args[0].equalsIgnoreCase("admin")) {
                    if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER") || (CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE") || (CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN")))) {
                        if (!CustomPlayer.getNametag(player).equalsIgnoreCase("§cADMIN")) {
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.getScoreboard().getTeam(Groups.ADMIN.getOrderSymbol() + Groups.ADMIN.name()).addEntry(player.getName());
                            }
                            CustomPlayer.setNametag("§cADMIN", player.getUniqueId());
                            player.sendMessage("§aVocê alterou sua tag para §c§lADMIN§a.");
                        } else {
                            player.sendMessage("§cVocê já está usando esta tag.");
                        }
                    } else {
                        player.sendMessage("§cVocê não possui esta tag.");
                    }
                } else if (args[0].equalsIgnoreCase("moderador")) {
                    if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER") || (CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§2MODERADOR"))) {
                        if (!CustomPlayer.getNametag(player).equalsIgnoreCase("§2MODERADOR")) {
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.getScoreboard().getTeam(Groups.MODERADOR.getOrderSymbol() + Groups.MODERADOR.name()).addEntry(player.getName());
                            }
                            CustomPlayer.setNametag("§2MODERADOR", player.getUniqueId());
                            player.sendMessage("§aVocê alterou sua tag para §2§lMODERADOR§a.");
                        } else {
                            player.sendMessage("§cVocê já está usando esta tag.");
                        }
                    } else {
                        player.sendMessage("§cVocê não possui esta tag.");
                    }
                } else if (args[0].equalsIgnoreCase("ajudante")) {
                    if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER") || (CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§2MODERADOR")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§eAJUDANTE"))) {
                        if (!CustomPlayer.getNametag(player).equalsIgnoreCase("§eAJUDANTE")) {
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.getScoreboard().getTeam(Groups.AJUDANTE.getOrderSymbol() + Groups.AJUDANTE.name()).addEntry(player.getName());
                            }
                            CustomPlayer.setNametag("§eAJUDANTE", player.getUniqueId());
                            player.sendMessage("§aVocê alterou sua tag para §e§lAJUDANTE§a.");
                        } else {
                            player.sendMessage("§cVocê já está usando esta tag.");
                        }
                    } else {
                        player.sendMessage("§cVocê não possui esta tag.");
                    }
                } else if (args[0].equalsIgnoreCase("water")) {
                    if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER") || (CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§2MODERADOR")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§eAJUDANTE")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§5WATER"))) {
                        if (!CustomPlayer.getNametag(player).equalsIgnoreCase("§5WATER")) {
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.getScoreboard().getTeam(Groups.WATER.getOrderSymbol() + Groups.WATER.name()).addEntry(player.getName());
                            }
                            CustomPlayer.setNametag("§5WATER", player.getUniqueId());
                            player.sendMessage("§aVocê alterou sua tag para §5§lWATER§a.");
                        } else {
                            player.sendMessage("§cVocê já está usando esta tag.");
                        }
                    } else {
                        player.sendMessage("§cVocê não possui esta tag.");
                    }
                } else if (args[0].equalsIgnoreCase("rain")) {
                    if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER") || (CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§2MODERADOR")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§eAJUDANTE")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§5WATER")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§2RAIN"))) {
                        if (!CustomPlayer.getNametag(player).equalsIgnoreCase("§2RAIN")) {
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.getScoreboard().getTeam(Groups.RAIN.getOrderSymbol() + Groups.RAIN.name()).addEntry(player.getName());
                            }
                            CustomPlayer.setNametag("§2RAIN", player.getUniqueId());
                            player.sendMessage("§aVocê alterou sua tag para §2§lRAIN§a.");
                        } else {
                            player.sendMessage("§cVocê já está usando esta tag.");
                        }
                    } else {
                        player.sendMessage("§cVocê não possui esta tag.");
                    }
                } else if (args[0].equalsIgnoreCase("cloud")) {
                    if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER") || (CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§2MODERADOR")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§eAJUDANTE")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§5WATER")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§2RAIN")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§bCLOUD"))) {
                        if (!CustomPlayer.getNametag(player).equalsIgnoreCase("§bCLOUD")) {
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.getScoreboard().getTeam(Groups.CLOUD.getOrderSymbol() + Groups.CLOUD.name()).addEntry(player.getName());
                            }
                            CustomPlayer.setNametag("§bCLOUD", player.getUniqueId());
                            player.sendMessage("§aVocê alterou sua tag para §b§lCLOUD§a.");
                        } else {
                            player.sendMessage("§cVocê já está usando esta tag.");
                        }
                    } else {
                        player.sendMessage("§cVocê não possui esta tag.");
                    }
                } else if (args[0].equalsIgnoreCase("membro")) {
                    if (CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER") || (CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§2MODERADOR")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§eAJUDANTE")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§5WATER")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§2RAIN")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§bCLOUD")) || (CustomPlayer.getGroup(player).equalsIgnoreCase("§7MEMBRO"))) {
                        if (!CustomPlayer.getNametag(player).equalsIgnoreCase("§7MEMBRO")) {
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.getScoreboard().getTeam(Groups.MEMBRO.getOrderSymbol() + Groups.MEMBRO.name()).addEntry(player.getName());
                            }
                            CustomPlayer.setNametag("§7MEMBRO", player.getUniqueId());
                            player.sendMessage("§aVocê alterou sua tag para §7§lMEMBRO§a.");
                        } else {
                            player.sendMessage("§cVocê já está usando esta tag.");
                        }
                    }
                }
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
