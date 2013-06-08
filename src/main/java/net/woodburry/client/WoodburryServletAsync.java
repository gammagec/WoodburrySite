package net.woodburry.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import net.woodburry.shared.CreateUserAccountResponse;
import net.woodburry.shared.UserInfo;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 5/30/13
 * Time: 10:50 PM
 */
public interface WoodburryServletAsync {
    void getUser(AsyncCallback<UserInfo> async);
    void login(String userName, String password, AsyncCallback<UserInfo> async);
    void logout(AsyncCallback<Void> async);
    void createUserAccount(String userName, String email, String password, AsyncCallback<CreateUserAccountResponse> async);
}
