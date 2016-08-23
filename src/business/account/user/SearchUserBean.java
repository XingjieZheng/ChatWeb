package business.account.user;


import entity.SampleUser;
import framework.BaseBean;

import java.io.Serializable;

/**
 * 登录的初始信息
 */
public class SearchUserBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    public void setUser(SampleUser user) {
        this.user = user;
    }

    private SampleUser user;

}
