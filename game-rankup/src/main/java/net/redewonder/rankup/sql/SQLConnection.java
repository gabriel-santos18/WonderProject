package net.redewonder.rankup.sql;

import net.redewonder.rankup.Rankup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {

    private final String HOST = Rankup.getInstance().getConfig().getString("host");
    private final int PORT = Rankup.getInstance().getConfig().getInt("port");
    private final String DATABASE = Rankup.getInstance().getConfig().getString("database");
    private final String USERNAME = Rankup.getInstance().getConfig().getString("username");
    private final String PASSWORD = Rankup.getInstance().getConfig().getString("password");

    private Connection connection;

    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL" +
                "=false", USERNAME, PASSWORD);

        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS players(UUID varchar(255), NICK varchar(30), CASH int, FIRST_LOGIN " +
                "varchar(255), LAST_LOGIN varchar(255), GRUPO varchar(255), RANK varchar(255), NAMETAG varchar(255))";
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
