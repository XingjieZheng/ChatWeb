package websocket;

import com.google.gson.Gson;
import dao.UserDao;
import entity.SampleUser;
import entity.User;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import static websocket.CommunicationMessageBean.FIELD_AND_VALUE_IN_SECURITY_KEY;

/**
 * Created by xj
 * on 2016/6/21.
 */
@ServerEndpoint(value = "/websocket", configurator = GetHttpSessionConfigurator.class)
public class ChatWebSocket {

    private static final String TAG = ChatWebSocket.class.getSimpleName();

    private static int onlineCount = 0;

    private static HashMap<Integer, ChatWebSocket> userWebSocketMap = new HashMap<>();

    private Session session;
    private int userId;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        addOnlineCount();
        print("new connection, online count is " + getOnlineCount());
    }

    @OnClose
    public void onClose() {
        userWebSocketMap.remove(userId);
        subOnlineCount();
        UserDao userDao = new UserDao();
        userDao.updateUserLoginState(userId, User.LOGIN_STATE_OUTLINE);
        print("User(" + userId + ") login out, online count is " + getOnlineCount());
    }

    @OnError
    public void onError(Session session, Throwable error) {
        print("error: " + error.toString());
        error.printStackTrace();
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        print("message from client" + message);

        try {
            Gson gson = new Gson();
            CommunicationMessageBean messageBean = gson.fromJson(message, CommunicationMessageBean.class);
            if (messageBean != null && messageBean.isDataValid()) {
                userId = messageBean.getMessage().getSender().getId();
                if (messageBean.isRegisterMessage()) {
                    UserDao userDao = new UserDao();
                    if (!userDao.updateUserLoginState(userId, User.LOGIN_STATE_ONLINE)) {
                        printAndSendMessage("User(" + userId + ") can not be find!");
                        return;
                    }
                    if (userWebSocketMap.containsKey(userId)) {
                        userWebSocketMap.replace(userId, this);
                    } else {
                        userWebSocketMap.put(userId, this);
                    }
                    sendMessage("User(" + userId + ") connects successfully!");

                } else if (messageBean.isNormalMessage()) {
                    int receiverUserId = messageBean.getMessage().getReceiver().getId();
                    int senderUserId =  messageBean.getMessage().getSender().getId();
                    messageBean.getMessage().setTime(new Date(System.currentTimeMillis()));
                    UserDao userDao = new UserDao();
                    SampleUser senderUser = userDao.findSampleUserById(senderUserId);
                    if (senderUser == null) {
                        printAndSendMessage("Sender user(" + receiverUserId + ") can not be find!");
                        return;
                    }
                    SampleUser receiverUser = userDao.findSampleUserById(receiverUserId);
                    if (receiverUser == null) {
                        printAndSendMessage("Receiver user(" + receiverUserId + ") can not be find!");
                        return;
                    }
                    ChatWebSocket receiverWebSocket = userWebSocketMap.get(receiverUserId);
                    if (receiverWebSocket != null) {
                        messageBean.getMessage().setSender(senderUser);
                        String sendMessageJson = gson.toJson(messageBean);
                        if (sendMessageJson.contains(FIELD_AND_VALUE_IN_SECURITY_KEY)) {
                            sendMessageJson = sendMessageJson.replace(FIELD_AND_VALUE_IN_SECURITY_KEY, "");
                        }
                        receiverWebSocket.sendMessage(sendMessageJson);
                        print("User(" + userId + ") send " + message + " to user(" + receiverUserId + ") successfully.");
                    } else {
                        printAndSendMessage("receiver (userId:" + receiverUserId + ") does not connect to the server!");
                    }
                } else {
                    printAndSendErrorMessage();
                }
            } else {
                printAndSendErrorMessage();
            }
        } catch (Exception e) {
            e.printStackTrace();
            printAndSendErrorMessage();
        }
    }

    private void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void printAndSendErrorMessage() {
        printAndSendMessage("Error message!");
    }

    private void printAndSendMessage(String message) {
        print(message);
        sendMessage(message);
    }

    private void print(String message) {
        System.out.println(TAG + "  " + message);
    }
}
