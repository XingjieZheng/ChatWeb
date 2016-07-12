package business.message.conversation;

import business.account.User;
import business.message.Message;
import business.message.list.MessageListBean;
import framework.BaseCookiesServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by xj
 * on 2016/7/12.
 */
public class ConversationServlet extends BaseCookiesServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String theOtherUserId = getParamValue(req, "theOtherUserId");
        if (getUserInfo(req) && theOtherUserId != null) {
            MessageListBean messageListBean = new MessageListBean();
            ArrayList<Message> messages = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                Message message = new Message(String.valueOf(i + 1));
                User user;
                if (i % 2 == 0) {
                    message.setContent("Hello, boy!");
                    user = new User(userId);
                    user.setAvatar("http://coffeephoto.yuanlai.com/private/u/4c72/15286c200af.jpg");
                } else {
                    message.setContent("Hello, girl!");
                    user = new User(theOtherUserId);
                    user.setAvatar("http://coffeephoto.yuanlai.com/private/u/3a92/151d7c2155a.jpg");
                }
                user.setUserName("user" + user.getUserId());
                message.setTime(System.currentTimeMillis());
                message.setUser(user);
                messages.add(message);
            }
            messageListBean.setMessageArrayList(messages);
            messageListBean.setStatusSuccess();
            messageListBean.setLastPage(true);
            messageListBean.setMsg("User(id:" + userId + ") gets conversation successfully!");
            printWrite(resp, messageListBean);
        }
    }
}
