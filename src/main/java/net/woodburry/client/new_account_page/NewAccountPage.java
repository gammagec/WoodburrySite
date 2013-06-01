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
import com.google.inject.Singleton;
import net.woodburry.client.WoodburryServlet;

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

    public NewAccountPage() {
        NewAccountPageClientBundle.INSTANCE.css().ensureInjected();
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @UiHandler("submitButton")
    void onSubmitButtonClicked(ClickEvent event) {
        WoodburryServlet.App.getInstance().createUserAccount(username.getText(), password.getText(), new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Failure: " + caught);
            }

            @Override
            public void onSuccess(Boolean result) {
                if(result) {
                    Window.alert("Success!");
                } else {
                    Window.alert("Failure!");
                }
            }
        });
    }
}