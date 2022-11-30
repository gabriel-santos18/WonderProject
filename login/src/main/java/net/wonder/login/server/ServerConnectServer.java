package net.wonder.login.server;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.wonder.login.Login;
import org.bukkit.entity.Player;

public class ServerConnectServer {
    public static void connect(Player player, String server) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("Connect");
        output.writeUTF(server);
        player.sendPluginMessage(Login.getInstance(), "BungeeCord", output.toByteArray());
    }
}
