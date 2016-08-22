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

    @Test
    public void addContacts() {
        contactsDao.addFriend(1, 2);
    }

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
        for (int userId : list) {
            System.out.println(userId);
        }
    }


}
