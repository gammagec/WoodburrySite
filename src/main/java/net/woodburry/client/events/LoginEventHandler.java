package net.woodburry.client.events;

import com.google.gwt.event.shared.EventHandler;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 6/7/13
 * Time: 8:55 PM
 */
public interface LoginEventHandler extends EventHandler {
    void onLogin(LoginEvent event);
}
