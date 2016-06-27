package business.account.login;

import framework.BaseBean;
import framework.BaseServlet;

import javax.servlet.ServletException;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

        String account = getParamValue(req, PARAM_ACCOUNT);
        String password = getParamValue(req, PARAM_PASSWORD);

        if ("13713709078".equals(account) && "123456".equals(password)) {
            AccountLoginBean accountLoginBean = new AccountLoginBean();
            accountLoginBean.setAccountName("13713709078");
            accountLoginBean.setRememberPassword(true);
            AccountLoginBean.Data data = new AccountLoginBean.Data();
            data.setAvatar("http://coffeephoto.yuanlai.com/private/u/36cd/154d8c110fa.jpg");
            data.setGender(2);
            data.setNickName("bingbing");
            data.setUserId(1);
            accountLoginBean.setData(data);
            accountLoginBean.setStatus(BaseBean.STATUS_SUCCESS);
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
