package org.iflab.wecentermobileandroidrestructure.model;

import org.litepal.crud.DataSupport;

/**
 * Created by hcjcch on 15/5/18.
 */

public class User extends DataSupport{
    private int uid;
    private String userName;
    private String avatarFile;

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

    public String getAvatarFile() {
        return avatarFile;
    }

    public void setAvatarFile(String avatarFile) {
        this.avatarFile = avatarFile;
    }

    public User() {

    }
}