package net.wonder.login.sql;

import net.wonder.login.Login;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CustomPlayer {

    private static UUID uuid;
    private static Login lobby;
    private static int cash;
    private static String firstLogin;
    private static String lastLogin;
    private static String group;

    private static String nametag;

    private static String rank;
    private static boolean registrado;

    public CustomPlayer(Login lobby, UUID uuid, Player player) throws SQLException {
        this.uuid = uuid;
        this.lobby = lobby;

        PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement("SELECT NICK, CASH, " +
                "FIRST_LOGIN, LAST_LOGIN, GRUPO, RANK, NAMETAG, REGISTRADO" + " " + "FROM players" + " " +
                "WHERE " +
                "UUID = " +
                "?;");
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
            nametag = resultSet.getString("NAMETAG");
            registrado = resultSet.getBoolean("REGISTRADO");
        } else {
            cash = 0;
            firstLogin = formatter.format(date);
            lastLogin = formatter.format(date);
            group = "§7MEMBRO";
            rank = "Anfitrite III";
            nametag = "§7MEMBRO";
            registrado = false;
            PreparedStatement statement1 = lobby.getSqlConnection().getConnection().prepareStatement("INSERT INTO" +
                    " players (UUID, NICK, CASH, FIRST_LOGIN, LAST_LOGIN, GRUPO, RANK, NAMETAG, REGISTRADO) VALUES (" +
                    "'"+ uuid.toString() + "'," +
                    "'" + player.getName() + "'," +
                    cash + "," +
                    "'" + firstLogin + "'," +
                    "'" + lastLogin + "'," +
                    "'" + group + "'," +
                    "'" + rank + "'," +
                    "'" + nametag + "'," +
                    registrado + ");");
            statement1.executeUpdate();
        }
    }

    public static boolean isRegistrado(String nick) {
        try {
            PreparedStatement statement =
                    lobby.getSqlConnection().getConnection().prepareStatement("SELECT * FROM " + "`players` WHERE" +
                            " " +
                            "NICK" +
                            " = '" + nick + "';");
            ResultSet resultSet = statement.executeQuery();
            if (registrado == true) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void createFieldPass() {
        try {
            PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement("ALTER TABLE " +
                    "players ADD SENHA varchar(255);");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setRegister(Player player) {
        try {
            PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement("UPDATE players " +
                    "SET REGISTRADO = true WHERE NICK = '" + player.getName() + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void setPassword(String senha, Player player) {
        try {
            PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement("UPDATE players " +
                    "SET SENHA = password('" + senha + "') WHERE NICK = '" + player.getName() + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public static boolean getPass(Player player, String senha) {
        try {
            PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement("SELECT SENHA " +
                    "FROM " +
                    "`players` WHERE SENHA = password('" + senha + "') AND NICK = '"+player.getName()+"';");
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
}
