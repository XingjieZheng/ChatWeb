package websocket;

import com.google.gson.Gson;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by xj
 * on 2016/6/21.
 */
@ServerEndpoint(value = "/websocket", configurator = GetHttpSessionConfigurator.class)
public class ChatWebSocket {

    private static int onlineCount = 0;

    private static HashMap<Integer, ChatWebSocket> userWebSocketMap = new HashMap<>();

    private Session session;
    private int userId;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        addOnlineCount();
        System.out.println("new connection, online count is " + getOnlineCount());
    }

    @OnClose
    public void onClose() {
        userWebSocketMap.remove(userId);
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
        System.out.println("message from client" + message);

        try {
            Gson gson = new Gson();
            MessageBean messageBean = gson.fromJson(message, MessageBean.class);
            if (messageBean != null && messageBean.isDataValid()) {
                userId = messageBean.getSenderUserId();
                if (messageBean.isRegisterMessage()) {
                    if (userWebSocketMap.containsKey(messageBean.getSenderUserId())) {
                        userWebSocketMap.replace(messageBean.getSenderUserId(), this);
                    } else {
                        userWebSocketMap.put(messageBean.getSenderUserId(), this);
                    }
                    System.out.println("base message come from user(" + userId + ")");
                } else {
                    ChatWebSocket receiverWebSocket = userWebSocketMap.get(messageBean.getReceiverUserId());
                    if (receiverWebSocket != null) {
                        receiverWebSocket.sendMessage(message);
                        System.out.println("User(" + userId + ") send " + message + " to user(" + messageBean.getReceiverUserId() + ") successfully.");
                    } else {
                        System.out.println("error: receiver (userId:" + messageBean.getReceiverUserId() + ") does not connect to the server!");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("error message!");
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
