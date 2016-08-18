package websocket;


import business.message.MessageBean;
import system.Constants;

/**
 * Created by XingjieZheng
 * on 2016/6/17.
 */
public class CommunicationMessageBean {

    private static final int TYPE_NORMAL_MESSAGE = 0;
    private static final int TYPE_REGISTER_MESSAGE = 1;
    public static final String FIELD_AND_VALUE_IN_SECURITY_KEY
            = "\"securityKey\":\"" + Constants.WEB_SOCKET_SECURITY_KEY + "\",";

    private MessageBean message;
    private String securityKey;
    private int type;

    public boolean isDataValid() {
        return Constants.WEB_SOCKET_SECURITY_KEY.equals(securityKey) && message != null;
    }

    public boolean isNormalMessage() {
        return message != null && message.getSender() != null && message.getReceiver() != null
                && message.getSender().getId() > 0 && message.getReceiver().getId() > 0
                && type == TYPE_NORMAL_MESSAGE;
    }

    public boolean isRegisterMessage() {
        return message != null && message.getSender() != null
                && message.getSender().getId() > 0
                && type == TYPE_REGISTER_MESSAGE;
    }


    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public String getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
