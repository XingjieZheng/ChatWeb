package framework;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by xj
 * on 2016/6/27.
 */
public class BaseCookiesServlet extends BaseServlet {

    public static final String COOKIE_USER_ID = "cookie_user_id";
    public static final String COOKIE_TOKEN = "cookie_token";

    protected String userId;
    protected String token;

    protected boolean getUserInfo(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (COOKIE_USER_ID.equals(cookie.getName())) {
                    userId = cookie.getValue();
                    print(COOKIE_USER_ID + " " + userId);
                } else if (COOKIE_TOKEN.equals(cookie.getName())) {
                    token = cookie.getValue();
                    print(COOKIE_TOKEN + " " + token);
                }
            }
        }
        return !(userId == null || token == null);
        // TODO: 2016/7/1 verify user
    }
}
