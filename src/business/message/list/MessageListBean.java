package business.message.list;


import business.message.Message;
import framework.BaseBean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by XingjieZheng
 * on 2016/7/11.
 */
public class MessageListBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Message> messageArrayList;

    public ArrayList<Message> getMessageArrayList() {
        return messageArrayList;
    }

    public void setMessageArrayList(ArrayList<Message> messageArrayList) {
        this.messageArrayList = messageArrayList;
    }
}