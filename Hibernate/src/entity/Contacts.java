package entity;

import javax.persistence.*;

/**
 * Created by xj
 * on 2016/8/9.
 */
@Entity
public class Contacts {

    public static final int STATE_NORMAL_FRIEND = 1;

    private int id;
    private int userId;
    private int contactUserId;
    private long createTime;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "create_time")
    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "contact_user_id")
    public int getContactUserId() {
        return contactUserId;
    }

    public void setContactUserId(int contactUserId) {
        this.contactUserId = contactUserId;
    }

    public String toString() {
        return "Contacts{" +
                "userId=" + userId +
                ", contactUserId='" + contactUserId + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

}
