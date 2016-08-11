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


    public List<Integer> getContacts(String userId) {
        int uId = Integer.valueOf(userId);
        List<Integer> list = null;
        String hql = "from  Contacts " +
                "where user_id=\'" + uId + "\' or contact_user_id=\'" + uId + "\'";
        System.out.println(hql);
        List<Contacts> contacts = findByHQL(hql);
        if (contacts != null && contacts.size() > 0) {
            list = new ArrayList<>();
            for (Contacts temp : contacts) {
                if (uId == temp.getUserId() && !list.contains(temp.getContactUserId())) {
                    list.add(temp.getContactUserId());
                } else if (uId == temp.getContactUserId() && !list.contains(temp.getUserId())) {
                    list.add(temp.getUserId());
                }
                System.out.print(temp.toString());
            }
            System.out.println();
        } else {
            System.out.println("ContactsDao getContacts(" + uId + ") is empty!");
        }
        return list;
    }

}
