package net.woodburry.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import net.woodburry.client.WoodburryServlet;
import net.woodburry.shared.UserInfo;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 5/30/13
 * Time: 10:50 PM
 */
public class WoodburryServletImpl extends RemoteServiceServlet implements WoodburryServlet {

    public static final int SALT_BYTES = 24;
    public static final int HASH_BYTES = 24;
    public static final int PBKDF2_ITERATIONS = 1000;

    @Override
    public UserInfo getUser() {
        UserInfo user = new UserInfo();
        user.setLoggedIn(false);
        return user;
    }

    // test / test
    // salt [B@2643e12a
    // hash [B@3273a006

    @Override
    public boolean createUserAccount(String userName, String password) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTES];
        random.nextBytes(salt);

        // Hash the password
        byte[] hash = new byte[0];
        try {
            hash = PasswordHash.pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTES);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return false;
        }
        // format salt:hash
        System.out.println(PasswordHash.toHex(salt) + ":" +  PasswordHash.toHex(hash));

        return true;
    }

    @Override
    public UserInfo login(String userName, String password) {
        UserInfo userInfo = new UserInfo();
        userInfo.setLoggedIn(false);
        if(userName.equalsIgnoreCase("test")) {
            byte[] salt = PasswordHash.fromHex("4ebe7b80fa372750918855abbb6978edfe93a4d906bbfd52");
            try {
                byte[] hash = PasswordHash.pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTES);
                System.out.println("Trying to log in with has " + hash);
                if(Arrays.equals(hash, PasswordHash.fromHex("936760ffe1ddcca0eb9636869393a0e8daa6ce1297acab7c"))) {
                    userInfo.setLoggedIn(true);
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }
        return userInfo;
    }
}