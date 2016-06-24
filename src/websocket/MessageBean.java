package websocket;


import system.Constants;

/**
 * Created by XingjieZheng
 * on 2016/6/17.
 */
public class MessageBean {

    private static final int TYPE_NORMAL_MESSAGE = 0;
    private static final int TYPE_REGISTER_MESSAGE = 1;
    public static final String FIELD_AND_VAULE_SECURITY_KEY = "\"securityKey\":\"" + Constants.WEB_SOCKET_SECURITY_KEY + "\",";

    private int senderUserId;
    private int receiverUserId;
    private String message;
    private int type;
    private String securityKey;

    public boolean isDataValid() {
        return Constants.WEB_SOCKET_SECURITY_KEY.equals(securityKey);
    }

    public boolean isNormalMessage() {
        return senderUserId != 0 && receiverUserId != 0 && message != null && type == TYPE_NORMAL_MESSAGE;
    }

    public boolean isRegisterMessage() {
        return senderUserId != 0 && type == TYPE_REGISTER_MESSAGE;
    }

    public int getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(int receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public int getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(int senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
