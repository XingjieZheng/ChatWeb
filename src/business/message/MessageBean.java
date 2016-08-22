package business.message;


import entity.Message;
import entity.SampleUser;

import java.io.Serializable;

/**
 * Created by XingjieZheng
 * on 2016/7/11.
 */
public class MessageBean extends Message implements Serializable {

    private int unReadCount;
    private SampleUser sender;
    private SampleUser receiver;

    public SampleUser getReceiver() {
        return receiver;
    }

    public void setReceiver(SampleUser receiver) {
        this.receiver = receiver;
    }

    public SampleUser getSender() {
        return sender;
    }

    public void setSender(SampleUser sender) {
        this.sender = sender;
    }

    public int getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(int unReadCount) {
        this.unReadCount = unReadCount;
    }

    public MessageBean(Message message) {
        setContent(message.getContent());
        setSenderId(message.getSenderId());
        setReceiverId(message.getReceiverId());
        setReadState(message.getReadState());
        setTime(message.getTime());
        setId(message.getId());
    }
}
