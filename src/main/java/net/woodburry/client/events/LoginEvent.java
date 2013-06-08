package net.woodburry.client.events;

import com.google.gwt.event.shared.GwtEvent;
import net.woodburry.shared.UserInfo;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 6/7/13
 * Time: 8:55 PM
 */
public class LoginEvent extends GwtEvent<LoginEventHandler> {
    public static Type<LoginEventHandler> TYPE = new Type<LoginEventHandler>();

    private final UserInfo userInfo;

    public LoginEvent(UserInfo user) {
        userInfo = user;
    }

    public Type<LoginEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(LoginEventHandler handler) {
        handler.onLogin(this);
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
