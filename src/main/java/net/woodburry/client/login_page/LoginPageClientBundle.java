package net.woodburry.client.login_page;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 5/30/13
 * Time: 11:44 PM
 */
public interface LoginPageClientBundle extends ClientBundle {

    static public LoginPageClientBundle INSTANCE = GWT.create(LoginPageClientBundle.class);

    interface MyCss extends CssResource {
        String loginPageBody();
        String loginPanel();
        @ClassName("gwt-Label")
        String gwtLabel();
        String createAccountButton();
        String loginButton();
        String loginSign();
        String loginSignText();
    }

    @Source("LoginPage.css")
    MyCss css();
}
