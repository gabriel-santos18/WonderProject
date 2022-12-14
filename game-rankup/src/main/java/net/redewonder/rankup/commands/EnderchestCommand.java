package net.redewonder.rankup.commands;

import me.imfighting.bukkit.managers.CommandManager;
import net.minecraft.server.v1_8_R3.IInventory;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class EnderchestCommand extends CommandManager {

    public EnderchestCommand() {
        super("enderchest", new String[]{"ec"});
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            IInventory inventory = ((CraftPlayer)player).getHandle().getEnderChest();
            ((CraftPlayer)player).getHandle().openContainer(inventory);

            player.sendMessage("§aVocê abriu seu enderchest com sucesso.");
        } else {
            commandSender.sendMessage("§cApenas jogadores.");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] strings) {
        return null;
    }
}
