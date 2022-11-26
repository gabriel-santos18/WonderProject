package net.redewonder.lobby.api;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;

public class ItemBuilder {

    private ItemStack is;

    public ItemBuilder(Material m) {
        this(m, 1);
    }

    public ItemBuilder(Material m, int amount) {
        is = new ItemStack(m, amount);
    }

    public ItemBuilder(ItemStack is) {
        this.is = is;
    }


    public ItemBuilder setDisplayName(String name) {
        ItemMeta isMeta = is.getItemMeta();
        isMeta.setDisplayName(name);
        is.setItemMeta(isMeta);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        ItemMeta isMeta = is.getItemMeta();
        isMeta.setLore(Arrays.asList(lore));
        is.setItemMeta(isMeta);
        return this;
    }

    public ItemBuilder setPlayerSkull(String nick) {
        is = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta isMeta = (SkullMeta) is.getItemMeta();
        isMeta.setOwner(nick);
        is.setItemMeta(isMeta);
        return this;
    }

    public ItemBuilder setSkull(String texture) {
        is = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) is.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", texture));
        Field field;
        try {
            field = skullMeta.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(skullMeta, profile);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        is.setItemMeta(skullMeta);
        return this;
    }

    public ItemBuilder setDurability(Material material, int durability) {
        is = new ItemStack(material, 1, (short) durability);
        return this;
    }


    public ItemStack toItemStack() {
        return is;
    }
}
