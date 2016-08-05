package websocket;


import system.Constants;

/**
 * Created by XingjieZheng
 * on 2016/6/17.
 */
public class MessageBean {

    private static final int TYPE_NORMAL_MESSAGE = 0;
    private static final int TYPE_REGISTER_MESSAGE = 1;
    public static final String FIELD_AND_VALUE_IN_SECURITY_KEY = "\"securityKey\":\"" + Constants.WEB_SOCKET_SECURITY_KEY + "\",";

    private String senderUserId;
    private String receiverUserId;
    private String message;
    private int type;
    private String securityKey;
    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isDataValid() {
        return Constants.WEB_SOCKET_SECURITY_KEY.equals(securityKey);
    }

    public boolean isNormalMessage() {
        return senderUserId != null && receiverUserId != null && message != null && type == TYPE_NORMAL_MESSAGE;
    }

    public boolean isRegisterMessage() {
        return senderUserId != null && type == TYPE_REGISTER_MESSAGE;
    }

    public String getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(String receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public String getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
