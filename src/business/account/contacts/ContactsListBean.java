package business.account.contacts;


import business.account.User;
import framework.BaseBean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 登录的初始信息
 */
public class ContactsListBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<User> userArrayList;

    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }
}
