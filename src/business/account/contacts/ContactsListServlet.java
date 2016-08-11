package business.account.contacts;

import dao.ContactsDao;
import dao.UserDao;
import entity.User;
import framework.BaseCookiesServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by xj
 * on 2016/7/1.
 */
public class ContactsListServlet extends BaseCookiesServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        if (getUserInfo(req)) {
            ContactsDao contactsDao = new ContactsDao();
            UserDao userDao = new UserDao();
            List<Integer> contactsList = contactsDao.getContacts(userId);

            ContactsListBean contactsListBean = new ContactsListBean();
            List<User> users = userDao.getUsers(contactsList);
            contactsListBean.setUserArrayList(users);
            contactsListBean.setStatusSuccess();
            contactsListBean.setLastPage(true);
            contactsListBean.setMsg("User(id:" + userId + ") gets contacts successfully!");
            printWrite(resp, contactsListBean);
        }
    }
}
