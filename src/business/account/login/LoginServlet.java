package business.account.login;

import hibernate.dao.UserDao;
import hibernate.entity.User;
import framework.BaseBean;
import framework.BaseCookiesServlet;
import framework.BaseServlet;
import util.RandomUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xj
 * on 2016/6/27.
 */
public class LoginServlet extends BaseServlet {

    private static final String PARAM_ACCOUNT = "mobile";
    private static final String PARAM_PASSWORD = "password";

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

        String account = getParamValue(req, PARAM_ACCOUNT);
        String password = getParamValue(req, PARAM_PASSWORD);
        String message;

        if (account != null && password != null) {
            UserDao userDao = new UserDao();
            User user = userDao.getUser(account);
            if (user == null) {
                message = "Error, account can not be find!";
                responseError(resp, message);
            } else {
                if (!password.equals(user.getPassword())) {
                    message = "Error, password is error!";
                    responseError(resp, message);
                } else {
                    user.setPassword("");
                    user.setCreateTime(0);
                    String userId = String.valueOf(user.getId());
                    String token = RandomUtils.getRandomString(32);
                    Cookie cookieUserId = new Cookie(BaseCookiesServlet.COOKIE_USER_ID, userId);
                    Cookie cookieToken = new Cookie(BaseCookiesServlet.COOKIE_TOKEN, token);
                    resp.addCookie(cookieUserId);
                    resp.addCookie(cookieToken);

                    AccountLoginBean accountLoginBean = new AccountLoginBean();
                    accountLoginBean.setUser(user);
                    accountLoginBean.setAccount(account);
                    accountLoginBean.setStatus(BaseBean.STATUS_SUCCESS);
                    accountLoginBean.setRememberPassword(true);
                    accountLoginBean.setMsg("Login successfully!");
                    printWrite(resp, accountLoginBean);
                }
            }
        } else {
            message = "Error, account and password can not be null!";
            responseError(resp, message);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }


    private void responseError(HttpServletResponse resp, String message) {
        BaseBean baseBean = new BaseBean();
        baseBean.setMsg(message);
        baseBean.setStatus(BaseBean.STATUS_FAILED);
        printWrite(resp, baseBean);
    }

}
