package entity;

import framework.BaseToStringInstance;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by xj
 * on 2016/8/16.
 */
@Entity
public class Message extends BaseToStringInstance {

    public static final int STATE_READ = 1;
    public static final int STATE_UNREAD = 2;

    private int id;
    private String content;
    private int senderId;
    private int receiverId;
    private int readState;
    private Date time;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sender_id")
    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    @Basic
    @Column(name = "receiver_id")
    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "create_time")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Basic
    @Column(name = "read_state")
    public int getReadState() {
        return readState;
    }

    public void setReadState(int readState) {
        this.readState = readState;
    }
}
