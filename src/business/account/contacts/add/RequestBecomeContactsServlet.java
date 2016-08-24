package business.account.contacts.add;

import dao.RequestBecomeContactsDao;
import framework.BaseCookiesServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xj
 * on 2016/7/1.
 */
public class RequestBecomeContactsServlet extends BaseCookiesServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String requestedUserIdS = getParamValue(req, "requestedUserId");
        int requestedUserId;
        try {
            requestedUserId = Integer.valueOf(requestedUserIdS);
        } catch (Exception e) {
            e.printStackTrace();
            responseError(resp, "Error, requestedUserId(" + requestedUserIdS + ") exception");
            return;
        }
        if (getUserInfo(req)) {
            if (userId == requestedUserId) {
                responseError(resp, "Error, requestedUserId(" + requestedUserIdS + ") exception." +
                        " You can not add yourself into contacts.");
                return;
            }
            RequestBecomeContactsDao requestBecomeContactsDao = new RequestBecomeContactsDao();
            requestBecomeContactsDao.requestBecomeContacts(userId, requestedUserId);
            responseSuccessfully(resp, "Request user(" + userId + ") become contacts successfully!");
        }
    }
}
