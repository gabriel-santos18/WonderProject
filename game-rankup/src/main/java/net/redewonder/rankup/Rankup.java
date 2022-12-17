package net.redewonder.rankup;

import net.redewonder.rankup.api.Cuboid;
import net.redewonder.rankup.commands.*;
import net.redewonder.rankup.listeners.PlayerListeners;
import net.redewonder.rankup.listeners.WorldListeners;
import net.redewonder.rankup.managers.LocationsManager;
import net.redewonder.rankup.managers.PlayerManager;
import net.redewonder.rankup.managers.WorldManager;
import net.redewonder.rankup.menus.loja.*;
import net.redewonder.rankup.menus.maquinas.CombustivelMenu;
import net.redewonder.rankup.menus.maquinas.MaquinasMenu;
import net.redewonder.rankup.menus.terrenos.TerrenosMenu;
import net.redewonder.rankup.sql.SQLConnection;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public final class Rankup extends JavaPlugin implements PluginMessageListener {

    private static Rankup instance;
    public File file;

    private SQLConnection sqlConnection;

    private PlayerManager playerManager;



    @Override
    public void onEnable() {
        instance = this;

        Bukkit.getPluginManager().registerEvents(new PlayerListeners(), this);
        Bukkit.getPluginManager().registerEvents(new WorldListeners(), this);

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        registerMenus();


        Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                Cuboid cuboid = new Cuboid(
                        new Location(Bukkit.getWorld("Minas"), -37, 74, 71),
                        new Location(Bukkit.getWorld("Minas"), -66, 59, 35));

                Cuboid cuboid2 = new Cuboid(
                        new Location(Bukkit.getWorld("Minas"), 7, 67, 982),
                        new Location(Bukkit.getWorld("Minas"), -22, 82, 946));

                Cuboid cuboid3 = new Cuboid(
                        new Location(Bukkit.getWorld("Minas"), 963, 104, 241),
                        new Location(Bukkit.getWorld("Minas"), 933, 90, 211));

                for (Block block : cuboid.getBlocks()) {
                    block.setType(Material.LAPIS_ORE);
                }

                for (Block block : cuboid2.getBlocks()) {
                    block.setType(Material.LAPIS_ORE);
                }

                for (Block block : cuboid3.getBlocks()) {
                    block.setType(Material.LAPIS_ORE);
                }

                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendMessage("§a✓ §eMina Lápis §7➸ §eResetada com sucesso.");
                    online.sendMessage("§a✓ §eMina VIP §7➸ §eResetada com sucesso.");
                    online.sendMessage("§a✓ §eMina PvP §7➸ §eResetada com sucesso.");
                    if (cuboid.contains(online.getLocation())) {
                        online.teleport(LocationsManager.getLocation(online, "Mina"));
                    }

                    if (cuboid2.contains(online.getLocation())) {
                        online.teleport(LocationsManager.getLocation(online, "MinaVip"));
                    }

                    if (cuboid3.contains(online.getLocation())) {
                        online.teleport(LocationsManager.getLocation(online, "MinaPvP"));
                    }

                }
            }
        }, 0, 20*180);

        Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                for (LivingEntity entities : Bukkit.getWorld("world").getLivingEntities()) {
                    if (!(entities instanceof Player)) {
                        if (!(entities instanceof ItemFrame)) {
                            entities.remove();
                        }
                    }
                }
            }
        }, 0, 1L);

        WorldCreator wc = new WorldCreator("Minas");
        wc.generator(new WorldManager());
        wc.createWorld();

        WorldCreator wc2 = new WorldCreator("Pesca");
        wc2.generator(new WorldManager());
        wc2.createWorld();

        playerManager = new PlayerManager();

        new SetSpawnCommand();
        new SetCommand();
        new TagCommand();
        new GamemodeCommand();
        new GroupCommand(this);
        new LobbyCommand();
        new TpCommand();
        new KitCommand();
        new WarpCommand();
        new EnderchestCommand();
        new RankupCommand();
        new MaquinasCommand();

        new SpawnCommand();
        new MinaCommand();
        new PescaCommand();
        new TerrenosCommand();
        new LojaCommand();
        new MinaVipCommand();
        new MinaPvPCommand();

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        file = new File(Rankup.getInstance().getDataFolder(), "locations.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Não foi possível carregar!");
                return;
            }
        }

        sqlConnection = new SQLConnection();
        try {
            sqlConnection.connect();
            Bukkit.getConsoleSender().sendMessage("§aMySQL conectado!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registerMenus() {
        Bukkit.getPluginManager().registerEvents(new LojaBlocosMenu(), this);
        Bukkit.getPluginManager().registerEvents(new LojaCombateMenu(), this);
        Bukkit.getPluginManager().registerEvents(new LojaCorantesMenu(), this);
        Bukkit.getPluginManager().registerEvents(new LojaDecoraçõesMenu(), this);
        Bukkit.getPluginManager().registerEvents(new LojaFarmMenu(), this);
        Bukkit.getPluginManager().registerEvents(new LojaMenu(), this);

        Bukkit.getPluginManager().registerEvents(new TerrenosMenu(), this);

        Bukkit.getPluginManager().registerEvents(new MaquinasMenu(), this);
        Bukkit.getPluginManager().registerEvents(new CombustivelMenu(), this);
    }

    @Override
    public void onDisable() {

    }

    public static Rankup getInstance() {
        return instance;
    }

    public SQLConnection getSqlConnection() {
        return sqlConnection;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    @Override
    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {
        if (!s.equals("BungeeCord")) {
            return;
        }
    }
}
