package net.woodburry.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 6/2/13
 * Time: 11:44 PM
 */
public interface DataConnector {

    boolean createNewUser(String userName, String email, String hash, String salt);
    boolean validateLogin(String userName, String hash);
    String getSalt(String userName);
}
