package net.woodburry.client.home_page;

import com.chrisgammage.ginjitsu.client.AfterInject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.woodburry.client.GlobalEventBus;
import net.woodburry.client.events.LoginEvent;
import net.woodburry.client.events.LoginEventHandler;
import net.woodburry.shared.UserInfo;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 5/30/13
 * Time: 11:06 PM
 */
@Singleton
public class HomePage extends Composite {
    interface HomePageUiBinder extends UiBinder<Widget, HomePage> {
    }

    private static HomePageUiBinder ourUiBinder = GWT.create(HomePageUiBinder.class);
    @UiField
    Label welcomeMessage;

    @Inject
    GlobalEventBus eventBus;

    public HomePage() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @AfterInject
    final void afterInject() {
        eventBus.addHandler(LoginEvent.TYPE, new LoginEventHandler() {
            @Override
            public void onLogin(LoginEvent event) {
                onUserLogin(event.getUserInfo());
            }
        });
    }

    private void onUserLogin(UserInfo userInfo) {
        welcomeMessage.setText("Welcome to Woodburry " + userInfo.getUserName());
    }
}