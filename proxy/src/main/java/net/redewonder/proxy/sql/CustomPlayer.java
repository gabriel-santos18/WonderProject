package net.redewonder.proxy.sql;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.protocol.packet.Login;
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

    private static String reason;

    private static String autor;

    private static Proxy proxy;
    private static UUID uuid;

    public CustomPlayer(Proxy proxy, UUID uuid, ProxiedPlayer player) throws SQLException {
        this.uuid = uuid;
        this.proxy = proxy;

        PreparedStatement statement = proxy.getSqlConnection().getConnection().prepareStatement("SELECT NICK, BANNED," +
                " " +
                "AUTOR, MOTIVO" + " " + "FROM bans" + " " +
                "WHERE " +
                "UUID = " +
                "?;");
        statement.setString(1, uuid.toString());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            banned = resultSet.getBoolean("BANNED");
            autor = resultSet.getString("AUTOR");
            reason = resultSet.getString("MOTIVO");
        } else {
            banned = false;
            reason = "...";
            autor = "...";
            PreparedStatement statement1 = proxy.getSqlConnection().getConnection().prepareStatement("INSERT INTO" +
                    " bans (UUID, NICK, BANNED, AUTOR, MOTIVO) VALUES (" +
                    "'"+ uuid.toString() + "'," +
                    "'" + player.getName() + "'," +
                    banned + "," +
                    "'" + autor + "'," +
                    "'" + reason + "');");
            statement1.executeUpdate();
        }
    }

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

    public static String getReason(ProxiedPlayer player) {
        try {
            PreparedStatement statement = Proxy.getInstance().getSqlConnection().getConnection().prepareStatement("SELECT * FROM " +
                    "`bans` WHERE NICK = '" + player.getName() + "';");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                reason = resultSet.getString("MOTIVO");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reason;
    }

    public static void setBanned(String nick) {
        try {
            PreparedStatement statement = Proxy.getInstance().getSqlConnection().getConnection().prepareStatement(
                    "UPDATE bans " +
                    "SET BANNED = true WHERE NICK = '" + nick + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setAuthor(String nick, String autor) {
        try {
            PreparedStatement statement = Proxy.getInstance().getSqlConnection().getConnection().prepareStatement(
                    "UPDATE bans " +
                            "SET AUTOR = '"+ autor + "' WHERE NICK = '" + nick + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setReason(String nick, String reason) {
        try {
            PreparedStatement statement = Proxy.getInstance().getSqlConnection().getConnection().prepareStatement(
                    "UPDATE bans " +
                            "SET MOTIVO = '"+ reason + "' WHERE NICK = '" + nick + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isBanned(ProxiedPlayer player) {
        try {
            PreparedStatement statement = Proxy.getInstance().getSqlConnection().getConnection().prepareStatement("SELECT * FROM " +
                    "`bans` WHERE UUID = '" + player.getUniqueId() + "';");
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
