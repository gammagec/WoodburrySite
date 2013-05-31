package net.woodburry.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import net.woodburry.client.WoodburryServlet;
import net.woodburry.shared.UserInfo;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 5/30/13
 * Time: 10:50 PM
 */
public class WoodburryServletImpl extends RemoteServiceServlet implements WoodburryServlet {

    @Override
    public UserInfo getUser() {
        UserInfo user = new UserInfo();
        user.setLoggedIn(true);
        return user;
    }
}