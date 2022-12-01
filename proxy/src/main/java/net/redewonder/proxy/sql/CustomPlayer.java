package net.redewonder.proxy.sql;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.redewonder.proxy.Proxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CustomPlayer {
    private static String group;
    private static boolean banned;

    public static boolean playerExists(String nick) {
        try {
            PreparedStatement statement =
                    Proxy.getInstance().getSqlConnection().getConnection().prepareStatement("SELECT * FROM " + "`players` WHERE" +
                            " " +
                            "NICK" +
                            " = '" + nick + "';");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void setGroup(String group, UUID uuid) {
        try {
            PreparedStatement statement = Proxy.getInstance().getSqlConnection().getConnection().prepareStatement("UPDATE players " +
                    "SET GRUPO = '" + group + "' WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getGroup(ProxiedPlayer player) {
        try {
            PreparedStatement statement = Proxy.getInstance().getSqlConnection().getConnection().prepareStatement("SELECT * FROM " +
                    "`players` WHERE NICK = '" + player.getName() + "';");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                group = resultSet.getString("GRUPO");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    public static void setBanned(String nick) {
        try {
            PreparedStatement statement = Proxy.getInstance().getSqlConnection().getConnection().prepareStatement("UPDATE players " +
                    "SET BANNED = true WHERE NICK = '" + nick + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isBanned(ProxiedPlayer player) {
        try {
            PreparedStatement statement = Proxy.getInstance().getSqlConnection().getConnection().prepareStatement("SELECT * FROM " +
                    "`players` WHERE NICK = '" + player.getName() + "';");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                banned = resultSet.getBoolean("BANNED");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banned;
    }
}
