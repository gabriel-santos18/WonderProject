package net.redewonder.lobby.server;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.imfighting.bukkit.BukkitMain;
import net.redewonder.lobby.Lobby;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class ServerConnectServer {

    public static void connect(Player player, String server) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("Connect");
        output.writeUTF(server);
        player.sendPluginMessage(Lobby.getInstance(), "BungeeCord", output.toByteArray());
    }
}
