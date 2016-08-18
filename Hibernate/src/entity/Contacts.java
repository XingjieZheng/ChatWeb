package entity;

import framework.BaseToStringInstance;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by xj
 * on 2016/8/9.
 */
@Entity
public class Contacts extends BaseToStringInstance {

    public static final int STATE_NORMAL_FRIEND = 1;

    private int id;
    private int userId;
    private int contactUserId;
    private Date createTime;

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
    @Column(name = "contact_user_id")
    public int getContactUserId() {
        return contactUserId;
    }

    public void setContactUserId(int contactUserId) {
        this.contactUserId = contactUserId;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
