package net.woodburry.client.chat;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 6/9/13
 * Time: 11:07 PM
 */
@Singleton
public class ChatDisplay extends Composite {
    interface ChatDisplayUiBinder extends UiBinder<Widget, ChatDisplay> {
    }

    private static ChatDisplayUiBinder ourUiBinder = GWT.create(ChatDisplayUiBinder.class);

    @UiField
    VerticalPanel container;

    public ChatDisplay() {
        ChatClientBundle.INSTANCE.css().ensureInjected();
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public void addChatMessage(String message) {
        container.add(new Label(message));
    }
}