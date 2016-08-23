package dao;

import entity.RequestBecomeContacts;
import framework.BaseDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xj
 * on 2016/8/23.
 */
public class RequestBecomeContactsDao extends BaseDao<RequestBecomeContacts> {

    public void requestBecomeContacts(int userId, int requestedUserId) {
        String hql = "from  RequestFriend" +
                " where user_id=" + userId + " and requested_user_id=" + requestedUserId;
        System.out.println(hql);
        List<RequestBecomeContacts> requestFriendList = findByHQL(hql);
        RequestBecomeContacts requestFriend;
        if (requestFriendList != null && requestFriendList.size() > 0) {
            requestFriend = requestFriendList.get(0);
            update(requestFriend);
        } else {
            requestFriend = new RequestBecomeContacts();
            requestFriend.setUserId(userId);
            requestFriend.setRequestedUserId(requestedUserId);
            save(requestFriend);
        }
    }

    public List<Integer> getRequestedBecomeContactsList(int requestedUserId) {
        String hql = "from  RequestFriend" +
                " where requested_user_id=" + requestedUserId;
        System.out.println(hql);
        List<RequestBecomeContacts> requestFriendList = findByHQL(hql);
        if (requestFriendList != null && requestFriendList.size() > 0) {
            List<Integer> result = new ArrayList<>();
            for (RequestBecomeContacts temp : requestFriendList) {
                result.add(temp.getUserId());
                System.out.println(temp.getUserId());
            }
            return result;
        } else {
            return null;
        }
    }

    @Override
    public void update(RequestBecomeContacts entity) {
        entity.setCreateTime(new Date(System.currentTimeMillis()));
        super.update(entity);
    }

    @Override
    public void save(RequestBecomeContacts entity) {
        entity.setCreateTime(new Date(System.currentTimeMillis()));
        super.save(entity);
    }
}
