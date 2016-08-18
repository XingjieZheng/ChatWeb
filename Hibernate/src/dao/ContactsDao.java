package dao;

import entity.Contacts;
import framework.BaseDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xj
 * on 2016/8/8.
 */
public class ContactsDao extends BaseDao<Contacts> {


    public List<Integer> getContacts(int userId) {
        List<Integer> list = null;
        String hql = "from  Contacts " +
                "where user_id=\'" + userId + "\' or contact_user_id=\'" + userId + "\'";
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
                System.out.print(temp.toString());
            }
            System.out.println();
        } else {
            System.out.println("ContactsDao getContacts(" + userId + ") is empty!");
        }
        return list;
    }

}
