package dao;

import entity.Contacts;
import entity.ContactsTableDataVersion;
import framework.BaseDao;
import system.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xj
 * on 2016/8/8.
 */
public class ContactsDao extends BaseDao<Contacts> {


    public List<Integer> getContacts(int userId) {
        List<Integer> list = null;
        String hql = "from  Contacts" +
                " where user_id=" + userId +
                " and state=" + Contacts.STATE_IS_FRIEND;
        System.out.println(hql);
        List<Contacts> contacts = findByHQL(hql);
        if (contacts != null && contacts.size() > 0) {
            list = new ArrayList<>();
            for (Contacts temp : contacts) {
                if (userId == temp.getUserId() && !list.contains(temp.getContactUserId())) {
                    list.add(temp.getContactUserId());
                } else if (userId == temp.getContactUserId() && !list.contains(temp.getUserId())) {
                    list.add(temp.getUserId());
                }
                System.out.println(temp.toString());
            }
            System.out.println();
        } else {
            System.out.println("ContactsDao getContacts(" + userId + ") is empty!");
        }
        return list;
    }

    @Override
    public void save(Contacts entity) {
        int version = modifyVersion(entity);
        entity.setVersion(version);
        entity.setState(Contacts.STATE_IS_FRIEND);
        entity.setCreateTime(new Date(System.currentTimeMillis()));
        super.save(entity);
    }

    @Override
    public void update(Contacts entity) {
        int version = modifyVersion(entity);
        entity.setVersion(version);
        super.update(entity);
    }

    @Override
    public void delete(Serializable id) {
//        super.delete(id);
    }

    private int modifyVersion(Contacts entity) {
        int version;
        ContactsTableDataVersionDao tableDataVersionDao = new ContactsTableDataVersionDao();
        ContactsTableDataVersion contactsTableDataVersion = tableDataVersionDao.findById(entity.getUserId());
        if (contactsTableDataVersion == null) {
            contactsTableDataVersion = new ContactsTableDataVersion();
            contactsTableDataVersion.setUserId(entity.getUserId());
            version = Constants.TABLE_DATA_VERSION_START;
            contactsTableDataVersion.setVersion(version);
            tableDataVersionDao.save(contactsTableDataVersion);
        } else {
            version = contactsTableDataVersion.getVersion() + 1;
            contactsTableDataVersion.setVersion(version);
            tableDataVersionDao.update(contactsTableDataVersion);
        }
        return version;
    }

    public void pullBlack(int userId, int contactUserId) {
        Contacts contacts = findContacts(userId, contactUserId, Contacts.STATE_IS_FRIEND);
        if (contacts != null) {
            contacts.setState(Contacts.STATE_IS_BLACK);
            update(contacts);
            System.out.println("ContactsDao putBlack(" + userId + "," + contactUserId + ") is successful!");
        } else {
            System.out.println("ContactsDao putBlack(" + userId + "," + contactUserId + ") is empty!");
        }
    }

    public void removeBlack(int userId, int contactUserId) {
        Contacts contacts = findContacts(userId, contactUserId, Contacts.STATE_IS_BLACK);
        if (contacts != null) {
            contacts.setState(Contacts.STATE_IS_FRIEND);
            update(contacts);
            System.out.println("ContactsDao removeBlack(" + userId + "," + contactUserId + ") is successful!");
        } else {
            System.out.println("ContactsDao removeBlack(" + userId + "," + contactUserId + ") is empty!");
        }
    }

    public void addBothContacts(int userId, int contactUserId) {
        addContacts(userId, contactUserId);
        addContacts(contactUserId, userId);
    }

    private void addContacts(int userId, int contactUserId) {
        String hql = "from  Contacts" +
                " where user_id=" + userId + " and contact_user_id=" + contactUserId;
        System.out.println(hql);
        List<Contacts> contactsList = findByHQL(hql);
        Contacts contacts;
        if (contactsList != null && contactsList.size() > 0) {
            contacts = contactsList.get(0);
            if (contacts.getState() != Contacts.STATE_IS_FRIEND) {
                contacts.setState(Contacts.STATE_IS_FRIEND);
                update(contacts);
            }
        } else {
            contacts = new Contacts();
            contacts.setUserId(userId);
            contacts.setContactUserId(contactUserId);
            contacts.setState(contacts.STATE_IS_FRIEND);
            save(contacts);
        }
    }

    private Contacts findContacts(int userId, int contactUserId, int state) {
        String hql = "from  Contacts" +
                " where user_id=" + userId + " and contact_user_id=" + contactUserId +
                " and state=" + state;
        System.out.println(hql);
        List<Contacts> contactsList = findByHQL(hql);
        Contacts contacts = null;
        if (contactsList != null && contactsList.size() > 0) {
            contacts = contactsList.get(0);
        }
        return contacts;
    }

    public void requestBecomeContacts() {
        
    }
}
