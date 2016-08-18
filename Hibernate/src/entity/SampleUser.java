package entity;

import com.sun.istack.internal.NotNull;
import framework.BaseToStringInstance;

/**
 * Created by xj
 * on 2016/8/18.
 */
public class SampleUser extends BaseToStringInstance {

    private int id;
    private String nickName;
    private int gender;
    private String avatar;

    public SampleUser(@NotNull User user) {
        this.id = user.getId();
        this.nickName = user.getNickName();
        this.gender = user.getGender();
        this.avatar = user.getAvatar();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
