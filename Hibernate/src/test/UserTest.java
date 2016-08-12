package test;

import dao.UserDao;
import entity.User;
import util.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.metadata.ClassMetadata;

import java.util.Date;
import java.util.Map;

/**
 * Created by xj
 * on 2016/8/6.
 */
public class UserTest {

    private static SessionFactory sessionFactory;
    private static Session session;

    public static void main(String[] args) {
        sessionFactory = HibernateUtils.getSessionFactory();
        session = HibernateUtils.getSession();
        addUser();
//        selectUser();
//        findUser();
//        findUserByName("xiaoming");
        closeSession();
    }

    private static void findUserByName(String name) {
        UserDao userDao = new UserDao();
        userDao.getUser(name);
    }


    private static void findUser() {
        UserDao userDao = new UserDao();
        User user = userDao.findById(2);
        if (user != null) {
            System.out.println(user.toString());
        }
    }


    private static void selectUser() {
        System.out.println("select all user");
        final Map metadataMap = session.getSessionFactory().getAllClassMetadata();
        for (Object key : metadataMap.keySet()) {
            final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get(key);
            final String entityName = classMetadata.getEntityName();
            final Query query = session.createQuery("from " + entityName);
            System.out.println("executing: " + query.getQueryString());
            for (Object o : query.list()) {
                System.out.println("=======selectUser  " + o.toString());
            }
        }
    }

    private static void addUser() {
        //开启事务
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setAccount("13713709078");
//        user.setId(1);
        user.setNickName("xingjiezheng");
        user.setPassword("123456");
        user.setCreateTime(new Date(System.currentTimeMillis()));
        user.setGender(1);
        user.setAvatar("http://coffeephoto.yuanlai.com/private/u/4c72/15286c200af.jpg");

        User user2 = new User();
        user2.setAccount("13713709079");
//        user2.setId(2);
        user2.setNickName("xiaoming");
        user2.setPassword("123456");
        user2.setCreateTime(new Date(System.currentTimeMillis()));
        user2.setGender(2);
        user2.setAvatar("http://coffeephoto.yuanlai.com/private/u/3a92/151d7c2155a.jpg");
        session.save(user); //保存对象进入数据库
        session.save(user2);
        transaction.commit(); //提交事务
    }

    private static void closeSession() {
        session.close(); //关闭会话
        sessionFactory.close(); //关闭会话工厂
    }

}
