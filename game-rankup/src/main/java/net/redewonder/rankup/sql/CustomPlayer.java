package net.redewonder.rankup.sql;

import com.avaje.ebean.enhance.agent.ScopeTransAdapter;
import net.redewonder.rankup.Rankup;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CustomPlayer {

    private static UUID uuid;
    private static Rankup lobby;
    private static int cash;
    private static long coins;
    private static String group;

    private static String nametag;

    private static String rank;

    public CustomPlayer(Rankup lobby, UUID uuid, Player player) throws SQLException {
        this.uuid = uuid;
        this.lobby = lobby;

        PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement("SELECT CASH, " + "GRUPO, RANK, NAMETAG" + " " + "FROM players" + " " + "WHERE NICK = " + "?;");
        statement.setString(1, player.getName());
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
            PreparedStatement statement1 = lobby.getSqlConnection().getConnection().prepareStatement("INSERT INTO" + " players (UUID, NICK, CASH, GRUPO, RANK, NAMETAG) VALUES (" + "'" + uuid.toString() + "'," + "'" + player.getName() + "'," + cash + "," + "'" + group + "'," + "'" + rank + "'," + "'" + nametag + "');");
            statement1.executeUpdate();
        }
    }

    public static boolean playerExists(String nick) {
        try {
            PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement("SELECT * FROM " + "`players` WHERE " + "NICK" + " = '" + nick + "';");
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

    public static void setGroup(String group, String nick) {
        try {
            PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement("UPDATE players " + "SET GRUPO = '" + group + "' WHERE NICK = '" + nick + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setRank(String rank, String nick) {
        try {
            PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement(
                    "UPDATE players " + "SET RANK = '" + rank + "' WHERE NICK = '" + nick + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setNametag(String group, String nick) {
        try {
            PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement("UPDATE players " + "SET NAMETAG = '" + group + "' WHERE NICK = '" + nick + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setCoins(long coins, String nick) {
        try {
            PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement("UPDATE players " + "SET COINS = '" + coins + "' WHERE NICK = '" + nick + "';");
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
            PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement("SELECT * FROM " + "`players` WHERE NICK = '" + player.getName() + "';");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                group = resultSet.getString("GRUPO");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    public static String getRank(String nick) {
        try {
            PreparedStatement statement = Rankup.getInstance().getSqlConnection().getConnection().prepareStatement("SELECT * FROM " + "`players` " + "WHERE " + "NICK = '" + nick + "';");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                rank = resultSet.getString("RANK");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rank;
    }

    public static long getCoins(String nick) {
        try {
            PreparedStatement statement = Rankup.getInstance().getSqlConnection().getConnection().prepareStatement("SELECT * FROM " + "`players` " + "WHERE " + "NICK = '" + nick + "';");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                coins = resultSet.getLong("COINS");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coins;
    }

    public static String getNametag(Player player) {
        try {
            PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement("SELECT * FROM " + "`players` WHERE NICK = '" + player.getName() + "';");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                nametag = resultSet.getString("NAMETAG");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nametag;
    }

    public static long getCoins(Player player) {
        try {
            PreparedStatement statement = lobby.getSqlConnection().getConnection().prepareStatement("SELECT * FROM " + "`players` WHERE NICK = '" + player.getName() + "';");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                coins = resultSet.getLong("COINS");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coins;
    }

    public static boolean plotmePlayer(String nick, String numberTerreno) {
        try {
            PreparedStatement statement = Rankup.getInstance().getSqlConnection().getConnection().prepareStatement("SELECT " + numberTerreno + " FROM " + "`plotme` WHERE " + "NICK" + " = '" + nick + "';");
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

    public static void addPlotme(Player player) {
        try {
            PreparedStatement statement = Rankup.getInstance().getSqlConnection().getConnection().prepareStatement("INSERT INTO plotme " + "(UUID, NICK, TERRENO1, TERRENO2, TERRENO3, TERRENO4, TERRENO5, TERRENO6) VALUES " + "('" + player.getUniqueId() + "'," + "'" + player.getName() + "'," + "false, false, false, false, false, false);");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setPlotme(Player player, String numberTerreno) {
        try {
            if (CustomPlayer.getGroup(player).equalsIgnoreCase("§bCLOUD") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§2RAIN") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§5WATER") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§eAJUDANTE") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§2MODERADOR") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§cADMIN") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§3GERENTE") ||
                    CustomPlayer.getGroup(player).equalsIgnoreCase("§6MASTER")) {
                if (numberTerreno == "TERRENO1") {
                    PreparedStatement statement = Rankup.getInstance().getSqlConnection().getConnection().prepareStatement(
                            "UPDATE " + "plotme " + "SET TERRENO1 = true WHERE NICK = '" + player.getName() + "';");
                    statement.executeUpdate();
                } else if (numberTerreno == "TERRENO2") {
                    PreparedStatement statement = Rankup.getInstance().getSqlConnection().getConnection().prepareStatement(
                            "UPDATE " + "plotme " + "SET TERRENO2 = true WHERE NICK = '" + player.getName() + "';");
                    statement.executeUpdate();
                } else if (numberTerreno == "TERRENO3") {
                    PreparedStatement statement = Rankup.getInstance().getSqlConnection().getConnection().prepareStatement(
                            "UPDATE " + "plotme " + "SET TERRENO3 = true WHERE NICK = '" + player.getName() + "';");
                    statement.executeUpdate();
                } else if (numberTerreno == "TERRENO4") {
                    PreparedStatement statement = Rankup.getInstance().getSqlConnection().getConnection().prepareStatement(
                            "UPDATE " + "plotme " + "SET TERRENO4 = true WHERE NICK = '" + player.getName() + "';");
                    statement.executeUpdate();
                } else if (numberTerreno == "TERRENO5") {
                    PreparedStatement statement = Rankup.getInstance().getSqlConnection().getConnection().prepareStatement(
                            "UPDATE " + "plotme " + "SET TERRENO5 = true WHERE NICK = '" + player.getName() + "';");
                    statement.executeUpdate();
                } else if (numberTerreno == "TERRENO6") {
                    PreparedStatement statement = Rankup.getInstance().getSqlConnection().getConnection().prepareStatement(
                            "UPDATE " + "plotme " + "SET TERRENO6 = true WHERE NICK = '" + player.getName() + "';");
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static boolean getPlotme1(Player player) {
        try {
            PreparedStatement statement = Rankup.getInstance().getSqlConnection().getConnection().prepareStatement(
                    "SELECT * " + "FROM " + "`plotme` WHERE NICK = '" + player.getName() + "' AND TERRENO1 = 1;");
            ResultSet resultSet1 = statement.executeQuery();
            if (resultSet1.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean getPlotme2(Player player) {
        try {
            PreparedStatement statement = Rankup.getInstance().getSqlConnection().getConnection().prepareStatement(
                    "SELECT * " + "FROM " + "`plotme` WHERE NICK = '" + player.getName() + "' AND TERRENO2 = 1;");
            ResultSet resultSet1 = statement.executeQuery();
            if (resultSet1.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean getPlotme3(Player player) {
        try {
            PreparedStatement statement = Rankup.getInstance().getSqlConnection().getConnection().prepareStatement(
                    "SELECT * " + "FROM " + "`plotme` WHERE NICK = '" + player.getName() + "' AND TERRENO3 = 1;");
            ResultSet resultSet1 = statement.executeQuery();
            if (resultSet1.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean getPlotme4(Player player) {
        try {
            PreparedStatement statement = Rankup.getInstance().getSqlConnection().getConnection().prepareStatement(
                    "SELECT * " + "FROM " + "`plotme` WHERE NICK = '" + player.getName() + "' AND TERRENO4 = 1;");
            ResultSet resultSet1 = statement.executeQuery();
            if (resultSet1.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean getPlotme5(Player player) {
        try {
            PreparedStatement statement = Rankup.getInstance().getSqlConnection().getConnection().prepareStatement(
                    "SELECT * " + "FROM " + "`plotme` WHERE NICK = '" + player.getName() + "' AND TERRENO5 = 1;");
            ResultSet resultSet1 = statement.executeQuery();
            if (resultSet1.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean getPlotme6(Player player) {
        try {
            PreparedStatement statement = Rankup.getInstance().getSqlConnection().getConnection().prepareStatement(
                    "SELECT * " + "FROM " + "`plotme` WHERE NICK = '" + player.getName() + "' AND TERRENO6 = 1;");
            ResultSet resultSet1 = statement.executeQuery();
            if (resultSet1.next()) {
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
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isInventoryEmpty(Player p) {
        for (ItemStack item : p.getInventory().getContents()) {
            if (item != null) {
                return false;
            }
        }
        return true;
    }
}
