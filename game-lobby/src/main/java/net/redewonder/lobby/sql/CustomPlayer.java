package net.redewonder.lobby.sql;

import net.redewonder.lobby.Lobby;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CustomPlayer {

    private static UUID uuid;
    private static Lobby lobby;
    private static int cash;
    private static String firstLogin;
    private static String lastLogin;
    private static String group;
    private static String rank;

    public CustomPlayer(Lobby lobby, UUID uuid, Player player) throws SQLException {
        this.uuid = uuid;
        this.lobby = lobby;

        PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement("SELECT NICK, CASH, " +
                "FIRST_LOGIN, LAST_LOGIN, GRUPO, RANK" + " " + "FROM players" + " " + "WHERE UUID = ?;");
        statement.setString(1, uuid.toString());
        ResultSet resultSet = statement.executeQuery();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();

        if (resultSet.next()) {
            cash = resultSet.getInt("CASH");
            firstLogin = resultSet.getString("FIRST_LOGIN");
            lastLogin = resultSet.getString("LAST_LOGIN");
            group = resultSet.getString("GRUPO");
            rank = resultSet.getString("RANK");
            player.getScoreboard().getTeam("group").setSuffix(group);
        } else {
            cash = 0;
            firstLogin = formatter.format(date);
            lastLogin = formatter.format(date);
            group = "§7MEMBRO";
            rank = "Anfitrite III";
            player.getScoreboard().getTeam("group").setSuffix(group);
            PreparedStatement statement1 = lobby.getSqlConnection().getConnection().prepareStatement("INSERT INTO" +
                    " players (UUID, NICK, CASH, FIRST_LOGIN, LAST_LOGIN, GRUPO, RANK) VALUES (" +
                    "'"+ uuid.toString() + "'," +
                    "'" + player.getName() + "'," +
                    cash + "," +
                    "'" + firstLogin + "'," +
                    "'" + lastLogin + "'," +
                    "'" + group + "'," +
                    "'" + rank + "');");
            statement1.executeUpdate();
        }
    }

    public static void setLastLogin(String lastLogin) {
        try {
            PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement("UPDATE players " +
                    "SET LAST_LOGIN = '" + lastLogin + "' WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setGroup(String group) {
        try {
            PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement("UPDATE players " +
                    "SET GRUPO = '" + group + "' WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getCash() {
        return cash;
    }
    public static String getFirstLogin() {
        return firstLogin;
    }
    public static String getLastLogin() {
        return lastLogin;
    }

    public static String getGroup() {
        return group;
    }
}
