package net.woodburry.server;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 6/2/13
 * Time: 11:45 PM
 */
public class SQLDataConnector implements DataConnector {

    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    @Override
    public boolean createNewUser(String userName, String email, String hash, String salt) {
        connect();
        String sql = "insert into woodburry_site.users values ('" +
                userName + "','" + email + "','" + hash + "','" + salt + "')";
        try {
            System.out.println("Executing sql: " + sql);
            boolean isNum = statement.execute(sql);
            if(isNum) {
                return false;
            } else {
                return statement.getUpdateCount() == 1;
            }
        } catch (SQLException e) {
            System.err.println(e.toString());
            return false;
        }
    }

    @Override
    public boolean validateLogin(String userName, String hash) {
        return getHash(userName).equals(hash);
    }

    private String getHash(String userName) {
        connect();
        String sql = "select password from woodburry_site.users where id = '" + userName + "'";
        String hash;
        try {
            System.out.println("Getting hash for " + userName);
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
                hash = resultSet.getString(1);
                return hash;
            } else {
                return "";
            }
        } catch (SQLException e) {
            System.err.println(e.toString());
            return "";
        }
    }

    @Override
    public String getSalt(String userName) {
        connect();
        String sql = "select salt from woodburry_site.users where id = '" + userName + "'";
        String salt;
        try {
            System.out.println("Executing sql: " + sql);
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
                salt = resultSet.getString(1);
                System.out.println("Got salt " + salt);
                return salt;
            } else {
                return "";
            }
        } catch (SQLException e) {
            System.err.println(e.toString());
            return "";
        }
    }

    private void connect() {
        try {
            if(connect != null && !connect.isClosed()) {
                return;
            }
        } catch (SQLException e) {
            System.err.println(e.toString());
            return;
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/woodburry_site?"
                            + "user=root&password=admin");

            statement = connect.createStatement();
            resultSet = statement
                    .executeQuery("select * from woodburry_site.users");

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
}
