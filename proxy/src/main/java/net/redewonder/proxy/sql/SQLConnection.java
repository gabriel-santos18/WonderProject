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

    private Connection connection;

    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL" +
                "=false", USERNAME, PASSWORD);

        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS players(UUID varchar(255), NICK varchar(30), CASH int, FIRST_LOGIN " +
                "varchar(255), LAST_LOGIN varchar(255), GRUPO varchar(255), RANK varchar(255), NAMETAG varchar(255), " +
                "REGISTRADO boolean, BANNED boolean" +
                ");";
        statement.execute(sql);
    }

    public boolean isConnected() {
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
