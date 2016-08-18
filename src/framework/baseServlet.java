package framework;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Created by xj
 * on 2016/6/27.
 */
public class BaseServlet extends HttpServlet {

    private static final String TAG = BaseServlet.class.getSimpleName();
    private Gson gson;
    private static final String CHARACTER_ENCODING = "utf-8";

    @Override
    public void init() throws ServletException {
        super.init();
        gson = new Gson();

    }

    protected void printWrite(HttpServletResponse response, BaseBean baseBean) {
        try {
            response.setContentType("text/html");
            PrintWriter pw;
            pw = response.getWriter();
            String data = gson.toJson(baseBean);
            pw.write(data);
            System.out.println("BaseServlet printWrite():" + data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(CHARACTER_ENCODING);
        resp.setCharacterEncoding(CHARACTER_ENCODING);
        printParams(req);
    }

    private void printParams(HttpServletRequest req) {
        System.out.print("printParams() : ");
        Enumeration<String> enumeration = req.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String paramName = enumeration.nextElement();
            String[] paramValues = req.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    System.out.print("param:" + paramName + " value:" + paramValue + "  ");
                }
            }
        }
        System.out.println();
    }

    protected String getParamValue(HttpServletRequest req, String param) {
        String value;
        String[] paramValues = req.getParameterValues(param);
        if (paramValues != null && paramValues.length == 1) {
            value = paramValues[0];
            if (value.length() != 0) {
                System.out.println(param + ":" + value);
                return value;
            }
        }
        return null;
    }

    protected void print(String msg) {
        System.out.println(TAG + " " + msg);
    }

    protected void responseError(HttpServletResponse resp, String message) {
        BaseBean baseBean = new BaseBean();
        baseBean.setMsg(message);
        baseBean.setStatus(BaseBean.STATUS_FAILED);
        printWrite(resp, baseBean);
        print(message);
    }
}
