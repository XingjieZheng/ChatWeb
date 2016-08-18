package business.message.conversation;

import business.message.MessageBean;
import business.message.list.MessageListBean;
import dao.MessageDao;
import dao.UserDao;
import entity.Message;
import entity.SampleUser;
import framework.BaseCookiesServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xj
 * on 2016/7/12.
 */
public class ConversationServlet extends BaseCookiesServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String theOtherUserIdS = getParamValue(req, "theOtherUserId");
        int theOtherUserId;
        try {
            theOtherUserId = Integer.valueOf(theOtherUserIdS);
        } catch (Exception e) {
            e.printStackTrace();
            responseError(resp, "Error, theOtherUserId(" + theOtherUserIdS + ")");
            return;
        }
        if (getUserInfo(req)) {
            UserDao userDao = new UserDao();
            SampleUser user = userDao.findSampleUserById(userId);
            if (user == null) {
                responseError(resp, "Error, userId(" + userId + ") can not be find!");
                return;
            }
            SampleUser theOtherUser = userDao.findSampleUserById(theOtherUserId);
            if (theOtherUser == null) {
                responseError(resp, "Error, theOtherUserId(" + theOtherUserId + ") can not be find!");
                return;
            }

            MessageListBean messageListBean = new MessageListBean();
            ArrayList<MessageBean> messageBeanArrayList = new ArrayList<>();
            MessageDao messageDao = new MessageDao();
            List<Message> messageList = messageDao.getMessages(userId, theOtherUserId);
            for (Message temp : messageList) {
                MessageBean messageBean = new MessageBean(temp);
                if (temp.getSenderId() == user.getId()) {
                    messageBean.setReceiver(theOtherUser);
                    messageBean.setSender(user);
                } else {
                    messageBean.setReceiver(user);
                    messageBean.setSender(theOtherUser);
                }
                messageBeanArrayList.add(messageBean);
            }

            messageListBean.setMessageArrayList(messageBeanArrayList);
            messageListBean.setStatusSuccess();
            messageListBean.setLastPage(true);
            messageListBean.setMsg("User(id:" + userId + ") gets conversation successfully!");
            printWrite(resp, messageListBean);
        } else {
            responseError(resp, "Error, userId(" + userId + ")");
        }
    }
}
