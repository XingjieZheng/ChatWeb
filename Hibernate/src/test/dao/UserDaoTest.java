package test.dao;

import dao.UserDao;
import org.junit.Test;

/**
 * Created by xj
 * on 2016/8/9.
 */
public class UserDaoTest {

    private static UserDao userDao = new UserDao();

    @Test
    public void addUser() {
//        for (int i = 0; i < 10; i++) {
//            User user = new User();
//            user.setAccount(String.valueOf(13713709070L + i));
//            user.setNickName("xingjiezheng" + i);
//            user.setPassword("123456");
//            user.setCreateTime(new Date(System.currentTimeMillis()));
//            user.setGender((i % 2) + 1);
//            user.setAvatar("http://coffeephoto.yuanlai.com/private/u/4c72/15286c200af.jpg");
//            userDao.save(user);
//        }
    }


//    @Test
//    public void getUser() {
//        userDao.getUser("13713709070");
//    }
//
//    @Test
//    public void getUsers() {
//        List<Integer> userIds = new ArrayList<>();
//        userIds.add(2);
//        userIds.add(3);
//        userIds.add(5);
//        userDao.getUsers(userIds);
//    }
//
//    @Test
//    public void upDateLoginState() {
//        upDateLoginState(1, User.LOGIN_STATE_ONLINE);
//        upDateLoginState(2, User.LOGIN_STATE_ONLINE);
//        upDateLoginState(3, User.LOGIN_STATE_OUTLINE);
//    }
//
//    private void upDateLoginState(int userId, int state) {
//        User user = userDao.findById(userId);
//        user.setLoginTime(new Date(System.currentTimeMillis()));
//        user.setLoginState(state);
//        userDao.update(user);
//    }

    @Test
    public void getUserSimpleInfo() {
        userDao.findSampleUserById(1);
    }

}
