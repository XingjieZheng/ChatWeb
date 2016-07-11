package business.message;

import business.account.User;
import framework.BaseCookiesServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by xj
 * on 2016/7/1.
 */
public class MessageListServlet extends BaseCookiesServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        if (getUserInfo(req)) {
            MessageListBean messageListBean = new MessageListBean();
            ArrayList<Message> messages = new ArrayList<>();
            for (int i = 0; i < 15; i++) {
                Message message = new Message(String.valueOf(i + 1));
                message.setContent("Hello, boy!");
                message.setTime(System.currentTimeMillis());
                message.setUnReadCount(i % 2 + 1);
                User user = new User(String.valueOf(i + 11));
                user.setAvatar("http://coffeephoto.yuanlai.com/private/u/4c72/15286c200af.jpg");
                user.setGender(i % 2);
                user.setUserName("user" + user.getUserId());
                message.setUser(user);
                messages.add(message);
            }
            messageListBean.setMessageArrayList(messages);
            messageListBean.setStatusSuccess();
            messageListBean.setLastPage(true);
            messageListBean.setMsg("User(id:" + userId + ") gets message successfully!");
            printWrite(resp, messageListBean);
        }
    }
}
