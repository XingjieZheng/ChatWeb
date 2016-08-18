package test.dao;

import dao.MessageDao;
import entity.Message;
import org.junit.Test;

import java.util.Date;

/**
 * Created by xj
 * on 2016/8/9.
 */
public class MessageDaoTest {

    private static MessageDao messageDao = new MessageDao();

    @Test
    public void addMessage() {
//        addMessage(1, 3, "hello 3");
//        addMessage(2, 3, "hi 6");
//        addMessage(5, 2, "hello 5");
//        addMessage(2, 4, "hi 4");
    }

    private Message addMessage(int senderId, int receiverId, String content) {
        Message message = new Message();
        message.setContent(content);
        message.setReceiverId(receiverId);
        message.setSenderId(senderId);
        message.setReadState(Message.STATE_UNREAD);
        message.setTime(new Date(System.currentTimeMillis()));
        messageDao.save(message);
        return message;
    }


    @Test
    public void getMessages() {
//        messageDao.getMessages(1);
    }

    @Test
    public void getMessagesWithTheOther() {
        messageDao.getMessages(1, 2);
    }
}
