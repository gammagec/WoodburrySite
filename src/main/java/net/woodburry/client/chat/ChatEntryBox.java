package net.woodburry.client.chat;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.security.Provider;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 6/9/13
 * Time: 9:04 PM
 */
@Singleton
public class ChatEntryBox extends Composite {
    interface ChatEntryBoxUiBinder extends UiBinder<Widget, ChatEntryBox> {
    }

    private static ChatEntryBoxUiBinder ourUiBinder = GWT.create(ChatEntryBoxUiBinder.class);
    @UiField
    TextBox text;
    @UiField
    Button sendButton;

    public ChatEntryBox() {
        ChatClientBundle.INSTANCE.css().ensureInjected();
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}