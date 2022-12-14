package net.redewonder.rankup.commands;

import me.imfighting.bukkit.inventory.InventoryBuilder;
import me.imfighting.bukkit.inventory.ItemBuilder;
import me.imfighting.bukkit.managers.CommandManager;
import net.redewonder.rankup.listeners.PlayerListeners;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class LojaCommand extends CommandManager {

    Inventory loja;

    public LojaCommand() {
        super("loja", new String[]{});
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            loja = new InventoryBuilder(3, "§8Loja (Categorias)").toInventory();

            loja.setItem(11, new ItemBuilder(Material.SKULL_ITEM).setDurability(Material.SKULL_ITEM, 3).setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzIyODM5ZDVjN2ZjMDY3ODA2MmYxYzZjOGYyN2IzMzIwOTQzODRlM2JiNWM0YjVlYmQxNjc2YjI3OWIwNmJmIn19fQ==").setDisplayName("§c§l✦ §a§lBLOCOS").toItemStack());
            loja.setItem(12, new ItemBuilder(Material.SKULL_ITEM).setDurability(Material.SKULL_ITEM, 3).setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2YwZTRhNjI5MmJjNWVlNzY0MmNhZTczNzZlZWRhNDQ2YzA0NzcxZTM5MDZmOGMwYjU4NjQxY2IzMWNmNjM3MyJ9fX0=").setDisplayName("§c§l✦ §5§lCORANTES").toItemStack());
            loja.setItem(13, new ItemBuilder(Material.SKULL_ITEM).setDurability(Material.SKULL_ITEM, 3).setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWU1NDE0ZDUxMDY2OWUyMjUyNmQ0MGM1NmM4MzQ5YTQ5M2NhNzQxOTMyYWNhMjZkYWVjZmFmYTZiOWY0OTA4NyJ9fX0=").setDisplayName("§c§l✦ §e§lFARM").toItemStack());
            loja.setItem(14, new ItemBuilder(Material.SKULL_ITEM).setDurability(Material.SKULL_ITEM, 3).setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTY0NWE0NGE5YTI4ZGRjNDdkZjZlODQ5ODExMWI2MjdkMGZiZGU5ODUyYjhjN2ZlODQ3MzRmMWQ2ZWVmYzlmYSJ9fX0=").setDisplayName("§c§l✦ §b§lDECORAÇÕES").toItemStack());
            loja.setItem(15, new ItemBuilder(Material.SKULL_ITEM).setDurability(Material.SKULL_ITEM, 3).setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjdhODExYWUyMDhjZTE0ODg4ZWVkNzQ2N2I3ODBhNzJlOTQwOGI2ZGNmMDY2MWM4Nzg2NWNjODY4NTUxZTljIn19fQ==").setDisplayName("§c§l✦ §4§lCOMBATE").toItemStack());

            player.openInventory(loja);
        } else {
            commandSender.sendMessage("§cApenas jogadores.");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] strings) {
        return null;
    }
}
