package org.iflab.wecentermobileandroidrestructure.model.homepage;

import org.litepal.crud.DataSupport;

/**
 * Created by hcjcch on 15/5/31.
 */
public class UserInfo extends DataSupport {
    private int uid;
    private String userName;
    private String userAvatar;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                "uid=" + uid +
                ", userName='" + userName + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                '}';
    }
}