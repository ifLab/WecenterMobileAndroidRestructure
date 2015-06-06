package org.iflab.wecentermobileandroidrestructure.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hcjcch on 15/5/18.
 */

public class User {
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

    public void save(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("loginUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("uid", uid);
        editor.putString("userName", userName);
        editor.putString("avatarFile", avatarFile);
        editor.apply();
    }

    public static User getLoginUser(Context context) {
        User user = new User();
        SharedPreferences sharedPreferences = context.getSharedPreferences("loginUser", Context.MODE_PRIVATE);
        user.setUid(sharedPreferences.getInt("uid", -1));
        user.setAvatarFile(sharedPreferences.getString("avatarFile", null));
        user.setUserName(sharedPreferences.getString("userName", null));
        return user;
    }

}