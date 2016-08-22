package dao;

import entity.SampleUser;
import entity.User;
import framework.BaseDao;

import java.io.Serializable;
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

    public List<User> getUsers(List<Integer> usersIdList) {
        if (usersIdList == null || usersIdList.size() <= 0) {
            return null;
        }
        String userIdListString = "";
        for (int id : usersIdList) {
            userIdListString += (id + ",");
        }
        userIdListString = userIdListString.substring(0, userIdListString.length() - 1);
        String hql = "from User where id in (" + userIdListString + ")";
        System.out.println(hql);
        List<User> users = findByHQL(hql);
        if (users != null && users.size() > 0) {
            for (User user : users) {
                System.out.println(user.toString());
            }
        } else {
            System.out.println("UserDao getUsers(usersIdList) is empty!");
        }
        return users;
    }

    public SampleUser findSampleUserById(Serializable id) {
        User user = findById(id);
        if (user != null) {
            System.out.println(user.toString());
            return new SampleUser(user);
        } else {
            System.out.println("UserDao findSampleUserById(" + id + ") is empty!");
            return null;
        }
    }

    public boolean updateUserLoginState(int id, int state) {
        User user = findById(id);
        if (user == null) {
            System.out.println("UserDao updateUserLoginState(" + id + ", " + state + ") is fail !");
            return false;
        } else {
            user.setLoginState(state);
            save(user);
            return true;
        }
    }

}
