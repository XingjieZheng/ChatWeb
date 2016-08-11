package business.account.contacts;


import entity.User;
import framework.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * 登录的初始信息
 */
public class ContactsListBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<User> userArrayList;

    public void setUserArrayList(List<User> userArrayList) {
        this.userArrayList = userArrayList;
    }
}
