package test.dao;

import dao.RequestBecomeContactsDao;
import org.junit.Test;

/**
 * Created by xj
 * on 2016/8/23.
 */
public class RequestBecomeContactsDaoTest {

    private static RequestBecomeContactsDao requestBecomeContactsDao = new RequestBecomeContactsDao();

    @Test
    public void requestFriendTest() {
        requestBecomeContactsDao.requestBecomeContacts(8, 1);
        requestBecomeContactsDao.requestBecomeContacts(1, 8);
        requestBecomeContactsDao.requestBecomeContacts(7, 1);
        requestBecomeContactsDao.requestBecomeContacts(5, 1);
    }

    @Test
    public void getRequestedBecomeContactsList() {
        requestBecomeContactsDao.getRequestedBecomeContactsList(1);
    }


}
