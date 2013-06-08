package net.woodburry.client.new_account_page;

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
import net.woodburry.client.login_page.LoginPage;
import net.woodburry.shared.CreateUserAccountResponse;

import javax.inject.Provider;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 5/31/13
 * Time: 11:06 PM
 */
@Singleton
public class NewAccountPage extends Composite {

    interface NewAccountPageUiBinder extends UiBinder<Widget, NewAccountPage> {
    }

    private static NewAccountPageUiBinder ourUiBinder = GWT.create(NewAccountPageUiBinder.class);
    @UiField
    TextBox username;
    @UiField
    TextBox password;
    @UiField
    Button submitButton;
    @UiField
    TextBox email;
    @UiField
    Button cancelButton;

    @Inject
    MainPage mainPage;

    @Inject
    Provider<LoginPage> loginPageProvider;

    public NewAccountPage() {
        NewAccountPageClientBundle.INSTANCE.css().ensureInjected();
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @UiHandler("cancelButton")
    void onCancelClicked(ClickEvent event) {
        mainPage.setPage(loginPageProvider.get());
    }

    @UiHandler("submitButton")
    void onSubmitButtonClicked(ClickEvent event) {
        WoodburryServlet.App.getInstance().createUserAccount(username.getText(), email.getText(), password.getText(),
                new AsyncCallback<CreateUserAccountResponse>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Failure: " + caught);
            }

            @Override
            public void onSuccess(CreateUserAccountResponse result) {
                if(result.isSuccess()) {
                    Window.alert("Success!");
                } else {
                    Window.alert(result.getReason());
                }
            }
        });
    }
}