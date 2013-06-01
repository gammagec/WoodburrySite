package net.woodburry.client.login_page;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.woodburry.client.MainPage;
import net.woodburry.client.WoodburryServlet;
import net.woodburry.client.home_page.HomePage;
import net.woodburry.client.new_account_page.NewAccountPage;
import net.woodburry.shared.UserInfo;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 5/30/13
 * Time: 10:49 PM
 */
@Singleton
public class LoginPage extends Composite {
    interface LoginPageUiBinder extends UiBinder<Widget, LoginPage> {
    }

    private static LoginPageUiBinder ourUiBinder = GWT.create(LoginPageUiBinder.class);
    @UiField
    Button createAccountButton;
    @UiField
    Button submitButton;
    @UiField
    TextBox username;
    @UiField
    TextBox password;

    @Inject
    private NewAccountPage newAccountPage;

    @Inject
    private MainPage mainPage;

    @Inject
    private HomePage homePage;

    public LoginPage() {
        LoginPageClientBundle.INSTANCE.css().ensureInjected();
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @UiHandler("createAccountButton")
    void onCreateAccountButtonClicked(ClickEvent event) {
        mainPage.setPage(newAccountPage);
    }

    @UiHandler("submitButton")
    void onLoginButtonClicked(ClickEvent event) {
        WoodburryServlet.App.getInstance().login(username.getText(), password.getText(), new AsyncCallback<UserInfo>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Failed to Log in: " + caught);
            }

            @Override
            public void onSuccess(UserInfo result) {
                if(result.isLoggedIn()) {
                    mainPage.setPage(homePage);
                } else {
                    Window.alert("Failed to Log in");
                }
            }
        });
    }
}