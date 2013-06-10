package net.woodburry.client.chat;

import com.chrisgammage.ginjitsu.client.AfterInject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.woodburry.client.GlobalEventBus;
import net.woodburry.client.WoodburryServlet;
import net.woodburry.client.events.LoginEvent;
import net.woodburry.client.events.LoginEventHandler;
import net.woodburry.shared.ChatMessage;
import net.woodburry.shared.UserInfo;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 6/9/13
 * Time: 2:22 PM
 */
@Singleton
public class ChatManager {

    @Inject
    GlobalEventBus eventBus;

    @Inject
    private ChatEntryBox chatEntryBox;

    @Inject
    private ChatDisplay chatDisplay;

    public Widget getChatEntryBox() {
        return chatEntryBox;
    }

    @AfterInject
    final void afterInject() {
        eventBus.addHandler(LoginEvent.TYPE, new LoginEventHandler() {
            @Override
            public void onLogin(LoginEvent event) {
                afterLogin(event.getUserInfo());
            }
        });
        chatEntryBox.sendButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onSendChat(chatEntryBox.text.getText());
            }
        });
    }

    private void onSendChat(String text) {
        WoodburryServlet.App.getInstance().sendChatMessage(text, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                caught.printStackTrace();
            }

            @Override
            public void onSuccess(Void result) {
            }
        });
    }

    private void afterLogin(UserInfo userInfo) {
        chatEntryBox.setVisible(true);
        fetchMessages();
    }

    private void processChatMessage(ChatMessage chatMessage) {
        chatDisplay.addChatMessage(chatMessage.getUser() + ":" + chatMessage.getText());
    }

    private void fetchMessages() {
        WoodburryServlet.App.getInstance().getChatMessages(new AsyncCallback<ChatMessage[]>() {
            @Override
            public void onFailure(Throwable caught) {
                caught.printStackTrace();
            }

            @Override
            public void onSuccess(ChatMessage[] result) {
                for(ChatMessage msg : result) {
                    processChatMessage(msg);
                }
                fetchMessages();
            }
        });
    }
}
