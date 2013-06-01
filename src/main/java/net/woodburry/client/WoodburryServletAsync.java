package net.woodburry.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import net.woodburry.shared.UserInfo;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 5/30/13
 * Time: 10:50 PM
 */
public interface WoodburryServletAsync {
    void getUser(AsyncCallback<UserInfo> async);
    void createUserAccount(String userName, String password, AsyncCallback<Boolean> async);
    void login(String userName, String password, AsyncCallback<UserInfo> async);
}
