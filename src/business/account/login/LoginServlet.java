package business.account.login;

import business.account.User;
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

        if (account != null && account.length() == 11 && "123456".equals(password)) {
            String userId = account.substring(5, 11);
            String token = RandomUtils.getRandomString(32);
            Cookie cookieUserId = new Cookie(BaseCookiesServlet.COOKIE_USER_ID, userId);
            Cookie cookieToken = new Cookie(BaseCookiesServlet.COOKIE_TOKEN, token);
            resp.addCookie(cookieUserId);
            resp.addCookie(cookieToken);

            AccountLoginBean accountLoginBean = new AccountLoginBean();
            User user = new User(userId);
            user.setAvatar("http://coffeephoto.yuanlai.com/private/u/4c72/15286c200af.jpg");
            user.setGender(2);
            user.setUserName("bing");
            accountLoginBean.setUser(user);
            accountLoginBean.setAccount(account);
            accountLoginBean.setStatus(BaseBean.STATUS_SUCCESS);
            accountLoginBean.setRememberPassword(true);
            accountLoginBean.setMsg("Login successfully!");
            printWrite(resp, accountLoginBean);
        } else {
            BaseBean baseBean = new BaseBean();
            baseBean.setMsg("Error, account or password is error!");
            baseBean.setStatus(BaseBean.STATUS_FAILED);
            printWrite(resp, baseBean);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
