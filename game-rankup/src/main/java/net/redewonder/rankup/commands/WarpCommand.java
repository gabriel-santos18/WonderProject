package net.redewonder.rankup.commands;

import me.imfighting.bukkit.inventory.InventoryBuilder;
import me.imfighting.bukkit.inventory.ItemBuilder;
import me.imfighting.bukkit.managers.CommandManager;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class WarpCommand extends CommandManager {

    public WarpCommand() {
        super("warp", new String[]{});
    }


    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            Inventory inventory = new InventoryBuilder(3, "§8Warps do servidor").toInventory();
            inventory.setItem(11,
                    new ItemBuilder(Material.SKULL_ITEM)
                            .setDurability(Material.SKULL_ITEM, 3)
                            .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDk3NTNhOTU2MjIxOGI1ZTAzZTdkNGY5M2QyMDcxZmI3ZmY0ZDVkYzk4Yjk4YjAwMWY1ZGQwODQyMTUzNTg2MyJ9fX0=")
                            .setDisplayName("§c§l➦ Spawn")
                            .setLore("§6§l「 §fÁrea principal do servidor §6§l」", "", "§e❲Clique para se teleportar❳")
                            .toItemStack());
            inventory.setItem(12,
                    new ItemBuilder(Material.SKULL_ITEM)
                            .setDurability(Material.SKULL_ITEM, 3)
                            .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzBjN2VlYjU3MmUwYzEyMjcxYmUwMGRkMDc0MGQzMWVjMDViYWI2YWNjNDNhNTYwNDhmMTgyMmUxYzNhY2Y4NSJ9fX0=")
                            .setDisplayName("§c§l➦ Mineração")
                            .setLore("§6§l「 §fÁrea de mineração do servidor, consiga dinheiro aqui §6§l」", "", "§e" +
                                    "❲Clique para se teleportar❳")
                            .toItemStack());
            inventory.setItem(13,
                    new ItemBuilder(Material.SKULL_ITEM)
                            .setDurability(Material.SKULL_ITEM, 3)
                            .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjg3MDNmNjY3MzY1MGQzNjE2MTI5MTAyN2NiMGNmOGZiYjdhMzM5ZDY2ODgzMTc2NzQzZjQwMjQ3MzM1NDg5MyJ9fX0=")
                            .setDisplayName("§c§l➦ Terrenos")
                            .setLore("§6§l「 §fÁrea de terrenos, consiga terrenos", "§fpara fazer farm, etc. §6§l」", "",
                                    "§e" +
                                    "❲Clique para se teleportar❳")
                            .toItemStack());
            inventory.setItem(14,
                    new ItemBuilder(Material.SKULL_ITEM)
                            .setDurability(Material.SKULL_ITEM, 3)
                            .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWMzYjE2M2RhODE4OTMwNzgzZDgwMjA0YjRhNDExZmUwZTI5OGJlMGExM2FmYTU0NzYyZWU0MzUxZDdkMjIxNCJ9fX0=")
                            .setDisplayName("§c§l➦ Loja")
                            .setLore("§6§l「 §fLoja oficial do servidor,", "§fclique para comprar blocos, decorações, etc." +
                                            "§6§l」", "")
                            .toItemStack());
            inventory.setItem(15,
                    new ItemBuilder(Material.SKULL_ITEM)
                            .setDurability(Material.SKULL_ITEM, 3)
                            .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjgxNWZjMWNkNjQzY2I1YTA4YWE5YmRjNjZhNjU1MTU3MmY2NDYzMDNmMGNhYTNjZmJjZjNjM2EyNWU1MTFkNCJ9fX0=")
                            .setDisplayName("§c§l➦ Mina PvP")
                            .setLore("§6§l「 §fÁrea de mineração com PvP §4§lON§f. §6§l」", "",
                                    "§e" +
                                            "❲Clique para se teleportar❳")
                            .toItemStack());
            player.openInventory(inventory);
        } else {
            commandSender.sendMessage("§cApenas jogadores!");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] strings) {
        return null;
    }
}
