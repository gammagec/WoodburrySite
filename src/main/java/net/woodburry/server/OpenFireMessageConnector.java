package net.woodburry.server;

import net.woodburry.shared.ChatMessage;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.muc.MultiUserChat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 6/9/13
 * Time: 4:08 PM
 */
public class OpenFireMessageConnector implements MessageConnector, PacketListener {

    private List<ChatMessage> chatMessages =
            new ArrayList<ChatMessage>();

    private XMPPConnection connection;
    private MultiUserChat chatRoom;

    public static final String CHAT_ROOM_NAME = "woodburry@conference.chrisgammage.com";
    private final String userName;

    private Object obj = new Object();

    public OpenFireMessageConnector(String userName) {
        this.userName = userName;
    }

    @Override
    public ArrayList<ChatMessage> getChatMessages() {
        try {
            connect();
        } catch (XMPPException e) {
            e.printStackTrace();
            return new ArrayList<ChatMessage>();
        }
        if(chatMessages.size() == 0) {
            synchronized (obj) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return new ArrayList<ChatMessage>();
                }
            }
        }
        ArrayList<ChatMessage> ret = new ArrayList<ChatMessage>();
        ret.addAll(chatMessages);
        chatMessages.clear();
        return ret;
    }

    @Override
    public void sendChatMessage(String message) {
        try {
            connect();
        } catch (XMPPException e) {
            e.printStackTrace();
            return;
        }
        try {
            chatRoom.sendMessage(message.toString());
        } catch (XMPPException e) {
            e.printStackTrace();
        }
    }

    private void connect() throws XMPPException {
        if(connection != null && connection.isConnected()) {
            return;
        }
        ConnectionConfiguration config = new ConnectionConfiguration("localhost", 5222);
        connection = new XMPPConnection(config);
        connection.connect();
        SASLAuthentication.supportSASLMechanism("PLAIN", 0);
        connection.login("woodburry", "woodburry");

        chatRoom = new MultiUserChat(connection, CHAT_ROOM_NAME);
        chatRoom.join(userName);
        chatRoom.addMessageListener(this);
    }

    @Override
    public void processPacket(Packet packet) {
        Message message = (Message)packet;
        if(message.getType() == Message.Type.error) {
            chatMessages.add(new ChatMessage("error",
                    message.getError().toString()));
            synchronized (obj) {
                obj.notifyAll();
            }
        } else if(message.getType() == Message.Type.groupchat) {
            int i = packet.getFrom().indexOf('/');
            String user = packet.getFrom().substring(i + 1);
            chatMessages.add(new ChatMessage(user,
                    message.getBody()));
            synchronized (obj) {
                obj.notifyAll();
            }
        }
    }
}
