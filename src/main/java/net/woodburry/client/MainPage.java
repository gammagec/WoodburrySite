package net.woodburry.client;

import com.chrisgammage.ginjitsu.client.AfterInject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import net.woodburry.client.chat.ChatDisplay;
import net.woodburry.client.chat.ChatEntryBox;
import net.woodburry.client.chat.ChatManager;
import net.woodburry.client.events.LoginEvent;
import net.woodburry.client.events.LoginEventHandler;
import net.woodburry.shared.UserInfo;

import javax.inject.Singleton;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 5/29/13
 * Time: 11:56 PM
 */
@Singleton
public class MainPage extends Composite {

    interface MainPageUiBinder extends UiBinder<Widget, MainPage> {
    }

    private static MainPageUiBinder ourUiBinder = GWT.create(MainPageUiBinder.class);
    @UiField
    SimplePanel body;
    @UiField
    Button logoutButton;

    @Inject
    GlobalEventBus eventBus;

    @Inject
    ChatEntryBox chatEntryBox;

    @Inject
    ChatDisplay chatDisplay;

    public MainPage() {
        MainPageClientBundle.INSTANCE.css().ensureInjected();
        initWidget(ourUiBinder.createAndBindUi(this));
        logoutButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                logout();
            }
        });
    }

    @AfterInject
    final void afterInject() {
        eventBus.addHandler(LoginEvent.TYPE, new LoginEventHandler() {
            @Override
            public void onLogin(LoginEvent event) {
                onUserLogin(event.getUserInfo());
            }
        });

        ((HasWidgets)getWidget()).add(chatEntryBox);
        ((HasWidgets)getWidget()).add(chatDisplay);
    }

    private void onUserLogin(UserInfo userInfo) {
        logoutButton.setVisible(true);
    }

    private void logout() {
        WoodburryServlet.App.getInstance().logout(new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.Location.reload();
            }

            @Override
            public void onSuccess(Void result) {
                Window.Location.reload();
            }
        });
    }

    public void setPage(Widget page) {
        body.setWidget(page);
    }
}