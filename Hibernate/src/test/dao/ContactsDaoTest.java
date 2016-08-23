package test.dao;

import dao.ContactsDao;
import org.junit.Test;

import java.util.List;

/**
 * Created by xj
 * on 2016/8/9.
 */
public class ContactsDaoTest {

    private static ContactsDao contactsDao = new ContactsDao();

//    @Test
//    public void addBothContacts() {
//        contactsDao.addBothContacts(1, 2);
//        contactsDao.addBothContacts(3, 1);
//        contactsDao.addBothContacts(1, 4);
//        contactsDao.addBothContacts(2, 4);
//    }

    @Test
    public void pullBack() {
        contactsDao.pullBlack(1, 3);
    }

    @Test
    public void removeBack() {
        contactsDao.removeBlack(1, 3);
    }

    @Test
    public void getContacts() {
        List<Integer> list = contactsDao.getContacts(1);
        if (list != null) {
            for (int userId : list) {
                System.out.println(userId);
            }
        }
    }


}
