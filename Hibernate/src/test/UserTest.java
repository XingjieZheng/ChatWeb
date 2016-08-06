package test;

import entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.Map;

/**
 * Created by xj
 * on 2016/8/6.
 */
public class UserTest {

    private static SessionFactory sessionFactory;
    private static Session session;

    static {
        try {
            //创建配置对象，获取hibernate.cfg.xml配置文件的信息
            Configuration config = new Configuration().configure();
            //创建服务注册对象，创建和销毁都相当耗费资源，通常一个系统内一个数据库只创建一个
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
            //创建会话工厂对象，类似于JDBC的Connection
            sessionFactory = config.buildSessionFactory(serviceRegistry);
        } catch (Throwable throwable) {
            throw new ExceptionInInitializerError(throwable);
        }

    }


    public static void main(String[] args) {

        //会话对象
        session = sessionFactory.openSession();
        addUser();
        selectUser();
        closeSession();
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
        user.setAccount("13713709077");
        user.setId(1);
        user.setNickName("xingjiezheng3");
        user.setPassword("123456");
        user.setCreateTime(System.currentTimeMillis());
        user.setGender(1);
        session.save(user); //保存对象进入数据库
        transaction.commit(); //提交事务
    }

    private static void closeSession() {
        session.close(); //关闭会话
        sessionFactory.close(); //关闭会话工厂
    }

}
