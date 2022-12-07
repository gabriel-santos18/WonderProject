package net.redewonder.rankup.sql;

import net.redewonder.rankup.Rankup;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CustomPlayer {

    private static UUID uuid;
    private static Rankup lobby;
    private static int cash;
    private static int coins;
    private static String group;

    private static String nametag;

    private static String rank;

    public CustomPlayer(Rankup lobby, UUID uuid, Player player) throws SQLException {
        this.uuid = uuid;
        this.lobby = lobby;

        PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement("SELECT NICK, CASH, " +
                "GRUPO, RANK, NAMETAG" + " " + "FROM players" + " " + "WHERE UUID = " +
                "?;");
        statement.setString(1, uuid.toString());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            cash = resultSet.getInt("CASH");
            group = resultSet.getString("GRUPO");
            rank = resultSet.getString("RANK");
            nametag = resultSet.getString("NAMETAG");
        } else {
            cash = 0;
            group = "§7MEMBRO";
            rank = "Anfitrite III";
            nametag = "§7MEMBRO";
            PreparedStatement statement1 = lobby.getSqlConnection().getConnection().prepareStatement("INSERT INTO" +
                    " players (UUID, NICK, CASH, GRUPO, RANK, NAMETAG) VALUES (" +
                    "'"+ uuid.toString() + "'," +
                    "'" + player.getName() + "'," +
                    cash + "," +
                    "'" + group + "'," +
                    "'" + rank + "'," +
                    "'" + nametag + "');");
            statement1.executeUpdate();
        }
    }

    public static boolean playerExists(String nick) {
        try {
            PreparedStatement statement =
                    lobby.getSqlConnection().getConnection().prepareStatement("SELECT * FROM " + "`players` WHERE " +
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
            PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement("UPDATE players " +
                    "SET GRUPO = '" + group + "' WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setNametag(String group, UUID uuid) {
        try {
            PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement("UPDATE players " +
                    "SET NAMETAG = '" + group + "' WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setCoins(String coins, UUID uuid) {
        try {
            PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement("UPDATE players " +
                    "SET COINS = '" + coins + "' WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getCash(UUID uuid) {
        try {
            PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement("SELECT * FROM " + "`players` WHERE UUID = '" + uuid + "';");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cash = resultSet.getInt("CASH");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cash;
    }

    public static String getGroup(Player player) {
        try {
            PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement("SELECT * FROM " +
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

    public static String getNametag(Player player) {
        try {
            PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement("SELECT * FROM " +
                    "`players` WHERE NICK = '" + player.getName() + "';");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                nametag = resultSet.getString("NAMETAG");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nametag;
    }

    public static int getCoins(Player player) {
        try {
            PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement("SELECT * FROM " +
                    "`players` WHERE NICK = '" + player.getName() + "';");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                coins = resultSet.getInt("COINS");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coins;
    }
}
