package net.woodburry.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import net.woodburry.client.home_page.HomePage;
import net.woodburry.client.login_page.LoginPage;
import net.woodburry.shared.UserInfo;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 5/29/13
 * Time: 11:05 PM
 */
public class WoodburrySite implements EntryPoint {

    private WoodburryInjector injector = GWT.create(WoodburryInjector.class);

    public void onModuleLoad() {
        MainPage mainPage = injector.mainPage();
        RootPanel.get().add(mainPage);
        WoodburryServlet.App.getInstance().getUser(new AsyncCallback<UserInfo>() {
            @Override
            public void onFailure(Throwable caught) {
                injector.mainPage().setPage(new Label("Failed to get User"));
            }

            @Override
            public void onSuccess(UserInfo result) {
                if(result.isLoggedIn()) {
                    injector.mainPage().setPage(injector.homePage());
                } else {
                    injector.mainPage().setPage(injector.loginPage());
                }
            }
        });
    }
}
