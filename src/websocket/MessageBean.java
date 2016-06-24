package websocket;

/**
 * Created by XingjieZheng
 * on 2016/6/17.
 */
public class MessageBean {

    private static final int TYPE_NORMAL_MESSAGE = 0;
    private static final int TYPE_REGISTER_MESSAGE = 1;

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

    private int senderUserId;
    private int receiverUserId;
    private String message;
    private int type;

    public boolean isRegisterMessage() {
        return type == TYPE_REGISTER_MESSAGE;
    }

    public boolean isDataValid() {
        return senderUserId != 0 && ((receiverUserId != 0 && message != null) || type == TYPE_REGISTER_MESSAGE);
    }

}
