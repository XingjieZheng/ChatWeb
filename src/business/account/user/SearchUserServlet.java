package business.account.user;

import dao.UserDao;
import entity.SampleUser;
import framework.BaseCookiesServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xj
 * on 2016/7/1.
 */
public class SearchUserServlet extends BaseCookiesServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String userIdS = getParamValue(req, "userId");
        int userId;
        try {
            userId = Integer.valueOf(userIdS);
        } catch (Exception e) {
            e.printStackTrace();
            responseError(resp, "Error, user(id:" + userIdS + ") can not be find!");
            return;
        }
        if (getUserInfo(req)) {
            UserDao userDao = new UserDao();
            SampleUser sampleUser = userDao.findSampleUserById(userId);
            if (sampleUser == null) {
                responseError(resp, "Error, user(id:" + userIdS + ") can not be find!");
                return;
            }

            SearchUserBean searchUserBean = new SearchUserBean();
            searchUserBean.setStatusSuccess();
            searchUserBean.setUser(sampleUser);
            searchUserBean.setMsg("Search user(id:" + userIdS + ") successfully!");
            printWrite(resp, searchUserBean);
        }
    }
}
