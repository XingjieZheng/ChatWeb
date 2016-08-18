package business.message.list;


import business.message.MessageBean;
import framework.BaseBean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by XingjieZheng
 * on 2016/7/11.
 */
public class MessageListBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<MessageBean> messageArrayList;

    public ArrayList<MessageBean> getMessageArrayList() {
        return messageArrayList;
    }

    public void setMessageArrayList(ArrayList<MessageBean> messageArrayList) {
        this.messageArrayList = messageArrayList;
    }
}
