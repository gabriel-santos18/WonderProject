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
    private static String reason;
    private static boolean banned;

    public static boolean playerExists(String nick) {
        try {
            PreparedStatement statement = Proxy.getInstance().getSqlConnection().getConnection().prepareStatement("SELECT * FROM " + "`players` WHERE" + " " + "NICK" + " = '" + nick + "';");
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

    public static String getGroup(ProxiedPlayer player) {
        try {
            PreparedStatement statement = Proxy.getInstance().getSqlConnection().getConnection().prepareStatement("SELECT * FROM " + "`players` WHERE NICK = '" + player.getName() + "';");
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
            PreparedStatement statement = Proxy.getInstance().getSqlConnection().getConnection().prepareStatement("SELECT * FROM " + "`bans` WHERE NICK = '" + player.getName() + "';");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                reason = resultSet.getString("MOTIVO");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reason;
    }

    public static boolean getPermMute(ProxiedPlayer player) {
        try {
            PreparedStatement statement = Proxy.getInstance().getSqlConnection().getConnection().prepareStatement(
                    "SELECT * FROM " + "`mutes` WHERE NICK = '" + player.getName() + "' AND TEMPO = 0;");
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

    public static boolean getPermBan(ProxiedPlayer player) {
        try {
            PreparedStatement statement = Proxy.getInstance().getSqlConnection().getConnection().prepareStatement(
                    "SELECT * FROM " + "`bans` WHERE NICK = '" + player.getName() + "' AND TEMPO = 0;");
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

    public static void setBan(String nick, String autor, String motivo, int tempo, String unidade) {
        try {
            PreparedStatement statement = Proxy.getInstance().getSqlConnection().getConnection().prepareStatement("INSERT INTO" +
                    " bans (NICK, BANNED, AUTOR, MOTIVO, TEMPO, UNIDADE) VALUES (?,?,?,?,?,?)");
            statement.setString(1, nick);
            statement.setBoolean(2, true);
            statement.setString(3, autor);
            statement.setString(4, motivo);
            statement.setInt(5, tempo);
            statement.setString(6, unidade);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setMute(String nick, String autor, String motivo, int tempo, String unidade) {
        try {

            PreparedStatement statement = Proxy.getInstance().getSqlConnection().getConnection().prepareStatement("INSERT INTO" +
                    " mutes (NICK, MUTED, AUTOR, MOTIVO, TEMPO, UNIDADE) VALUES (?,?,?,?,?,?)");
            statement.setString(1, nick);
            statement.setBoolean(2, true);
            statement.setString(3, autor);
            statement.setString(4, motivo);
            statement.setInt(5, tempo);
            statement.setString(6, unidade);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteBan(String nick) {
        try {

            PreparedStatement statement = Proxy.getInstance().getSqlConnection().getConnection().prepareStatement(
                    "DELETE FROM bans " + "WHERE NICK = '" + nick + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMute(String nick) {
        try {

            PreparedStatement statement = Proxy.getInstance().getSqlConnection().getConnection().prepareStatement(
                    "DELETE FROM mutes " + "WHERE NICK = '" + nick + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isBanned(String nick) {
        try {
            PreparedStatement statement = Proxy.getInstance().getSqlConnection().getConnection().prepareStatement(
                    "SELECT * FROM " + "`bans` WHERE NICK = '" + nick + "';");
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

    public static boolean isMuted(String nick) {
        try {
            PreparedStatement statement = Proxy.getInstance().getSqlConnection().getConnection().prepareStatement(
                    "SELECT * FROM " + "`mutes` WHERE NICK = '" + nick + "';");
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

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
