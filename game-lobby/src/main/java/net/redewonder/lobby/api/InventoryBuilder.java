package net.redewonder.lobby.api;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class InventoryBuilder {

    Inventory inventory;

    public InventoryBuilder(int size, String title) {
        inventory = Bukkit.createInventory(null, 9*size, title);
    }

    public Inventory toInventory() {
        return inventory;
    }
}
