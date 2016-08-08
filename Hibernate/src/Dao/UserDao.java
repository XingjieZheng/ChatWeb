package dao;

import entity.User;
import framework.BaseDao;

import java.util.List;

/**
 * Created by xj
 * on 2016/8/8.
 */
public class UserDao extends BaseDao<User> {


    public User getUser(String account) {
        User user = null;
        List<User> users = findByHQL("from User where account=\'" + account + "\'");
        if (users != null && users.size() > 0) {
            user = users.get(0);
            System.out.println(user.toString());
        } else {
            System.out.println("UserDao getUser(" + account + ") is empty!");
        }
        return user;
    }

}
