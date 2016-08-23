package entity;

import framework.BaseToStringInstance;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by xj
 * on 2016/8/23.
 */
@Entity
public class RequestBecomeContacts extends BaseToStringInstance {

    private int id;
    private int userId;
    private int requestedUserId;
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
    @Column(name = "requested_user_id")
    public int getRequestedUserId() {
        return requestedUserId;
    }

    public void setRequestedUserId(int requestedUserId) {
        this.requestedUserId = requestedUserId;
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
