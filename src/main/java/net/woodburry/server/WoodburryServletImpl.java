package net.woodburry.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sun.deploy.net.HttpResponse;
import net.woodburry.client.WoodburryServlet;
import net.woodburry.shared.CreateUserAccountResponse;
import net.woodburry.shared.UserInfo;
import sun.misc.IOUtils;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
    private static final String CONNECTOR = "connector";
    private static final String LOGGED_IN_USER = "loggedInUser";

    private DataConnector dataConnector() {
        DataConnector dc = (DataConnector)getServletContext().getAttribute(CONNECTOR);
        if(dc == null) {
            dc = new SQLDataConnector();
            getServletContext().setAttribute(CONNECTOR, dc);
        }
        return dc;
    }

    @Override
    public UserInfo getUser() {
        UserInfo user = (UserInfo)getServletContext().getAttribute(LOGGED_IN_USER);
        if(user == null) {
            user = new UserInfo();
            user.setLoggedIn(false);
        }
        return user;
    }

    // test / test
    // salt [B@2643e12a
    // hash [B@3273a006

    @Override
    public CreateUserAccountResponse createUserAccount(String userName, String email, String password) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTES];
        random.nextBytes(salt);
        CreateUserAccountResponse resp = new CreateUserAccountResponse();
        resp.setSuccess(false);
        try {
            URL url = new URL("https://www.minecraft.net/haspaid.jsp?user=" + userName);
            URLConnection con = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String result = in.readLine();
            if(!result.equals("true")) {
                resp.setReason("Your username must be a valid minecraft account");
                return resp;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return resp;
        }

        // Hash the password
        byte[] hash;
        try {
            hash = PasswordHash.pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTES);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            resp.setReason("Server Error");
            return resp;
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            resp.setReason("Server Error");
            return resp;
        }
        System.out.println(PasswordHash.toHex(salt) + ":" +  PasswordHash.toHex(hash));

        dataConnector().createNewUser(userName, email, PasswordHash.toHex(hash), PasswordHash.toHex(salt));

        resp.setSuccess(true);
        return resp;
    }

    @Override
    public void logout() {
        getServletContext().setAttribute(LOGGED_IN_USER, null);
    }

    @Override
    public UserInfo login(String userName, String password) {
        UserInfo userInfo = new UserInfo();
        userInfo.setLoggedIn(false);

        String salt = dataConnector().getSalt(userName);
        if(salt.length() < 1) {
            return userInfo;
        }

        try {
            byte[] hash = PasswordHash.pbkdf2(password.toCharArray(), PasswordHash.fromHex(salt), PBKDF2_ITERATIONS, HASH_BYTES);
            System.out.println("Trying to log in with has " + hash);
            if(dataConnector().validateLogin(userName, PasswordHash.toHex(hash))) {
                userInfo.setLoggedIn(true);
                userInfo.setUserName(userName);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        getServletContext().setAttribute(LOGGED_IN_USER, userInfo);

        return userInfo;
    }
}