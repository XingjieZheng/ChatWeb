package dao;

import entity.Message;
import framework.BaseDao;

import java.util.List;

/**
 * Created by xj
 * on 2016/8/16.
 */
public class MessageDao extends BaseDao<Message> {

    public List<Message> getMessages(int senderId) {
        String hql = "from  Message " +
                "where sender_id=\'" + senderId + "\'";
        System.out.println(hql);
        List<Message> messageList = findByHQL(hql);
        if (messageList != null && messageList.size() > 0) {
            for (Message temp : messageList) {
                System.out.println(temp.toString());
            }
        } else {
            System.out.println("MessageDao getMessages(" + senderId + ") is empty!");
        }
        return messageList;
    }

    public List<Message> getMessages(int userId, int theOtherUserId) {
        String hql = "FROM Message " +
                "WHERE (sender_id = " + userId + "and receiver_id = " + theOtherUserId + ")"
                + "or(sender_id = " + theOtherUserId + "and receiver_id = " + userId + ")" +
                " ORDER BY create_time DESC ";
        System.out.println(hql);
        List<Message> messageList = findByHQL(hql);
        if (messageList != null && messageList.size() > 0) {
            for (Message temp : messageList) {
                System.out.println(temp.toString());
            }
        } else {
            System.out.println("MessageDao getMessages(" + userId + ", " + theOtherUserId + ") is empty!");
        }
        return messageList;
    }
}
