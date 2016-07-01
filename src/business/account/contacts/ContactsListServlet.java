package business.account.contacts;

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
public class ContactsListServlet extends BaseCookiesServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        if (getUserInfo(req)) {
            ContactsListBean contactsListBean = new ContactsListBean();
            ArrayList<User> users = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                User user = new User((i + 100) + "");
                user.setAvatar("http://coffeephoto.yuanlai.com/private/u/36cd/154d8c110fa.jpg");
                user.setGender(i % 2);
                user.setUserName("user" + user.getUserId());
                users.add(user);
            }
            contactsListBean.setUserArrayList(users);
            contactsListBean.setStatusSuccess();
            contactsListBean.setLastPage(true);
            contactsListBean.setMsg("User(id:" + userId + ") gets contacts successfully!");
            printWrite(resp, contactsListBean);
        }
    }
}
