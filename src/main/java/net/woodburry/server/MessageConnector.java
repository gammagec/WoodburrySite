package net.woodburry.server;

import net.woodburry.shared.ChatMessage;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 6/9/13
 * Time: 4:04 PM
 */
public interface MessageConnector {
    List<ChatMessage> getChatMessages();
    void sendChatMessage(String message);
}
