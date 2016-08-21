package test.dao;

import dao.ContactsDao;
import entity.Contacts;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Created by xj
 * on 2016/8/9.
 */
public class ContactsDaoTest {

    private static ContactsDao contactsDao = new ContactsDao();

    @Before
    public void addContacts() {
        addContacts(4, 1);
        addContacts(6, 1);
        addContacts(4, 3);
        addContacts(1, 2);

    }

    private void addContacts(int userId, int contactsUserId) {
        Contacts contacts = new Contacts();
        contacts.setUserId(userId);
        contacts.setContactUserId(contactsUserId);
        contacts.setCreateTime(new Date(System.currentTimeMillis()));
        contactsDao.save(contacts);
    }


    @Test
    public void getContacts() {
        List<Integer> list = contactsDao.getContacts(1);
        for (int userId : list) {
            System.out.println(userId);
        }
    }

}
