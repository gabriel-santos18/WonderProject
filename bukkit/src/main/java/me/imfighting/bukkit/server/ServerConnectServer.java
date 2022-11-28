package me.imfighting.bukkit.server;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.imfighting.bukkit.BukkitMain;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class ServerConnectServer implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        if (!channel.equals("BungeeCord")) {
            return;
        }

        ByteArrayDataInput input = ByteStreams.newDataInput(bytes);
        String subchannel = input.readUTF();
    }



    public static void connect(Player player, String server) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("Connect");
        output.writeUTF(server);
        player.sendPluginMessage(BukkitMain.getInstance(), "BungeeCord", output.toByteArray());
    }
}
