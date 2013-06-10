package net.woodburry.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;
import net.woodburry.shared.ChatMessage;
import net.woodburry.shared.CreateUserAccountResponse;
import net.woodburry.shared.UserInfo;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 5/30/13
 * Time: 10:50 PM
 */
@RemoteServiceRelativePath("WoodburryServlet")
public interface WoodburryServlet extends RemoteService {

    UserInfo getUser();
    CreateUserAccountResponse createUserAccount(String userName, String email, String password);
    UserInfo login(String userName, String password);
    void logout();
    ChatMessage[] getChatMessages();
    void sendChatMessage(String message);

    /**
     * Utility/Convenience class.
     * Use WoodburryServlet.App.getInstance() to access static instance of WoodburryServletAsync
     */
    public static class App {
        private static final WoodburryServletAsync ourInstance = (WoodburryServletAsync) GWT.create(WoodburryServlet.class);

        public static WoodburryServletAsync getInstance() {
            return ourInstance;
        }
    }
}
