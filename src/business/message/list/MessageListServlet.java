package business.message.list;

import business.message.MessageBean;
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
            ArrayList<MessageBean> messages = new ArrayList<>();
//            for (int i = 0; i < 3; i++) {
//                MessageBean message = new MessageBean(String.valueOf(i + 1));
//                message.setContent("Hello, boy!");
//                message.setTime(System.currentTimeMillis());
//                message.setUnReadCount(i % 2 + 1);
//                User user = new User();
//                user.setId(i + 700000);
//                user.setAvatar("http://coffeephoto.yuanlai.com/private/u/4c72/15286c200af.jpg");
//                user.setGender(i % 2);
//                user.setNickName("user" + user.getId());
//                message.setSender(user);
//                messages.add(message);
//            }
            messageListBean.setMessageArrayList(messages);
            messageListBean.setStatusSuccess();
            messageListBean.setLastPage(true);
            messageListBean.setMsg("User(id:" + userId + ") gets message successfully!");
            printWrite(resp, messageListBean);
        }
    }
}
