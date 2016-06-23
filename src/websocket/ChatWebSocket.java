package websocket;

import com.google.gson.Gson;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import static websocket.GetHttpSessionConfigurator.REMOTE_ADDRESS_AND_PORT;

/**
 * Created by xj
 * on 2016/6/21.
 */
@ServerEndpoint(value = "/websocket", configurator = GetHttpSessionConfigurator.class)
public class ChatWebSocket {

    private static int onlineCount = 0;

    private static CopyOnWriteArraySet<ChatWebSocket> chatWebSocketSet = new CopyOnWriteArraySet<>();
    private Session session;
    private String host;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        chatWebSocketSet.add(this);
        addOnlineCount();
        host = (String) session.getUserProperties().get(REMOTE_ADDRESS_AND_PORT);
        System.out.println("new connection(" + host + "), online count is " + getOnlineCount());
    }

    @OnClose
    public void onClose() {
        chatWebSocketSet.remove(this);
        subOnlineCount();
        System.out.println("some one out, online count is " + getOnlineCount());
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("error: " + error.toString());
        error.printStackTrace();
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        Gson gson = new Gson();
        if (host == null) {
            host = (String) session.getUserProperties().get(REMOTE_ADDRESS_AND_PORT);
        }
        System.out.println("message from client" + host + ":" + message);

        for (ChatWebSocket item : chatWebSocketSet) {
            try {
                if (host != null && host.equals(item.session.getUserProperties().get(REMOTE_ADDRESS_AND_PORT))) {
                    System.out.println("host:" + item.session.getUserProperties().get(REMOTE_ADDRESS_AND_PORT));
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }

    }

    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    private static synchronized void addOnlineCount() {
        ChatWebSocket.onlineCount++;
    }

    private static synchronized void subOnlineCount() {
        ChatWebSocket.onlineCount--;
    }
}
