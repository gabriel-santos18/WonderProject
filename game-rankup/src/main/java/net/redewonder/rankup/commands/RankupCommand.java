package net.redewonder.rankup.commands;

import me.imfighting.bukkit.api.ActionBarAPI;
import me.imfighting.bukkit.managers.CommandManager;
import net.redewonder.rankup.sql.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class RankupCommand extends CommandManager {


    public RankupCommand() {
        super("rankup", new String[]{});
    }


    public static long PegaPrimirosDigitos(long valor, long digitos) {
        digitos = Math.max(1, digitos);
        long positivo = Math.abs(valor);
        String texto = String.valueOf(positivo);
        if (digitos > texto.length()) {
            return valor;
        }
        return Long.parseLong(texto.substring(0, Math.toIntExact(digitos))) * Long.signum(valor);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (strings.length == 0) {
                if (CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Anfitrite III")) {
                    if (CustomPlayer.getCoins(player.getName()) >= 1500) {
                        CustomPlayer.setRank("Anfitrite II", player.getName());
                        player.sendMessage("§aVocê upou seu rank para §eAnfitrite II §acom sucesso.");
                        new ActionBarAPI("§e"+player.getName()+" §aupou de rank para §eAnfitrite II§a.")
                                .sendToAll();
                        player.getScoreboard().getTeam("rank").setPrefix("  §e" + CustomPlayer.getRank(player.getName()));
                        CustomPlayer.setCoins(CustomPlayer.getCoins(player.getName()) - 1500, player.getName());
                        player.getScoreboard().getTeam("money").setSuffix((CustomPlayer.getCoins(player.getName()) > 999 &&
                                CustomPlayer.getCoins(player.getName()) <= 9999 ?
                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 1) + "K" :
                                CustomPlayer.getCoins(player.getName()) > 9999 &&
                                        CustomPlayer.getCoins(player.getName()) <= 99999 ?
                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 2) + "K" :
                                        CustomPlayer.getCoins(player.getName()) > 99999 &&
                                                CustomPlayer.getCoins(player.getName()) <= 999999 ?
                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 3) + "K" :
                                                CustomPlayer.getCoins(player.getName()) > 999999 &&
                                                        CustomPlayer.getCoins(player.getName()) <= 9999999 ?
                                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 1) + "M" :
                                                        CustomPlayer.getCoins(player.getName()) > 9999999 &&
                                                                CustomPlayer.getCoins(player.getName()) <= 99999999 ?
                                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                        2) + "M" :
                                                                CustomPlayer.getCoins(player.getName()) > 99999999 &&
                                                                        CustomPlayer.getCoins(player.getName()) <= 999999999 ?
                                                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                3) + "M" :
                                                                        CustomPlayer.getCoins(player.getName()) > 999999999 &&
                                                                                CustomPlayer.getCoins(player.getName()) <= 9999999999L ?
                                                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                        1) + "B" :
                                                                                "§a$" + CustomPlayer.getCoins(player.getName())));
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente. §e(1k500)");
                    }
                } else if (CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Anfitrite II")) {
                    if (CustomPlayer.getCoins(player.getName()) >= 2500) {
                        CustomPlayer.setRank("Anfitrite I", player.getName());
                        player.sendMessage("§aVocê upou seu rank para §eAnfitrite I §acom sucesso.");
                        new ActionBarAPI("§e"+player.getName()+" §aupou de rank para §eAnfitrite I§a.")
                                .sendToAll();
                        player.getScoreboard().getTeam("rank").setPrefix("  §e" + CustomPlayer.getRank(player.getName()));
                        CustomPlayer.setCoins(CustomPlayer.getCoins(player.getName()) - 2500, player.getName());
                        player.getScoreboard().getTeam("money").setSuffix((CustomPlayer.getCoins(player.getName()) > 999 &&
                                CustomPlayer.getCoins(player.getName()) <= 9999 ?
                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 1) + "K" :
                                CustomPlayer.getCoins(player.getName()) > 9999 &&
                                        CustomPlayer.getCoins(player.getName()) <= 99999 ?
                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 2) + "K" :
                                        CustomPlayer.getCoins(player.getName()) > 99999 &&
                                                CustomPlayer.getCoins(player.getName()) <= 999999 ?
                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 3) + "K" :
                                                CustomPlayer.getCoins(player.getName()) > 999999 &&
                                                        CustomPlayer.getCoins(player.getName()) <= 9999999 ?
                                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 1) + "M" :
                                                        CustomPlayer.getCoins(player.getName()) > 9999999 &&
                                                                CustomPlayer.getCoins(player.getName()) <= 99999999 ?
                                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                        2) + "M" :
                                                                CustomPlayer.getCoins(player.getName()) > 99999999 &&
                                                                        CustomPlayer.getCoins(player.getName()) <= 999999999 ?
                                                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                3) + "M" :
                                                                        CustomPlayer.getCoins(player.getName()) > 999999999 &&
                                                                                CustomPlayer.getCoins(player.getName()) <= 9999999999L ?
                                                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                        1) + "B" :
                                                                                "§a$" + CustomPlayer.getCoins(player.getName())));
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente. §e(2k500)");
                    }

                } else if (CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Anfitrite I")) {
                    if (CustomPlayer.getCoins(player.getName()) >= 3500) {
                        CustomPlayer.setRank("Nereus III", player.getName());
                        player.sendMessage("§aVocê upou seu rank para §eNereus III §acom sucesso.");
                        new ActionBarAPI("§e"+player.getName()+" §aupou de rank para §eNereus III§a.")
                                .sendToAll();
                        player.getScoreboard().getTeam("rank").setPrefix("  §e" + CustomPlayer.getRank(player.getName()));
                        CustomPlayer.setCoins(CustomPlayer.getCoins(player.getName()) - 3500, player.getName());
                        player.getScoreboard().getTeam("money").setSuffix((CustomPlayer.getCoins(player.getName()) > 999 &&
                                CustomPlayer.getCoins(player.getName()) <= 9999 ?
                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 1) + "K" :
                                CustomPlayer.getCoins(player.getName()) > 9999 &&
                                        CustomPlayer.getCoins(player.getName()) <= 99999 ?
                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 2) + "K" :
                                        CustomPlayer.getCoins(player.getName()) > 99999 &&
                                                CustomPlayer.getCoins(player.getName()) <= 999999 ?
                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 3) + "K" :
                                                CustomPlayer.getCoins(player.getName()) > 999999 &&
                                                        CustomPlayer.getCoins(player.getName()) <= 9999999 ?
                                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 1) + "M" :
                                                        CustomPlayer.getCoins(player.getName()) > 9999999 &&
                                                                CustomPlayer.getCoins(player.getName()) <= 99999999 ?
                                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                        2) + "M" :
                                                                CustomPlayer.getCoins(player.getName()) > 99999999 &&
                                                                        CustomPlayer.getCoins(player.getName()) <= 999999999 ?
                                                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                3) + "M" :
                                                                        CustomPlayer.getCoins(player.getName()) > 999999999 &&
                                                                                CustomPlayer.getCoins(player.getName()) <= 9999999999L ?
                                                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                        1) + "B" :
                                                                                "§a$" + CustomPlayer.getCoins(player.getName())));
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente. §e(3k500)");
                    }
                } else if (CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Nereus III")) {
                    if (CustomPlayer.getCoins(player.getName()) >= 4500) {
                        CustomPlayer.setRank("Nereus II", player.getName());
                        player.sendMessage("§aVocê upou seu rank para §eNereus II §acom sucesso.");
                        new ActionBarAPI("§e"+player.getName()+" §aupou de rank para §eNereus II§a.")
                                .sendToAll();
                        player.getScoreboard().getTeam("rank").setPrefix("  §e" + CustomPlayer.getRank(player.getName()));
                        CustomPlayer.setCoins(CustomPlayer.getCoins(player.getName()) - 4500, player.getName());
                        player.getScoreboard().getTeam("money").setSuffix((CustomPlayer.getCoins(player.getName()) > 999 &&
                                CustomPlayer.getCoins(player.getName()) <= 9999 ?
                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 1) + "K" :
                                CustomPlayer.getCoins(player.getName()) > 9999 &&
                                        CustomPlayer.getCoins(player.getName()) <= 99999 ?
                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 2) + "K" :
                                        CustomPlayer.getCoins(player.getName()) > 99999 &&
                                                CustomPlayer.getCoins(player.getName()) <= 999999 ?
                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 3) + "K" :
                                                CustomPlayer.getCoins(player.getName()) > 999999 &&
                                                        CustomPlayer.getCoins(player.getName()) <= 9999999 ?
                                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 1) + "M" :
                                                        CustomPlayer.getCoins(player.getName()) > 9999999 &&
                                                                CustomPlayer.getCoins(player.getName()) <= 99999999 ?
                                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                        2) + "M" :
                                                                CustomPlayer.getCoins(player.getName()) > 99999999 &&
                                                                        CustomPlayer.getCoins(player.getName()) <= 999999999 ?
                                                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                3) + "M" :
                                                                        CustomPlayer.getCoins(player.getName()) > 999999999 &&
                                                                                CustomPlayer.getCoins(player.getName()) <= 9999999999L ?
                                                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                        1) + "B" :
                                                                                "§a$" + CustomPlayer.getCoins(player.getName())));
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente. §e(4k500)");
                    }

                } else if (CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Nereus I")) {
                    if (CustomPlayer.getCoins(player.getName()) >= 5500) {
                        CustomPlayer.setRank("Proteu III", player.getName());
                        player.sendMessage("§aVocê upou seu rank para §eProteu III §acom sucesso.");
                        new ActionBarAPI("§e"+player.getName()+" §aupou de rank para §eProteu III§a.")
                                .sendToAll();
                        player.getScoreboard().getTeam("rank").setPrefix("  §e" + CustomPlayer.getRank(player.getName()));
                        CustomPlayer.setCoins(CustomPlayer.getCoins(player.getName()) - 5500, player.getName());
                        player.getScoreboard().getTeam("money").setSuffix((CustomPlayer.getCoins(player.getName()) > 999 &&
                                CustomPlayer.getCoins(player.getName()) <= 9999 ?
                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 1) + "K" :
                                CustomPlayer.getCoins(player.getName()) > 9999 &&
                                        CustomPlayer.getCoins(player.getName()) <= 99999 ?
                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 2) + "K" :
                                        CustomPlayer.getCoins(player.getName()) > 99999 &&
                                                CustomPlayer.getCoins(player.getName()) <= 999999 ?
                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 3) + "K" :
                                                CustomPlayer.getCoins(player.getName()) > 999999 &&
                                                        CustomPlayer.getCoins(player.getName()) <= 9999999 ?
                                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 1) + "M" :
                                                        CustomPlayer.getCoins(player.getName()) > 9999999 &&
                                                                CustomPlayer.getCoins(player.getName()) <= 99999999 ?
                                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                        2) + "M" :
                                                                CustomPlayer.getCoins(player.getName()) > 99999999 &&
                                                                        CustomPlayer.getCoins(player.getName()) <= 999999999 ?
                                                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                3) + "M" :
                                                                        CustomPlayer.getCoins(player.getName()) > 999999999 &&
                                                                                CustomPlayer.getCoins(player.getName()) <= 9999999999L ?
                                                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                        1) + "B" :
                                                                                "§a$" + CustomPlayer.getCoins(player.getName())));
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente. §e(5k500)");
                    }
                } else if (CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Proteu III")) {
                    if (CustomPlayer.getCoins(player.getName()) >= 6500) {
                        CustomPlayer.setRank("Proteu II", player.getName());
                        player.sendMessage("§aVocê upou seu rank para §eProteu II §acom sucesso.");
                        new ActionBarAPI("§e"+player.getName()+" §aupou de rank para §eProteu II§a.")
                                .sendToAll();
                        player.getScoreboard().getTeam("rank").setPrefix("  §e" + CustomPlayer.getRank(player.getName()));
                        CustomPlayer.setCoins(CustomPlayer.getCoins(player.getName()) - 6500, player.getName());
                        player.getScoreboard().getTeam("money").setSuffix((CustomPlayer.getCoins(player.getName()) > 999 &&
                                CustomPlayer.getCoins(player.getName()) <= 9999 ?
                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 1) + "K" :
                                CustomPlayer.getCoins(player.getName()) > 9999 &&
                                        CustomPlayer.getCoins(player.getName()) <= 99999 ?
                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 2) + "K" :
                                        CustomPlayer.getCoins(player.getName()) > 99999 &&
                                                CustomPlayer.getCoins(player.getName()) <= 999999 ?
                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 3) + "K" :
                                                CustomPlayer.getCoins(player.getName()) > 999999 &&
                                                        CustomPlayer.getCoins(player.getName()) <= 9999999 ?
                                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 1) + "M" :
                                                        CustomPlayer.getCoins(player.getName()) > 9999999 &&
                                                                CustomPlayer.getCoins(player.getName()) <= 99999999 ?
                                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                        2) + "M" :
                                                                CustomPlayer.getCoins(player.getName()) > 99999999 &&
                                                                        CustomPlayer.getCoins(player.getName()) <= 999999999 ?
                                                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                3) + "M" :
                                                                        CustomPlayer.getCoins(player.getName()) > 999999999 &&
                                                                                CustomPlayer.getCoins(player.getName()) <= 9999999999L ?
                                                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                        1) + "B" :
                                                                                "§a$" + CustomPlayer.getCoins(player.getName())));
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente. §e(6k500)");
                    }
                } else if (CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Proteu II")) {
                    if (CustomPlayer.getCoins(player.getName()) >= 7500) {
                        CustomPlayer.setRank("Proteu I", player.getName());
                        player.sendMessage("§aVocê upou seu rank para §eProteu I §acom sucesso.");
                        new ActionBarAPI("§e"+player.getName()+" §aupou de rank para §eProteu I§a.")
                                .sendToAll();
                        player.getScoreboard().getTeam("rank").setPrefix("  §e" + CustomPlayer.getRank(player.getName()));
                        CustomPlayer.setCoins(CustomPlayer.getCoins(player.getName()) - 7500, player.getName());
                        player.getScoreboard().getTeam("money").setSuffix((CustomPlayer.getCoins(player.getName()) > 999 &&
                                CustomPlayer.getCoins(player.getName()) <= 9999 ?
                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 1) + "K" :
                                CustomPlayer.getCoins(player.getName()) > 9999 &&
                                        CustomPlayer.getCoins(player.getName()) <= 99999 ?
                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 2) + "K" :
                                        CustomPlayer.getCoins(player.getName()) > 99999 &&
                                                CustomPlayer.getCoins(player.getName()) <= 999999 ?
                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 3) + "K" :
                                                CustomPlayer.getCoins(player.getName()) > 999999 &&
                                                        CustomPlayer.getCoins(player.getName()) <= 9999999 ?
                                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 1) + "M" :
                                                        CustomPlayer.getCoins(player.getName()) > 9999999 &&
                                                                CustomPlayer.getCoins(player.getName()) <= 99999999 ?
                                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                        2) + "M" :
                                                                CustomPlayer.getCoins(player.getName()) > 99999999 &&
                                                                        CustomPlayer.getCoins(player.getName()) <= 999999999 ?
                                                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                3) + "M" :
                                                                        CustomPlayer.getCoins(player.getName()) > 999999999 &&
                                                                                CustomPlayer.getCoins(player.getName()) <= 9999999999L ?
                                                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                        1) + "B" :
                                                                                "§a$" + CustomPlayer.getCoins(player.getName())));
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente. §e(7k500)");
                    }
                } else if (CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Proteu I")) {
                    if (CustomPlayer.getCoins(player.getName()) >= 8500) {
                        CustomPlayer.setRank("Poseidon", player.getName());
                        player.sendMessage("§aVocê upou seu rank para §ePoseidon §acom sucesso.");
                        new ActionBarAPI("§e"+player.getName()+" §aupou de rank para §ePoseidon§a.")
                                .sendToAll();
                        player.getScoreboard().getTeam("rank").setPrefix("  §e" + CustomPlayer.getRank(player.getName()));
                        CustomPlayer.setCoins(CustomPlayer.getCoins(player.getName()) - 8500, player.getName());
                        player.getScoreboard().getTeam("money").setSuffix((CustomPlayer.getCoins(player.getName()) > 999 &&
                                CustomPlayer.getCoins(player.getName()) <= 9999 ?
                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 1) + "K" :
                                CustomPlayer.getCoins(player.getName()) > 9999 &&
                                        CustomPlayer.getCoins(player.getName()) <= 99999 ?
                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 2) + "K" :
                                        CustomPlayer.getCoins(player.getName()) > 99999 &&
                                                CustomPlayer.getCoins(player.getName()) <= 999999 ?
                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 3) + "K" :
                                                CustomPlayer.getCoins(player.getName()) > 999999 &&
                                                        CustomPlayer.getCoins(player.getName()) <= 9999999 ?
                                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 1) + "M" :
                                                        CustomPlayer.getCoins(player.getName()) > 9999999 &&
                                                                CustomPlayer.getCoins(player.getName()) <= 99999999 ?
                                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                        2) + "M" :
                                                                CustomPlayer.getCoins(player.getName()) > 99999999 &&
                                                                        CustomPlayer.getCoins(player.getName()) <= 999999999 ?
                                                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                3) + "M" :
                                                                        CustomPlayer.getCoins(player.getName()) > 999999999 &&
                                                                                CustomPlayer.getCoins(player.getName()) <= 9999999999L ?
                                                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                        1) + "B" :
                                                                                "§a$" + CustomPlayer.getCoins(player.getName())));
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente. §e(8k500)");
                    }
                } else if (CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Poseidon")) {
                    player.sendMessage("§cVocê já está no rank máximo.");
                } else if (CustomPlayer.getRank(player.getName()).equalsIgnoreCase("Nereus II")) {
                    if (CustomPlayer.getCoins(player.getName()) >= 5500) {
                        CustomPlayer.setRank("Nereus I", player.getName());
                        player.sendMessage("§aVocê upou seu rank para §eNereus I §acom sucesso.");
                        new ActionBarAPI("§e" + player.getName() + " §aupou de rank para §eNereus I§a.")
                                .sendToAll();
                        player.getScoreboard().getTeam("rank").setPrefix("  §e" + CustomPlayer.getRank(player.getName()));
                        CustomPlayer.setCoins(CustomPlayer.getCoins(player.getName()) - 5500, player.getName());
                        player.getScoreboard().getTeam("money").setSuffix((CustomPlayer.getCoins(player.getName()) > 999 &&
                                CustomPlayer.getCoins(player.getName()) <= 9999 ?
                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 1) + "K" :
                                CustomPlayer.getCoins(player.getName()) > 9999 &&
                                        CustomPlayer.getCoins(player.getName()) <= 99999 ?
                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 2) + "K" :
                                        CustomPlayer.getCoins(player.getName()) > 99999 &&
                                                CustomPlayer.getCoins(player.getName()) <= 999999 ?
                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 3) + "K" :
                                                CustomPlayer.getCoins(player.getName()) > 999999 &&
                                                        CustomPlayer.getCoins(player.getName()) <= 9999999 ?
                                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()), 1) + "M" :
                                                        CustomPlayer.getCoins(player.getName()) > 9999999 &&
                                                                CustomPlayer.getCoins(player.getName()) <= 99999999 ?
                                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                        2) + "M" :
                                                                CustomPlayer.getCoins(player.getName()) > 99999999 &&
                                                                        CustomPlayer.getCoins(player.getName()) <= 999999999 ?
                                                                        "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                3) + "M" :
                                                                        CustomPlayer.getCoins(player.getName()) > 999999999 &&
                                                                                CustomPlayer.getCoins(player.getName()) <= 9999999999L ?
                                                                                "§a$" + PegaPrimirosDigitos(CustomPlayer.getCoins(player.getName()),
                                                                                        1) + "B" :
                                                                                "§a$" + CustomPlayer.getCoins(player.getName())));
                    } else {
                        player.sendMessage("§cVocê não tem coins o suficiente. §e(5k500)");
                    }
                }
            }
        } else {
            commandSender.sendMessage("§cApenas jogadores.");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] strings) {
        return null;
    }
}
