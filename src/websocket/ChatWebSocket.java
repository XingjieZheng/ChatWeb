package websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by xj
 * on 2016/6/21.
 */
@ServerEndpoint("/websocket")
public class ChatWebSocket {

    private static int onlineCount = 0;

    private static CopyOnWriteArraySet<ChatWebSocket> chatWebSocketSet = new CopyOnWriteArraySet<>();
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        chatWebSocketSet.add(this);
        addOnlineCount();
        System.out.println("new connection, online count is " + getOnlineCount());
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
        System.out.println("来自客户端" + session.getRequestURI() + "的消息:" + message);

        for (ChatWebSocket item : chatWebSocketSet) {
            try {
                item.sendMessage(message);
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
