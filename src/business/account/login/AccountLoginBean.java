package business.account.login;


import entity.User;
import framework.BaseBean;

import java.io.Serializable;

/**
 * 登录的初始信息
 */
public class AccountLoginBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户
     */
    private String account;
    /**
     * 登录信息-本地账户密码
     */
    private String accountPwd;
    /**
     * 登录信息-是否记录密码
     */
    private boolean isRememberPassword;
    private User user;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountPwd() {
        return accountPwd;
    }

    public void setAccountPwd(String accountPwd) {
        this.accountPwd = accountPwd;
    }

    public boolean isRememberPassword() {
        return isRememberPassword;
    }

    public void setRememberPassword(boolean rememberPassword) {
        isRememberPassword = rememberPassword;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
