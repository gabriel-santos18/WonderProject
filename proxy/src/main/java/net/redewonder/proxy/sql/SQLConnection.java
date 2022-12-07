package net.redewonder.proxy.sql;

import net.redewonder.proxy.Proxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {

    private final String HOST = Proxy.getInstance().configuration.getString("host");
    private final int PORT = Proxy.getInstance().configuration.getInt("port");
    private final String DATABASE = Proxy.getInstance().configuration.getString("database");
    private final String USERNAME = Proxy.getInstance().configuration.getString("username");
    private final String PASSWORD = Proxy.getInstance().configuration.getString("password");

    private static Connection connection;

    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL" +
                "=false", USERNAME, PASSWORD);

        Statement statement = connection.createStatement();
        String sqlBan = "CREATE TABLE IF NOT EXISTS bans(UUID varchar(255), NICK varchar(30), BANNED boolean, " +
                "AUTOR varchar(255), MOTIVO varchar(255), TEMPO int, UNIDADE varchar(30)" +
                ");";
        statement.execute(sqlBan);

        Statement statement2 = connection.createStatement();
        String sqlMute = "CREATE TABLE IF NOT EXISTS mutes(UUID varchar(255), NICK varchar(30), MUTED boolean, " +
                "AUTOR varchar(255), MOTIVO varchar(255), TEMPO int, UNIDADE varchar(30)" +
                ");";
        statement2.execute(sqlMute);
    }

    public static boolean isConnected() {
        return connection != null;
    }

    public Connection getConnection() {
        return connection;
    }


    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
